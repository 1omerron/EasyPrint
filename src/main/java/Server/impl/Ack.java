package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Ack extends Packet {
    private short block;
    public Ack(short blockNumber) {
        super((short)4, "ACK");
        block = blockNumber;
    }

    public short getBlock() {
        return block;
    }
}
