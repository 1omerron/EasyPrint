
package Server.srv.Reactor;


import Server.srv.NetworkImplementation.ServerEncoderDecoder;
import Server.srv.NetworkImplementation.ServerProtocol;
import Server.API.Server;

/**
 * Created by Mika on 13/01/2017.
 */

public class ReactorMain
{
    public static void main(String[] args)
    {
        int port = Integer.parseInt(args[0]);
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                port,
                () -> new ServerProtocol(), //protocol factory
                () -> new ServerEncoderDecoder() //message encoder decoder factory
        ).serve();
    }
}
