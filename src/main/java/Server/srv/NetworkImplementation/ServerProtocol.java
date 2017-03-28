package Server.srv.NetworkImplementation;

import Server.API.Connections;
import Server.API.MessagingProtocol;
import Server.API.Packets.Packet;

/**
 * Created by 1omer on 27/03/2017.
 */
public class ServerProtocol implements MessagingProtocol<Packet>
{

    /**
     * process the given message
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    @Override
    public Packet process(Packet msg) {
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
     * @param connections handles the clients connected to the server
     */
    @Override
    public void start(Connections connections)
    {

    }
}
