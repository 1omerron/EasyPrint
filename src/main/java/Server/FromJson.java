package Server;

import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

/**
 * Created by nimrod on 26/03/2017.
 */
public class FromJson
{
    public static String pathServer = "C:\\Users\\nimrod\\Desktop\\easyPrint";//todo check what the path to the files in the server
    public static void main(String[] args)
    {
        FileInfo fileInfo1 = new FileInfo();
        FileInfo fileInfo2 = new FileInfo();
        fileInfo1.setFile(new File("C:\\Users\\nimrod\\Desktop\\algo\\algo-zohar.pdf"));
        fileInfo2.setFile(new File("C:\\Users\\nimrod\\Desktop\\easyPrint\\zohar.pdf"));
        FileInstruction file1 = new FileInstruction(fileInfo1);
        FileInstruction file2 = new FileInstruction(fileInfo2);
        LinkedList<FileInstruction> list = new LinkedList<>();
        list.add(file1);
        list.add(file2);
        OrderInstruction order = new OrderInstruction(list);
        //OrderInstruction orderTest = fromJson("order.json"); //make class from the json file "order.json"
        // Client client = new Client("127.0.0.1",7777);
        // client.start(new ClientEncoderDecoder(),new ClientProtocol());
    }

    /**
     * converting json file to java object
     * @param filename name of the json file (including the ".json")
     */
    public static OrderInstruction fromJson(String filename)
    {
        Gson gson = new Gson();
        OrderInstruction orderIns=null;
        try (Reader reader = new FileReader(pathServer+filename)) {

            // Convert JSON to Java Object
            orderIns = gson.fromJson(reader, OrderInstruction.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //update the File file in each file info
        for(FileInstruction fileins: orderIns.getInstructionsList())
        {
            filename = fileins.getFile().getFileName();
            fileins.getFile().setFile(new File(pathServer+filename));
        }
        return orderIns;
    }
}
