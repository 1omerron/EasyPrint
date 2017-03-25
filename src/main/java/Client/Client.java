package Client;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.NetworkImplementation.ClientEncoderDecoder;
import Client.NetworkImplementation.ClientProtocol;

/**
 * Created by 1omer on 25/03/2017.
 */
public class Client
{
    private final String serverIp;
    private final int serverPort;

    private ConnectionHandler handler;

    private void start(MessageEncoderDecoder coder, MessagingProtocol prot)
    {
        this.handler = new BlockingConnectionHandler(serverIp, serverPort, coder, prot);
        handler.run();
    }

    public Client(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
    }

    public static void main(String[] args)
    {
        Client client = new Client("127.0.0.1",7777);
        client.start(new ClientEncoderDecoder(),new ClientProtocol());
    }
}
