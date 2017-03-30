package Server;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class tempPrint {
    public static void main(String[] a) {
        try {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            desktop.print(new File("C:\\Users\\nimrod\\Desktop\\easyPrint\\nimrod.docx"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
