/*
package Server.impl.TFTPreactor;
import Server.impl.TftpEncoderDecoder;
import Server.impl.TftpProtocol;

*/
/**
 * Created by Mika on 13/01/2017.
 *//*

public class ReactorMain {
  
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                port,
                //7777, //port
                () -> new TftpProtocol(), //protocol factory
                TftpEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
}
*/
