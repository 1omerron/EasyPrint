package Server.srv.NetworkImplementation;

import Server.API.MessagingProtocol;

/**
 * Created by 1omer on 27/03/2017.
 */
public class ServerProtocol implements MessagingProtocol
{
    /**
     * process the given message
     *
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    @Override
    public Object process(Object msg) {
        return null;
    }

    /**
     * @return true if the connection should be terminated
     */
    @Override
    public boolean shouldTerminate() {
        return false;
    }

    /**
     * starts the protocol
     */
    @Override
    public void start() {

    }
}
