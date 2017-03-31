package Client;

import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import Client.User.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;

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
        fileInfo1.setFile(new File("C:\\Users\\nimrod\\Desktop\\easyPrint\\Start.docx"));
        fileInfo2.setFile(new File("C:\\Users\\nimrod\\Desktop\\easyPrint\\nimrod.txt"));
        FileInstruction file1 = new FileInstruction(fileInfo1);
        FileInstruction file2 = new FileInstruction(fileInfo2);
        LinkedList<FileInstruction> list = new LinkedList<>();
        list.add(file1);
        list.add(file2);
        OrderInstruction order = new OrderInstruction(list, "jsonfile");
        JsonHandler.toJson(order);
        ConvertToZip.zipFiles(order);
        //todo end json tests*/
    }

    /**
     * convert OrderInstruction or User to json file.
     * @param object instruction of the user.
     */
    //todo this function static - maybe we need to change this
    public static void toJson(Object object)
    {
        //1. Convert object to JSON string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //2. Convert object to JSON string and save into a file directly
        if(object instanceof OrderInstruction)
        {
            String filename = ((OrderInstruction) object).getOrderId();
            try (FileWriter writer = new FileWriter(pathClient+"\\"+filename+".json")) {

                gson.toJson(object, writer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            String filename = ((User) object).getUserId();
            try (FileWriter writer = new FileWriter(pathClient+"\\"+filename+"_User.json")) {

                gson.toJson(object, writer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User fromJson(String filename)
    {
        Gson gson = new Gson();
        User user = null;
        try (Reader reader = new FileReader(Client.pathClient+"\\"+filename)) {
            // Convert JSON to Java Object
            user = gson.fromJson(reader, User.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
