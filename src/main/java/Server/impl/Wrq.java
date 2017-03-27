package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Wrq extends Packet {
    private String fileName;
    public Wrq( String name) {
        super((short)2, "WRQ");
        fileName = name;
    }
    public String getFileName(){return fileName;}
}
