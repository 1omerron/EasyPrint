package Client.NetworkImplementation;

import Client.API.MessagingProtocol;
import Client.API.Packets.Packet;

/**
 * Created by 1omer on 25/03/2017.
 */
public class ClientProtocol implements MessagingProtocol<Packet>
{
    private boolean shouldTerminate = false;

    // processing variables
    private char op;
    private char operation;
    // private int connectionId;

    /**
     * process the given message
     *
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    @Override
    public Packet process(Packet msg)
    {
        this.op = msg.getCode();
        this.op = msg.getOperation();
        if(op=='e')
        {

        }

        return null;
    }

    /**
     * @return true if the connection should be terminated
     */
    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    /**
     * starts the protocol
     */
    @Override
    public void start()
    {

    }
}
