package Server.srv.ThreadPerClient;

import Server.API.Connections;
import Server.API.MessageEncoderDecoder;
import Server.API.MessagingProtocol;
import Server.API.Server;
import Server.srv.NetworkImplementation.ConnectionsImpl;
import Server.srv.NetworkImplementation.ServerEncoderDecoder;
import Server.srv.NetworkImplementation.ServerProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

public abstract class BaseServer<T> implements Server<T> {

    private final int port;
    private final Supplier<MessagingProtocol<T>> protocolFactory;
    private final Supplier<MessageEncoderDecoder<T>> encdecFactory;
    private ServerSocket sock;
    private int id;
    private Connections<T> connections;

    protected BaseServer(
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {

        this.port = port;
        this.protocolFactory = protocolFactory;
        this.encdecFactory = encdecFactory;
		this.sock = null;
		connections = new ConnectionsImpl<>();
		id=0;
    }

    @Override
    public void serve() {

        try (ServerSocket serverSock = new ServerSocket(port)) {

            this.sock = serverSock; //just to be able to close

            while (!Thread.currentThread().isInterrupted())
            {
                Socket clientSock = serverSock.accept();
                BlockingConnectionHandler<T> handler = new BlockingConnectionHandler<T>(
                        clientSock,
                        encdecFactory.get(),
                        protocolFactory.get(),connections,id);

                execute(handler);
                id++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException
    {
        if (sock != null)
			sock.close();
    }

    protected abstract void execute(BlockingConnectionHandler<T>  handler);

}
