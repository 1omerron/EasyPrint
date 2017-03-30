package Client.API.Packets;

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
 * json file name, '\0', json file, '\0', zipped folder, '\0'
 */
public class OrderPacket extends Packet
{
    private String jsonFileName;
    private File jsonFile;
    private File zippedFolder;

    // Constructor with JSON Object / Zipped File
    public OrderPacket(String jsonFileName, File jsonFile, File zippedFolder)
    {
        // TODO added /0
        super('o','0', "".getBytes());
        byte[] jsonFileBytes = new byte[0];
        byte[] zippedFolderBytes = new byte[0];
        try {
            zippedFolderBytes = Files.readAllBytes(zippedFolder.toPath());
            jsonFileBytes = Files.readAllBytes(jsonFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] jsonFileNameBytes = jsonFileName.getBytes();
        byte[] destArray = new byte[jsonFileBytes.length+zippedFolderBytes.length+jsonFileNameBytes.length+3];
        int index=0;
        for (byte jsonFileNameByte : jsonFileNameBytes) {
            destArray[index++] = jsonFileNameByte;
        }
        destArray[index++]='\0';
        for (byte jsonFileByte : jsonFileBytes) {
            destArray[index++] = jsonFileByte;
        }
        destArray[index++]='\0';
        for (byte zippedFolderByte : zippedFolderBytes) {
            destArray[index++] = zippedFolderByte;
        }
        destArray[index]='\0';
        setData(destArray);
        this.jsonFileName = jsonFileName;
        this.jsonFile = jsonFile;
        this.zippedFolder = zippedFolder;
    }

    public File getZippedFolder() {
        return zippedFolder;
    }

    public void setZippedFolder(File zippedFolder) {
        this.zippedFolder = zippedFolder;
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }
}
