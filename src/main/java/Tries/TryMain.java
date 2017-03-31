package Tries;


import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by 1omer on 29/03/2017.
 */
public class TryMain
{
    public TryMain()
    {
        File newFile = new File("src/main/java/Server/ServerFiles/text.txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        TryMain t = new TryMain();
    }
}
