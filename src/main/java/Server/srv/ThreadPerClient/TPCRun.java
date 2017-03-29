package Server.srv.ThreadPerClient;

import Server.srv.NetworkImplementation.ServerEncoderDecoder;
import Server.srv.NetworkImplementation.ServerProtocol;

import java.util.function.Supplier;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TPCRun
{
    public static void main(String[] args)
    {
        Supplier encdec = () -> new ServerEncoderDecoder();
        Supplier prot = () -> new ServerProtocol();
        ThreadPerClientServer tpc = new ThreadPerClientServer(7777,
                prot, encdec);
        tpc.serve();
    }
}
