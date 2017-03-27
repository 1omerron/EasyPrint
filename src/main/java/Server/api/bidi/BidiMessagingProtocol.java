package Server.api.bidi;

import java.io.IOException;

/**
 * Created by Mika on 08/01/2017.
 */

public interface BidiMessagingProtocol<T>  {

    void start(int connectionId, Connections<T> connections);

    void process(T message) throws IOException;

    /**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
}