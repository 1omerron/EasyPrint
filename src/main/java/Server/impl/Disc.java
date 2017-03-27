package Server.impl;

/**
 * Created by Mika on 09/01/2017.
 */
public class Disc extends Packet {
    public Disc() {
        super((short)10, "DISC");
    }
}
