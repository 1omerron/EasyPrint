package Server.srv.ThreadPerClient;

import Server.API.MessageEncoderDecoder;
import Server.API.ConnectionHandler;
import Server.API.Connections;
import Server.API.MessagingProtocol;
import Server.srv.NetworkImplementation.ConnectionsImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BlockingConnectionHandler<T> implements Runnable, java.io.Closeable,ConnectionHandler<T> {

    private final MessagingProtocol<T> protocol;
    private final MessageEncoderDecoder<T> encdec;
    private final Socket sock;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;

    /* Package Private */ BlockingConnectionHandler(Socket sock, MessageEncoderDecoder<T> reader, MessagingProtocol<T> protocol, Connections<T> connections, int id) {

        this.sock = sock;
        this.encdec = reader;
        this.protocol = protocol;
        this.protocol.start(connections);
        ((ConnectionsImpl<T>)connections).add(id, this); //add the connection to the connections
    }

    @Override
    public void run() {

        try (Socket sock = this.sock) { //just for automatic closing
            int read;

            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());

            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0)
            {
                T nextMessage = encdec.decodeNextByte((byte) read);
                if (nextMessage != null)
                {
                    protocol.process(nextMessage);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException
    {
        connected = false;
        sock.close();
    }

    @Override
    public void send(T msg)
    {
        if(msg!=null)
        {
            try {
                out.write(encdec.encode(msg));
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
