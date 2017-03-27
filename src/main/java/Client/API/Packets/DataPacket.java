package Client.API.Packets;

import Client.RequestOrganization.FileInfo;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by 1omer on 27/03/2017.
 *
 * Code : d
 *
 * Operation Field in Order Packet represents:
 * always 0
 *
 * byte[] data = the bytes representing the information to send (JSON File / Json file Name / File to print)
 */
public class DataPacket extends Packet
{
    DataPacket(FileInfo fileInfo) throws IOException
    {
        super('d', '0', Files.readAllBytes(fileInfo.getFile().toPath()));
    }
}
