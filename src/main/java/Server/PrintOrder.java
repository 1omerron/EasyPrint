package Server;

import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PrintOrder {


    private static void print(String pathToFile) {
        System.out.println("start print: "+pathToFile);
        try {
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            desktop.print(new File(pathToFile));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void printOrder(String jsonName)
    {
        System.out.println("try to open json: "+jsonName);
        OrderInstruction order = FromJson.fromJson(jsonName);
        String path = FromJson.pathServer;
        UnZip.unZipIt(order);
        for(FileInstruction fileIns : order.getInstructionsList())
        {
            String fileName = fileIns.getFile().getFile().getName();
            System.out.println(fileName);
            String filePath = path+"\\"+fileName;
            print(filePath);
        }


    }
}
