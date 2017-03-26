package Client;

import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.NetworkImplementation.ClientEncoderDecoder;
import Client.NetworkImplementation.ClientProtocol;
import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
        FileInstruction fileIns1 = new FileInstruction(new FileInfo());
        FileInstruction fileIns2 = new FileInstruction(new FileInfo());
        LinkedList<FileInstruction> list = new LinkedList<>();
        list.add(fileIns1);
        list.add(fileIns2);
        OrderInstruction ordreIns = new OrderInstruction(list);
        toJson(ordreIns);
       // Client client = new Client("127.0.0.1",7777);
       // client.start(new ClientEncoderDecoder(),new ClientProtocol());
    }

    /**
     * convert OrderInstruction to json file.
     * @param order instruction of the user.
     */
    public static void toJson(OrderInstruction order)
    {
        //1. Convert object to JSON string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //todo printing for testing remove after
        String json = gson.toJson(order);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("C:\\Users\\nimrod\\order.json")) {

            gson.toJson(order, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
