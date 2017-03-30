package Client;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.NetworkImplementation.ClientEncoderDecoder;
import Client.NetworkImplementation.ClientProtocol;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by 1omer on 25/03/2017.
 */
public class Client<T>
{
    public static String pathClient = "C:\\Users\\1omer\\Desktop\\ClientFiles\\";

    private final String serverIp;
    private final int serverPort;
    private Socket socket;

    private ConnectionHandler handler;

    public void start(MessageEncoderDecoder<T> coder, MessagingProtocol<T> prot)
    {
        try {
            this.socket = new Socket(this.serverIp,this.serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.handler = new BlockingConnectionHandler<T>(this.socket, coder, prot);
    }

    public void sendOrder(File jsonObject, String jsonFileName, File zippedFolder)
    {
        ((BlockingConnectionHandler)this.handler).sendOrder(jsonObject, jsonFileName, zippedFolder);
    }

    public Client(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
    }

    public static void main(String[] args)
    {
        /* JUST FOR TESTING THE CLIENT - REMOVE THE MAIN AFTER */
        Client client = new Client("132.72.233.199",7777);
        client.start(new ClientEncoderDecoder(),new ClientProtocol());
        String fileName = "jsonfile";
        File jsonObject = new File(pathClient+fileName+".json");
        File zippedFiles = new File(pathClient+fileName+".zip");
        String jsonFileName = jsonObject.getName();
        client.sendOrder(jsonObject, jsonFileName, zippedFiles);
        System.out.println("Finished. Disconnected.");
    }
}
