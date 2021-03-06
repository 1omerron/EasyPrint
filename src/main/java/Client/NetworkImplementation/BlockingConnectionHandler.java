package Client.NetworkImplementation;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.API.Packets.Packet;

import java.io.*;

import java.net.Socket;

public class BlockingConnectionHandler<T> implements Runnable, ConnectionHandler<T>, Closeable
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
        this.protocol.start(this); // using this in the constructor should do something?
    }

    public void sendOrder(File jsonObject, String jsonFileName, File zippedFolder)
    {
        int read;
        try {
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());

            ((ClientProtocol)protocol).setAndSendOrder(jsonObject, jsonFileName, zippedFolder);
            while (!protocol.shouldTerminate() && connected && (read = in.read()) >= 0)
            {
                T nextMessage = encdec.decodeNextByte((byte) read);
                if (nextMessage != null)
                {
                    System.out.println("ClientHandler >> Decoded Message != null");
                    T toReturn = protocol.process(nextMessage);
                    if(toReturn != null)
                    {
                        System.out.println("ClientHandler >> Protocol returned a packet to send to the server != null");
                        send(toReturn);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("BlockingConnectionHandler >> run() >> new Socket >> IOException");
        }
    }


    /**
     * WE DO NOT USE THE FUNCTION RUN!
     */
    public void run()
    {
/*        int read;
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
            throw new RuntimeException("BlockingConnectionHandler >> run() >> new Socket >> IOException");
        }*/
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
            System.out.println("ClientHandler >> sending the message - send(T msg) function");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
