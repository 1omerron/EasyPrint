package Server;

/**
 * Created by nimrod on 27/03/2017.
 */

import Client.RequestOrganization.OrderInstruction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip
{

    /**
     * Unzip it
     */
    public static void unZipIt(OrderInstruction order){
        String pathServer = FromJson.pathServer;//todo check what the path to the files in the server
        new File(pathServer+File.separator+order.getOrderId()).mkdir();
        String zipFile = pathServer+File.separator+order.getOrderId()+".zip";
        String outputFolder = pathServer+File.separator+order.getOrderId();
        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File(pathServer);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}