package Client;
import Client.API.ConnectionHandler;
import Client.API.MessageEncoderDecoder;
import Client.API.MessagingProtocol;
import Client.NetworkImplementation.BlockingConnectionHandler;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 1omer on 25/03/2017.
 */
public class Client
{
    public static String pathClient = "C:\\Users\\nimrod\\Desktop\\easyPrint";//todo check what the path to the files in the client
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
        ConvertToZip toZip = new ConvertToZip();
        toZip.zipFiles(pathClient+"\\nimrod",pathClient+"\\nimrod.zip");
        //todo end json tests
       // Client client = new Client("127.0.0.1",7777);
       // client.start(new ClientEncoderDecoder(),new ClientProtocol());
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
    private static void zipFile(String filePath, String filename) {
        try {

            // input file
            FileInputStream in = new FileInputStream(filePath);

            // out put file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(pathClient+"\\"+filename+".zip"));

            // name the file inside the zip  file
            out.putNextEntry(new ZipEntry(filename+".json"));

            // buffer size
            byte[] b = new byte[1024];
            int count;

            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.close();
            in.close();

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
}
