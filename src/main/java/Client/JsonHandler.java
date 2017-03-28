package Client;

import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 1omer on 25/03/2017.
 */
public class JsonHandler
{
    public static String pathClient = "C:\\Users\\nimrod\\Desktop\\easyPrint";//todo check what the path to the files in the client

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
        OrderInstruction order= new OrderInstruction(list);
        toJson(order);
        new ConvertToZip().zipFiles(order);
        //todo end json tests*/
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