package Server;

import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

/**
 * Created by nimrod on 26/03/2017.
 */
public class fromJson
{
    public static void main(String[] args)
    {
        OrderInstruction orderTest = fromJson("order.json"); //make class from the json file "order.json"
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
        try (Reader reader = new FileReader("C:\\Users\\nimrod\\"+filename)) {

            // Convert JSON to Java Object
            orderIns = gson.fromJson(reader, OrderInstruction.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderIns;
    }
}
