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
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private volatile boolean connected = true;
    private Socket socket;

    public BlockingConnectionHandler(Socket sock, MessageEncoderDecoder<T> reader, MessagingProtocol<T> protocol)
    {
        this.socket = sock;
        this.encdec = reader;
        this.protocol = protocol;
        this.protocol.start();
    }


    public void run()
    {
        int read;
        try {
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
    public void send(T msg)
    {
        try {
            out.write(encdec.encode(msg));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
