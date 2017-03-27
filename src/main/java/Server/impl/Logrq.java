package Server.impl;

import bgu.spl171.net.impl.Packet;

/**
 * Created by Mika on 09/01/2017.
 */
public class Logrq extends Packet {
    String username;
    public Logrq(String name) {
        super((short)7, "LOGRQ");
        username = name;
    }
    public String getUsername(){return username;}
}
