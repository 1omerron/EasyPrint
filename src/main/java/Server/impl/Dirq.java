package Server.impl;

import bgu.spl171.net.impl.Packet;

/**
 * Created by Mika on 09/01/2017.
 */
public class Dirq extends Packet {
    public Dirq() {
        super((short) 6, "DIRQ");
    }
}
