package Client.NetworkImplementation;

import Client.API.ConnectionHandler;
import Client.API.MessagingProtocol;
import Client.API.Packets.*;

import java.io.File;

/**
 * Created by 1omer on 25/03/2017.
 */
public class ClientProtocol implements MessagingProtocol<Packet>
{
    private boolean shouldTerminate;
    private ConnectionHandler<Packet> myHandler;
    private String myUserName;

    private char opCode;
    private char operation;
    private Packet toReturn;

    private int orderPartsSent = 0;
    private File jsonObject, zippedFolder;

    public void setAndSendOrder(File jsonObject, String jsonFileName, File zippedFolder)
    {
        this.jsonObject = jsonObject;
        this.zippedFolder = zippedFolder;
        this.myHandler.send(new OrderPacket(jsonFileName));
        orderPartsSent++;
    }

    /**
     * process the given message
     * @param msg the received message
     * @return the response to send or null if no response is expected by the server
     */
    @Override
    public Packet process(Packet msg)
    {
        this.opCode = msg.getCode();
        this.operation = msg.getOperation();

        switch(opCode)
        {
            case 'e': // error
            {
                // the encoder decoder returns error message when decoding message with wrong op code
                // the error message will be that the packet received had a non-existing op code
                System.out.println("#### Got an Error Message from the Server: ####");
                System.out.println(((ErrorPacket)msg).getErrorMessage());
                // TODO change or remove
                break;
            }
            case 'l': // log in/out
            {
                // client should not receive log in/out packets
                break;
            }
            case 'a': // ack packet
            {
                toReturn = handleAck();
                return toReturn;
            }
            case 'o': // order packet
            {
                // client should not receive order packet
                break;
            }
            default: // wrong op code
            {
                // should never get here because of enc-dec
                System.out.println("Client Protocol >> process >> Received packet with wrong op code");
                // TODO remove
                break;
            }
        }
        return null;
    }

    /**
     * handles receiving ack packet
     * @return packet to return, or null if no answer needed
     */
    private Packet handleAck()
    {
        switch (orderPartsSent)
        {
            case 1:
            {
                toReturn = new OrderPacket(jsonObject);
                break;
            }
            case 2:
            {
                toReturn =  new OrderPacket(zippedFolder);
                break;
            }
            case 3:
            {
                orderPartsSent = 0;
                shouldTerminate = true;
                toReturn = new LogInOutPacket('l', '0', myUserName);
                break;
            }
            default:
            {
                toReturn = null;
                break;
            }
        }
        return toReturn;
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
    public void start(ConnectionHandler myHandler)
    {
        this.myHandler = myHandler;
        shouldTerminate = false;
        this.myUserName = "Omer"; // TODO change
    }
}
