package Client.NetworkImplementation;

import Client.API.MessageEncoderDecoder;
import Client.API.Packets.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 1omer on 25/03/2017.
 */
public class ClientEncoderDecoder implements MessageEncoderDecoder<Packet>
{
    private static final String JsonFilesDirectory = "C:\\Users\\1omer\\Desktop\\ClientFiles";
    // TODO change to Client files directory
    // TODO make it somewhere static so every class will use this path

    // general variables
    private final int BUFFER_SIZE = 1024;
    private int index=0;
    private byte[] buffer = new byte[BUFFER_SIZE];
    private Packet toReturn;

    // decoding variables
    private char opCode;
    private char operationCode;

    /**
     * add the next byte to the decoding process
     * @param nextByte the next byte to consider for the currently decoded message
     * @return a message if this byte completes one or null if it doesn't.
     */
    @Override
    public Packet decodeNextByte(byte nextByte)
    {
        toReturn = null;
        if(index>= this.buffer.length)
            buffer = increaseBufferSize(buffer);
        buffer[index++] = nextByte;
        if(index==1) // already read 1 byte (op code byte)
        {
            opCode = (char) buffer[index - 1];
        }
        else if(index==2) // already read 2 bytes (operation)
        {
            operationCode = (char) buffer[index - 1];
        }
        else if(nextByte=='\0') // finished receiving bytes, decode. skip and return null otherwise
        {
            switch(opCode)
            {
                case 'e': // Error
                {
                    toReturn = decodeError();
                    if (toReturn != null)
                        reset();
                    return toReturn;
                }
                case 'l': // Log In/Out
                {
                    toReturn = decodeLog();
                    if (toReturn != null)
                        reset();
                    return toReturn;
                }
                case 'o': // Order
                {
                    toReturn = decodeOrder();
                    if (toReturn != null)
                        reset();
                    return toReturn;
                }
                case 'a': // Acknowledge
                {
                    toReturn = decodeAck();
                    if (toReturn != null)
                        reset();
                    return toReturn;
                }
            }
        }
        return toReturn;
    }

    private Packet decodeAck()
    {
        String bytesOfInteger = "";
        for(int i=2; i<index; i++)
        {
            if(buffer[i]!='\0')
                bytesOfInteger = bytesOfInteger+(char)buffer[i];
        }
        int ackNum = Integer.parseInt(bytesOfInteger);
        return new AckPacket(opCode, operationCode, ackNum);
    }

    private Packet decodeOrder()
    {
        // client should not get order packets
        return null;
    }

    private Packet decodeLog()
    {
        String userName = new String(buffer,2,index-1);
        return new LogInOutPacket(opCode, operationCode, userName);
    }

    private Packet decodeError()
    {
        String errorMessage = new String(buffer,2,index-1);
        return new LogInOutPacket(opCode, operationCode, errorMessage);
    }

    private void reset()
    {
        index=0;
        opCode = '\0';
        operationCode = '\0';
    }
    /**
     * increases buffer's size by BUFFER_SIZE * 10
     * buffer will not return to original first size after this function
     * @param buf the buf to increase and copy
     * @return new buffer with the same bytes, with the length required
     */
    private byte[] increaseBufferSize(byte[] buf)
    {
        byte[] newBuf = new byte[buf.length + BUFFER_SIZE*10];
        System.arraycopy(buf, 0, newBuf, 0, buf.length);
        return newBuf;
    }

    /**
     * encodes the given message to bytes array
     * @param message the message to encode
     * @return the encoded bytes
     */
    @Override
    public byte[] encode(Packet message) {
        return message.encodePacket();
    }
}
