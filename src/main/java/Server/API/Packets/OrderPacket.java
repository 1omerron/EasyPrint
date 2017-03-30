package Server.API.Packets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : o (the letter o, not zero)
 *
 * Operation Field in Order Packet represents:
 * '0' - all order
 *
 * byte[] data = the bytes representing the information to send. Structure:
 * json file name length (4 bytes) , json file length (4 bytes), zipped folder length (4 bytes), json file name, json file,  zipped folder
 */
public class OrderPacket extends Packet
{
    // Files

    private String jsonFileName;
    private File jsonFile;
    private File zippedFolder;

    // length

    private int jsonFileNameLength;
    private int jsonFileLength;
    private int zippedFolderLength;

    public OrderPacket(String jsonFileName, File jsonFile, File zippedFolder)
    {
        super('o','0', "".getBytes());
        byte[] jsonFileBytes = new byte[0];
        byte[] zippedFolderBytes = new byte[0];
        try {
            zippedFolderBytes = Files.readAllBytes(zippedFolder.toPath());
            jsonFileBytes = Files.readAllBytes(jsonFile.toPath());
            this.jsonFileName = jsonFileName;
            this.jsonFile = jsonFile;
            this.zippedFolder = zippedFolder;
            this.jsonFileNameLength = jsonFileName.length();
            this.jsonFileLength = (int)jsonFile.length();
            this.zippedFolderLength = (int)zippedFolder.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] jsonFileNameBytes = jsonFileName.getBytes();
        byte[] destArray = new byte[jsonFileNameLength+jsonFileLength+zippedFolderLength+12];
        int index=0;

        for(byte lengthByte : (toByteArray(jsonFileNameLength)))
        {
            destArray[index++] = lengthByte;
        }

        for(byte lengthByte : (toByteArray(jsonFileLength)))
        {
            destArray[index++] = lengthByte;
        }

        for(byte lengthByte : (toByteArray(zippedFolderLength)))
        {
            destArray[index++] = lengthByte;
        }

        for (byte jsonFileNameByte : jsonFileNameBytes)
        {
            destArray[index++] = jsonFileNameByte;
        }

        for (byte jsonFileByte : jsonFileBytes) {
            destArray[index++] = jsonFileByte;
        }

        for (byte zippedFolderByte : zippedFolderBytes) {
            destArray[index++] = zippedFolderByte;
        }

        setData(destArray);
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    private static byte[] toByteArray(int value)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((value >> 24) & 0xFF);
        bytes[1] = (byte) ((value >> 16) & 0xFF);
        bytes[2] = (byte) ((value >> 8) & 0xFF);
        bytes[3] = (byte) (value & 0xFF);
        return bytes;
    }
}