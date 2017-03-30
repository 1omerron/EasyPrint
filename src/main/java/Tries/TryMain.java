package Tries;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TryMain
{
    public TryMain() throws Exception
    {
        URL location = getClass().getClassLoader().getResource("ServerFiles/bla.txt");
        URI uri = location.toURI();
        File file = new File(uri);
    }

    public static int fromByteArray(byte[] bytes)
    {
        return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
    }

    public static byte[] toByteArray(int value)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((value >> 24) & 0xFF);
        bytes[1] = (byte) ((value >> 16) & 0xFF);
        bytes[2] = (byte) ((value >> 8) & 0xFF);
        bytes[3] = (byte) (value & 0xFF);
        return bytes;
    }

}
