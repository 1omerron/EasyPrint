package Server.impl;

import Server.impl.Packet;

/**
 * Created by Mika on 09/01/2017.
 */
public class Dirq extends Packet {
    public Dirq() {
        super((short) 6, "DIRQ");
    }
}
