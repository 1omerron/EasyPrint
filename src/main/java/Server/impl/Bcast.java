package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Bcast extends Packet {
    private String fileName;
    private byte del_add;
    public Bcast(byte del_add, String name) {
        super((short)9, "BCAST");
        fileName = name;
        this.del_add = del_add;
    }

    public byte getDel_add() {
        return del_add;
    }

    public String getFileName() {
        return fileName;
    }
}
