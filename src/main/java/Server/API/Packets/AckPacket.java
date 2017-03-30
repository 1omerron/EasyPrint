package Server.API.Packets;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : a
 *
 * Operation Field in Ack represents:
 * '0' - ack for order
 * '1' - ack for login/logout
 *
 * byte[] data = the bytes representing the String value, of the acknowledge number
 */
public class AckPacket extends Packet
{
    private int ackPacketNumber;

    public AckPacket(char code, char operation, int ackPacketNumber)
    {
        super(code,operation,(String.valueOf(ackPacketNumber)+'\0').getBytes());
        this.ackPacketNumber = ackPacketNumber;
    }

    public int getAckPacketNumber() {
        return ackPacketNumber;
    }
}
