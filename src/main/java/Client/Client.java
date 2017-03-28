package Client;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.NetworkImplementation.ClientEncoderDecoder;
import Client.NetworkImplementation.ClientProtocol;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by 1omer on 28/03/2017.
 *
 * Client does not use Connections Interface so its connection handler is a bit changed
 */
public class Client<T>
{
    public static String pathClient = "C:\\Users\\nimrod\\Desktop\\easyPrint";//todo check what the path to the files in the client
    private final String serverIp;
    private final int serverPort;

    private ConnectionHandler<T> handler;

    private void start(MessageEncoderDecoder coder, MessagingProtocol prot)
    {
        Socket socket = null;
        try {
            socket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.handler = new BlockingConnectionHandler<>(socket, coder, prot);
    }

    public Client(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
        start(new ClientEncoderDecoder(),new ClientProtocol());
    }
}
