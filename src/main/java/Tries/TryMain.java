package Tries;

import java.io.File;
import java.io.IOException;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TryMain
{
    public static void main(String[] args)
    {
        File file = new File("C:\\Users\\1omer\\Desktop\\ServerFiles\\newfile.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("opened");
    }

    public void convertIntToByteArrayAndBack()
    {
        int num = 5;
        String intValue = String.valueOf(num)+'\0';
        byte[] numInBytes = intValue.getBytes();
        String numberIsString = new String(numInBytes);
        int number = Integer.parseInt(numberIsString);
        System.out.println(number);
    }
}
