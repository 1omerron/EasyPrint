package Server.srv.NetworkImplementation;

import Server.API.Connections;
import Server.API.MessagingProtocol;
import Server.API.Packets.AckPacket;
import Server.API.Packets.ErrorPacket;
import Server.API.Packets.OrderPacket;
import Server.API.Packets.Packet;
import Server.PrintOrder;

/**
 * Created by 1omer on 27/03/2017.
 */
public class ServerProtocol<T> implements MessagingProtocol<Packet>
{
    private boolean shouldTerminate;
    private char opCode;
    private char operation;
    private Packet toReturn;
    private Packet receivedPacket;

    /**
     * process the given message
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    @Override
    public Packet process(Packet msg)
    {
        this.opCode = msg.getCode();
        this.operation = msg.getOperation();
        this.receivedPacket = msg;
        toReturn = null;
        switch(opCode)
        {
            case 'e': // error
            {
                // the encoder decoder returns error message when decoding message with wrong op code
                // the error message will be that the packet received had a non-existing op code
                return new ErrorPacket('e',msg.getOperation(),((ErrorPacket)msg).getErrorMessage());
            }
            case 'l': // log in/out
            {
                toReturn = handleLog();
                return toReturn;
            }
            case 'a': // ack packet - server should not receive ack packets
            {
                toReturn = handleAck();
                return toReturn;
            }
            case 'o': // order packet
            {
                toReturn = handleOrder((OrderPacket)msg);
                return toReturn;
            }
            default: // wrong op code
            {
                return new ErrorPacket('e', '0', "Wrong Op Code Received");
            }
        }
    }

    /**
     * handles order packet
     * calls the printOrder function in order to handle the order
     * @return acknowledge packet to return, or null if no answer needed
     */
    private Packet handleOrder(OrderPacket msg)
    {
    PrintOrder.printOrder(msg.getJsonFileName()); // PRINTING THE FILE
        toReturn = new AckPacket('a', '0', 0);
        return toReturn;
    }

    /**
     * handles receiving ack packet
     * @return packet to return, or null if no answer needed
     */
    private Packet handleAck()
    {
        // TODO implement if needed
        return null;
    }

    /**
     * handles log in request or log out request
     * @return the coresponding packet, or null if no answer needed
     */
    private Packet handleLog()
    {
        switch (operation)
        {
            case '0': // log out
            {
                if(opCode=='l' /* TODO check if the username exists in the database */)
                {
                    // TODO remove the username from the database
                    shouldTerminate = true;
                    return new AckPacket('a', '1', 0);
                }
                else
                {
                    return new ErrorPacket('e', '1', "Username doesn't exist in the database");
                }
            }
            case '1': // log in
            {
                if(opCode!='l' /* TODO check if the username exists in the database */)
                {
                    return new ErrorPacket('e', '1', "Username is Already Taken");
                }
                else
                {
                    // TODO add the username to the database
                    return new AckPacket('a', '1', 0);
                }
            }
            default:
            {
                return new ErrorPacket('e', '0', "Incorrect Operation Code In Log Packet");
            }
        }
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
     * @param connections handles the clients connected to the server
     */
    @Override
    public void start(Connections connections)
    {
        shouldTerminate = false;
    }

    public void printOrder(String jsonName)
    {

    }
}
