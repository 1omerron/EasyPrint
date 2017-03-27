package Server.srv;

import Server.API.MessageEncoderDecoder;
import Server.API.MessagingProtocol;

import java.util.function.Supplier;

/**
 * Created by 1omer on 10/01/2017.
 */
public class ThreadPerClientServer<T> extends BaseServer<T> {

    public ThreadPerClientServer(
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encoderDecoderFactory) {

        super(port, protocolFactory, encoderDecoderFactory);
    }

    @Override
    protected void execute(BlockingConnectionHandler handler)
    {
        new Thread(handler).start();
    }

}
