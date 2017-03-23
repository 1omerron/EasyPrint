package Server.impl;

import Server.impl.Packet;

/**
 * Created by Mika on 09/01/2017.
 */
public class Rrq extends Packet {
    private String fileName;
    public Rrq(String name) {
        super((short)1, "RRQ");
        fileName = name;
    }
    public String getFileName(){return fileName;}
}
