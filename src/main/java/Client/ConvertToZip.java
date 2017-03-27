package Client;

/**
 * Created by nimrod on 27/03/2017.
 */

import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ConvertToZip {

    public void zipFiles(OrderInstruction order)
    {
        LinkedList<FileInstruction> files = order.getInstructionsList();
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(Client.pathClient+File.separator+order.getOrderId()+".zip");
            zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
            for(FileInstruction file : files){
                File input = new File(file.getFile().getFile().getPath());
                fis = new FileInputStream(input);
                ZipEntry ze = new ZipEntry(input.getName());
                System.out.println("Zipping the file: "+input.getName());
                zipOut.putNextEntry(ze);
                byte[] tmp = new byte[4*1024];
                int size = 0;
                while((size = fis.read(tmp)) != -1){
                    zipOut.write(tmp, 0, size);
                }
                zipOut.flush();
                fis.close();
            }
            zipOut.close();
            System.out.println("Done... Zipped the files...");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(fos != null) fos.close();
            } catch(Exception ex){

            }
        }
    }
}