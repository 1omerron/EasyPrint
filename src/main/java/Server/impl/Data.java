package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Data extends Packet {
    private short block;
    private short packetSize;
    private byte[] data;
    public Data(short blockNumber, short size,byte[] n) {
        super((short)3, "DATA");
        block = blockNumber;
        if(size==-1){size=0;}
        packetSize = size;
        if(n.length!=size){
            byte[] updatedData = new byte[size];
            for (int i=0; i<size;i++){
                updatedData[i]=n[i];
            }
            data = updatedData;
        }
        else {
            data = n;
        }
    }

    public short getPacketSize() {
        return packetSize;
    }

    public short getBlock() {
        return block;
    }

    public byte[] getData() {
        return data;
    }
}
