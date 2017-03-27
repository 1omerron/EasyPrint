package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public abstract class Packet {
    private short opCode;
    private String type;
    public Packet(short op, String type){
        opCode=op;
        this.type=type;
    }
    public short getOpCode(){
        return opCode;
    }

}
