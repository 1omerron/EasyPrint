package Client.API.Packets;

import java.io.File;

/**
 * Created by 1omer on 26/03/2017.
 *
 * Code : o (the letter o, not zero)
 *
 * Operation Field in Order Packet represents:
 * '0' - all order
 *
 * byte[] data = the bytes representing the information to send (JSON File / Json file Name)
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
        super('o','0', (jsonFileName.toString()+'\0'+jsonFile+'\0'+zippedFolder.toString().getBytes()+'\0').getBytes());
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
