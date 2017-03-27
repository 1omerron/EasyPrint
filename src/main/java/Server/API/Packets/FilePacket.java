package Server.API.Packets;

import Client.RequestOrganization.FileInfo;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by 1omer on 27/03/2017.
 *
 * Packet containing a single file
 * Code : f
 *
 * Operation Field in Order Packet represents:
 * '0' - file name
 * '1' - the file's bytes
 *
 * byte[] data = the bytes representing the information to send (File's data bytes / File's name)
 */
public class FilePacket extends Packet
{
    FilePacket(FileInfo fileInfo) throws IOException
    {
        super('d', '1', Files.readAllBytes(fileInfo.getFile().toPath()));
    }

    FilePacket(String fileName) throws IOException
    {
        super('d', '0', fileName.getBytes());
    }
}
