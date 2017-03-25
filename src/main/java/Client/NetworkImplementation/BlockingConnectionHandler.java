package Client.NetworkImplementation;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;

import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T>
{
    private final MessagingProtocol<T> protocol;
    private final MessageEncoderDecoder<T> encdec;
    private String serverIp;
    private int serverPort;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;
    private int id=-1;
    private Socket socket;

    public BlockingConnectionHandler(String serverIp, int port, MessageEncoderDecoder<T> reader, MessagingProtocol<T> protocol)
    {
        this.serverIp = serverIp;
        this.serverPort = port;
        this.encdec = reader;
        this.protocol = protocol;
    }

    @Override
    public void run()
    {
        protocol.start();
        int read;
        try {
            this.socket = new Socket(serverIp, serverPort);
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());

            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0)
            {
                T nextMessage = encdec.decodeNextByte((byte) read);
                if (nextMessage != null) {
                    protocol.process(nextMessage);
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("BlockingConnectionHandler >> run() >> new Socket >> IOExceptio");
        }
    }

    @Override
    public void close() throws IOException
    {
        connected = false;
        socket.close();
    }

    @Override
    public void send(T msg) throws IOException
    {
        out.write(encdec.encode(msg));
        out.flush();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){return id;}

    @Override
    public MessagingProtocol<T> getProtocol()
    {
        return protocol;
    }
}
