
package Server.impl.TFTPtpc;

import bgu.spl171.net.Server;
import bgu.spl171.net.impl.TftpEncoderDecoder;
import bgu.spl171.net.impl.TftpProtocol;

/**
 * Created by Mika on 13/01/2017.
 */
public class TPCMain {

   
    public static void main(String[] args) {
        String port = args[0];
        Server.threadPerClient(
                Integer.parseInt(port), //port
                () -> new TftpProtocol(), //protocol factory
                TftpEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
}
