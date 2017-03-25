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

    public Packet(char code, char operation, byte[] dataArray)
    {
        this.code = code;
        this.operation = operation;
        this.data = dataArray;
    }

    // Encoding

    public byte[] encodePacket()
    {
        byte[] encoded = new byte[data.length+2];
        encoded[0] = (byte)code;
        encoded[1] = (byte)operation;
        for(int i=0;i<data.length;i++)
        {
            encoded[i+2] = data[i];
        }
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
