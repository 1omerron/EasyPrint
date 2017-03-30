package Server.srv.NetworkImplementation;

import Server.API.MessageEncoderDecoder;
import Server.API.Packets.AckPacket;
import Server.API.Packets.LogInOutPacket;
import Server.API.Packets.OrderPacket;
import Server.API.Packets.Packet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 1omer on 27/03/2017.
 *
 * Notes:
 * 1. The class checks if JSON file NAME packet was received before the Zipped file itself and before the json file,
 * in order to create the files in the server's directory. we can move it to the protocol if we want.
 */
public class ServerEncoderDecoder<T> implements MessageEncoderDecoder<Packet>
{
    private static final String JsonFilesDirectory = "C:\\Users\\1omer\\Desktop\\ServerFiles";
    // TODO change to Client files directory
    // TODO make it somewhere static so every class will use this path

    // general variables
    private final int BUFFER_SIZE = 1024;
    private int index=0;
    private byte[] buffer = new byte[BUFFER_SIZE];
    private Packet toReturn = null;

    // decoding variables
    private char opCode;
    private char operationCode;
    private String jsonFileName = null;
    private File jsonFile;
    private File zippedFolder;
    private int received=0;
    private int orderIndex=2;

    /**
     * add the next byte to the decoding process
     * @param nextByte the next byte to consider for the currently decoded message
     * @return a message if this byte completes one or null if it doesn't.
     */
    @Override
    public Packet decodeNextByte(byte nextByte)
    {
        // TODO remove PINIs from this class

        if(index>= this.buffer.length) {
            System.out.println("Server EncDec increasing byte buffer");
            buffer = increaseBufferSize(buffer);
        }
        buffer[index] = nextByte;
        index++;
        if(index==1) // already read 1 byte (op code byte)
        {
            opCode = (char) buffer[index - 1];
            System.out.println("Server EncDec >> read opCode : '"+opCode+"'");
            return null;
        }
        else if(index==2) // already read 2 bytes (operation)
        {
            operationCode = (char) buffer[index - 1];
            System.out.println("Server EncDec >> read operationCode : '"+operationCode+"'");
            return null;
        }
        else if(nextByte=='\0') {
            switch (opCode) {
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
                    if (toReturn != null) {
                        reset();
                    }
                    return toReturn;
                }
                default: // Any Other
                {
                    reset();
                    jsonFileName = null;
                    System.out.println("################################################");
                    System.out.println("Server EncDec >> Illegal OpCode : '" + opCode + "'");
                    System.out.println("################################################");
                    // TODO handle wrong op code - return error message
                    break;
                }
            }
        }
        return toReturn;
    }

    private Packet decodeAck()
    {
        String ackNumberString = new String(buffer,2,index-2);
        return new AckPacket(opCode, operationCode, Integer.valueOf(ackNumberString));
    }

    private Packet decodeOrder()
    {
        received++;
        switch (received)
        {
            case 1: // json name
                {
                    jsonFileName = new String(buffer, 2, index-3);
                    orderIndex = index;
                    toReturn = null;
                    break;
                }
            case 2: // json file
                {
                    try {
                        System.out.println("ServerEncDec >> jsonFileName = "+jsonFileName);
                        File file = new File("C:\\Users\\1omer\\Desktop\\ServerFiles\\"+jsonFileName);
                        file.createNewFile();
                        FileOutputStream stream = new FileOutputStream(file);
                        stream.write(buffer,orderIndex, index-orderIndex);
                        stream.flush();
                        stream.close();
                        jsonFile = file;
                        System.out.println("ServerEncDec >> decodeOrder >> finished creating json file");
                        toReturn = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    orderIndex=index;
                    break;
                }
            case 3: // zipped folder
                {
                    try {
                        String zipName = jsonFileName.substring(0, (jsonFileName.length()-4)); // removes json (. stays)
                        zipName = zipName + "zip";
                        System.out.println("ServerEncDec >> zipFileName = "+zipName);
                        File file = new File("C:\\Users\\1omer\\Desktop\\ServerFiles\\"+zipName);
                        file.createNewFile();
                        FileOutputStream stream = new FileOutputStream(file);
                        stream.write(buffer,orderIndex, index-orderIndex);
                        stream.flush();
                        stream.close();
                        System.out.println("ServerEncDec >> decodeOrder >> finished creating zipped file");
                        zippedFolder = file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    toReturn = new OrderPacket(jsonFileName, jsonFile, zippedFolder);
                    orderIndex=2;
                    received=0;
                    break;
                }
            default:
                {
                    System.out.println("ServerEncDec >> decodeOrder >> default switch case");
                }
        }
        return toReturn;
    }

    private Packet decodeLog()
    {
        String userName = new String(buffer,2,index-2);
        return new LogInOutPacket(opCode, operationCode, userName);
    }

    private Packet decodeError()
    {
        String errorMessage = new String(buffer,2,index-2);
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
