package Client.NetworkImplementation;

import Client.API.MessagingProtocol;

/**
 * Created by 1omer on 25/03/2017.
 */
public class ClientProtocol implements MessagingProtocol
{
    /**
     * starts the protocol
     */
    public void start()
    {
    }

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
}
