package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Delrq extends Packet {
    private String fileName;
    public Delrq( String name) {
        super((short)8, "DELRQ");
        fileName = name;
    }
    public String getFileName(){return fileName;}

}
