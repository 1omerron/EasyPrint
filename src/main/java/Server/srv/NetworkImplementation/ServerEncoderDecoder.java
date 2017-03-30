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
 */
public class ServerEncoderDecoder<T> implements MessageEncoderDecoder<Packet>
{
    private static final String ServerFilesFolder = "C:\\Users\\nimrod\\Desktop\\easyPrint\\";

    // general variables
    private final int BUFFER_SIZE = 102400000;
    private int index = 0;
    private byte[] buffer = new byte[BUFFER_SIZE];
    private Packet toReturn = null;

    // decoding variables
    private char opCode;
    private char operationCode;
    private String jsonFileName;
    private File jsonFile;
    private File zippedFolder;
    private int nameLength;
    private int fileLength;
    private int zipLength;
    private int beginningOfFileIndex;
    private int partsReceived;


    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded message
     * @return a message if this byte completes one or null if it doesn't.
     */
    @Override
    public Packet decodeNextByte(byte nextByte)
    {
        toReturn = null;
        if (index >= this.buffer.length) {
            buffer = increaseBufferSize(buffer);
        }
        buffer[index++] = nextByte;
        if (index == 1) // already read 1 byte (op code byte)
        {
            opCode = (char) buffer[index - 1];
            return null;
        } else if (index == 2) // already read 2 bytes (operation)
        {
            operationCode = (char) buffer[index - 1];
            return null;
        } else {
            switch (opCode) {
                case 'e': // Error
                {
                    if (nextByte == '\0') {
                        toReturn = decodeError();
                        if (toReturn != null)
                            reset();
                    }
                    return toReturn;
                }
                case 'l': // Log In/Out
                {
                    if (nextByte == '\0') {
                        toReturn = decodeLog();
                        if (toReturn != null)
                            reset();
                    }
                    return toReturn;
                }
                case 'o': // Order
                {
                    if (index == 6) // now need to get length of an item
                    {
                        byte[] bytesOfLength = new byte[4];
                        bytesOfLength[0] = buffer[2];
                        bytesOfLength[1] = buffer[3];
                        bytesOfLength[2] = buffer[4];
                        bytesOfLength[3] = buffer[5];
                        this.nameLength = fromByteArrayToInt(bytesOfLength);
                    } else if (index == 10) {
                        byte[] bytesOfLength = new byte[4];
                        bytesOfLength[0] = buffer[6];
                        bytesOfLength[1] = buffer[7];
                        bytesOfLength[2] = buffer[8];
                        bytesOfLength[3] = buffer[9];
                        this.fileLength = fromByteArrayToInt(bytesOfLength);
                    } else if (index == 14) {
                        byte[] bytesOfLength = new byte[4];
                        bytesOfLength[0] = buffer[10];
                        bytesOfLength[1] = buffer[11];
                        bytesOfLength[2] = buffer[12];
                        bytesOfLength[3] = buffer[13];
                        this.zipLength = fromByteArrayToInt(bytesOfLength);
                        System.out.println("decodeNextByte : zipLength = " + this.zipLength);
                        beginningOfFileIndex = 14;
                        partsReceived = 0;
                    } else if (index >= 15)// finished getting lengths
                    {
                        toReturn = decodeOrder();
                        if (toReturn != null)
                        {
                            reset();
                            return toReturn;
                        }
                        return null;
                    }
                    break;
                }
                case 'a': // Acknowledge
                {
                    if (nextByte == '\0') {
                        toReturn = decodeAck();
                        if (toReturn != null) {
                            reset();
                        }
                    }
                    return toReturn;
                }
            }
        }
        return null;
    }

    private Packet decodeAck()
    {
        String ackNumberString = new String(buffer, 2, index - 2);
        return new AckPacket(opCode, operationCode, Integer.valueOf(ackNumberString));
    }

    private Packet decodeOrder() // received all lengths
    {
        switch (partsReceived)
        {
            case 0: // now receiving the JSON File Name
            {
                if(index-beginningOfFileIndex==nameLength)
                {
                    partsReceived++;
                    this.jsonFileName = new String(buffer, beginningOfFileIndex, nameLength);
                    beginningOfFileIndex = index;
                }
                break;
            }
            case 1: // now receiving the JSON File
            {
                if(index-beginningOfFileIndex==fileLength)
                {
                    try {
                        File file = new File(ServerFilesFolder + this.jsonFileName);
                        boolean fileCreationSuccess = file.createNewFile();
                        if(!fileCreationSuccess)
                            throw new RuntimeException("JSON file creation FAILED");
                        FileOutputStream stream = new FileOutputStream(file);
                        stream.write(buffer, beginningOfFileIndex, fileLength);
                        stream.flush();
                        stream.close();
                        jsonFile = file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    partsReceived++;
                    beginningOfFileIndex = index;
                }
                break;
            }
            case 2:  // now receiving the Zipped Folder
            {
                if(index-beginningOfFileIndex==zipLength)
                {
                    try {
                        String zipName;
                        int fileExtPos = this.jsonFileName.lastIndexOf(".");
                        zipName= this.jsonFileName.substring(0, fileExtPos);
                        zipName = zipName + ".zip";
                        File file = new File(ServerFilesFolder + zipName);
                        boolean createFileSucceed = file.createNewFile();
                        if (!createFileSucceed)
                            throw new RuntimeException("ServerEncDec >> decodeOrder >> create new fie FAILED");
                        FileOutputStream stream = new FileOutputStream(file);
                        stream.write(buffer, beginningOfFileIndex, zipLength);
                        stream.flush();
                        stream.close();
                        this.zippedFolder = file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new OrderPacket(this.jsonFileName, this.jsonFile, this.zippedFolder);
                }
                break;
            }
        }
        return null;
    }

    private Packet decodeLog() {
        String userName = new String(buffer, 2, index - 2);
        return new LogInOutPacket(opCode, operationCode, userName);
    }

    private Packet decodeError() {
        String errorMessage = new String(buffer, 2, index - 2);
        return new LogInOutPacket(opCode, operationCode, errorMessage);
    }

    private void reset() {
        this.index = 0;
    }

    /**
     * increases buffer's size by BUFFER_SIZE * 10
     * buffer will not return to original first size after this function
     *
     * @param buf the buf to increase and copy
     * @return new buffer with the same bytes, with the length required
     */
    private byte[] increaseBufferSize(byte[] buf) {
        byte[] newBuf = new byte[buf.length + BUFFER_SIZE * 10];
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

    private static int fromByteArrayToInt(byte[] bytes)
    {
        return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
    }
}
