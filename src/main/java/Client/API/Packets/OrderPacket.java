package Client.API.Packets;

import org.json.simple.JSONObject;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : o (the letter o, not zero)
 *
 * Operation Field in Order Packet represents:
 * '0' - JSON file
 * '1' - JSON file NAME
 * '2' - The file to print itself
 * .
 *
 * byte[] data = the bytes representing the JSON file bytes
 */
public class OrderPacket extends Packet
{
    private JSONObject order;

    public OrderPacket(char code, char operation, JSONObject orderJsonFile)
    {
        super(code,operation, orderJsonFile.toString().getBytes() /* TODO change the empty string for the super constructor! */);
        this.order = orderJsonFile;
    }
}
