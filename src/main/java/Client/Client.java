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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 1omer on 25/03/2017.
 */
public class Client
{
    public static String pathClient = "C:\\Users\\nimrod\\";//todo check what the path to the files in the client
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

        //todo json tests
        FileInstruction fileIns1 = new FileInstruction(new FileInfo());
        FileInstruction fileIns2 = new FileInstruction(new FileInfo());
        LinkedList<FileInstruction> list = new LinkedList<>();
        list.add(fileIns1);
        list.add(fileIns2);
        OrderInstruction ordreIns = new OrderInstruction(list);
        toJson(ordreIns);
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
        try (FileWriter writer = new FileWriter(pathClient+filename)) {

            gson.toJson(order, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        zipFile(pathClient+"order.json");
    }
    private static void zipFile(String filePath) {
        try {
            File file = new File(filePath);
            String zipFileName = file.getName().concat(".zip");

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(file.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
}
