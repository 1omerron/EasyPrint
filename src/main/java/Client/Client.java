package Client;
import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 1omer on 25/03/2017.
 *
 * Client does not use Connections Interface so its connection handler is a bit changed
 */
public class Client<T>
{
    public static String pathClient = "C:\\Users\\nimrod\\Desktop\\easyPrint";//todo check what the path to the files in the client
    private final String serverIp;
    private final int serverPort;

    private ConnectionHandler handler;

    private void start(MessageEncoderDecoder<T> coder, MessagingProtocol<T> prot) throws IOException
    {
        Socket socket = new Socket(serverIp, serverPort);
        this.handler = new BlockingConnectionHandler<>(socket, coder, prot);
        //handler.run();todo fix this line
    }

    public Client(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
    }

    public static void main(String[] args)
    {
        /*
           Client client = new Client("127.0.0.1",7777);
           client.start(new ClientEncoderDecoder(),new ClientProtocol());
         */

        FileInfo fileInfo1 = new FileInfo();
        FileInfo fileInfo2 = new FileInfo();
        fileInfo1.setFile(new File("C:\\Users\\nimrod\\Desktop\\algo\\algo-zohar.pdf"));
        fileInfo2.setFile(new File("C:\\Users\\nimrod\\Desktop\\easyPrint\\zohar.pdf"));
        FileInstruction file1 = new FileInstruction(fileInfo1);
        FileInstruction file2 = new FileInstruction(fileInfo2);
        LinkedList<FileInstruction> list = new LinkedList<>();
        list.add(file1);
        list.add(file2);
        OrderInstruction order= new OrderInstruction(list);
        toJson(order);
        new ConvertToZip().zipFiles(order);
        //todo end json tests
    }

    /**
     * convert OrderInstruction to json file.
     * @param order instruction of the user.
     */
    //todo this function static - maybe we need to change this
    public static void toJson(OrderInstruction order)
    {
        //1. Convert object to JSON string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //todo printing for testing remove after
        String json = gson.toJson(order);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        String filename = order.getOrderId();
        try (FileWriter writer = new FileWriter(pathClient+"\\"+filename+".json")) {

            gson.toJson(order, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
