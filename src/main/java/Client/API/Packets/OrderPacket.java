package Client.API.Packets;

import java.io.File;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : o (the letter o, not zero)
 *
 * Operation Field in Order Packet represents:
 * '0' - JSON file
 * '1' - JSON file NAME
 * .
 *
 * byte[] data = the bytes representing the information to send (JSON File / Json file Name)
 */
public class OrderPacket extends Packet
{
    private Object information;

    // Constructor with JSON Object
    public OrderPacket(File orderJsonFile)
    {
        super('o','0', orderJsonFile.toString().getBytes());
        information = orderJsonFile;
    }

    // Constructor with JSON name
    public OrderPacket(String jsonName /* Should be Order-Number.json */)
    {
        super('o','1', jsonName.getBytes());
        information = jsonName;
    }

    public Object getInformation() {
        return information;
    }
}
