package Client.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Packets Instructions:
 * (the char representing the type of packet is the first letter of the description)
 * 'e' - error packet
 * 'l' - log in packet
 * 'a' - acknowledge packet
 * 'o' - order packet
 * 'd' - disconnect packet
 *
 * Operation field:
 * a char which is relevant to the instruction of the packet
 * Example - LogInOut packet - Operation '0' is logout request, Operation '1' is login request
 *
 * Data array:
 * array of bytes representing the data of the packet
 */

public abstract class Packet
{
    private char code;
    private char operation;
    private byte[] data;

    // Constructors

    /* Package Private */ Packet(char code, char operation, byte[] dataArray)
    {
        this.code = code;
        this.operation = operation;
        this.data = dataArray;
    }

    // Encoding

    /**
     * encodes the packet to a byte array containing:
     * Packet Code (1 char), Packet Operation (1char) - 0 is default (when not needed), and the array of
     * bytes representing the data contained in this packet
     * @return
     */
    public byte[] encodePacket()
    {
        byte[] encoded = new byte[data.length+2];
        encoded[0] = (byte)code;
        encoded[1] = (byte)operation;
        System.arraycopy(data, 0, encoded, 2, data.length); // i hope it works - omer
        return encoded;
    }

    // Getters and Setters

    public void setCode(char code)
    {
        this.code = code;
    }

    public void setOperation(char operation)
    {
        this.operation = operation;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public char getCode()
    {
        return code;
    }

    public char getOperation()
    {
        return operation;
    }

    public byte[] getData()
    {
        return data;
    }
}
