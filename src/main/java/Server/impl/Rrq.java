package Server.impl;

import bgu.spl171.net.impl.Packet;

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
