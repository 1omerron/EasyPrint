package Client.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
 */
public class AckPacket extends Packet
{
    private int ackPacketNumber;

    public AckPacket(char code, char operation, int ackPacketNumber)
    {
        super(code,operation,String.valueOf(ackPacketNumber).getBytes());
        this.ackPacketNumber = ackPacketNumber;
    }
}
