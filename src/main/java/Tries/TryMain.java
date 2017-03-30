package Tries;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TryMain
{
    public static void main(String[] args)
    {
        String s = "jsonfile.txt";
        int fileExtPos = s.lastIndexOf(".");
        if (fileExtPos >= 0 )
            s= s.substring(0, fileExtPos);
        System.out.println(s);
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
