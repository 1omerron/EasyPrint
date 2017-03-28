package Server.srv.ThreadPerClient;

import Server.API.Packets.Packet;
import Server.srv.NetworkImplementation.ServerEncoderDecoder;
import Server.srv.NetworkImplementation.ServerProtocol;
import Server.API.Server;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TPCMain
{
    public static void main(String[] args)
    {
        Server.threadPerClient(
                7777, //port
                () -> new ServerProtocol(), //protocol factory
                () -> new ServerEncoderDecoder() //message encoder decoder factory
        ).serve();
    }
}
