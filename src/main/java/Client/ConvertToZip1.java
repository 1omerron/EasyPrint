package Client;

/**
 * Created by nimrod on 27/03/2017.
 * Create Zip File From Multiple Files using ZipOutputStream Example
   This Java example shows how create zip file containing multiple files
   using Java ZipOutputStream class.
 */
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ConvertToZip1 {

    public void zipFiles(OrderInstruction order)
    {
        try
        {
            String orderName = order.getOrderId()+".zip";
            String zipFile = Client.pathClient+ File.separator+orderName;
            Object[] sourceFiles = order.getInstructionsList().toArray();
            //create byte buffer
            byte[] buffer = new byte[1024];
             /* To create a zip file, use
             * ZipOutputStream(OutputStream out)
              constructor of ZipOutputStream class.*/

            //create object of FileOutputStream
            FileOutputStream fout = new FileOutputStream(zipFile);

            //create object of ZipOutputStream from FileOutputStream
            ZipOutputStream zout = new ZipOutputStream(fout);

            for(int i=0; i < sourceFiles.length; i++)
            {

                System.out.println("Adding " + ((FileInstruction)sourceFiles[i]).getFile().getFileName());
                //create object of FileInputStream for source file
                FileInputStream fin = new FileInputStream(((FileInstruction)sourceFiles[i]).getFile().getFile().getPath());

                 /* To begin writing ZipEntry in the zip file, use
                 *
                 * void putNextEntry(ZipEntry entry)
                 * method of ZipOutputStream class.
                 *
                 * This method begins writing a new Zip entry to
                 * the zip file and positions the stream to the start
                 * of the entry data.*/

                zout.putNextEntry(new ZipEntry(((FileInstruction)sourceFiles[i]).getFile().getFile().getPath()));
                int length;
                while((length = fin.read(buffer)) > 0)
                {
                    zout.write(buffer, 0, length);
                }

                /*
                 * After writing the file to ZipOutputStream, use
                 *
                 * void closeEntry() method of ZipOutputStream class to
                 * close the current entry and position the stream to
                 * write the next entry.
                 */
                zout.closeEntry();

                //close the InputStream
                fin.close();
            }
            //close the ZipOutputStream
            zout.close();
            System.out.println("Zip file has been created!");
        }
        catch(IOException ioe)
        {
            System.out.println("IOException :" + ioe);
        }

    }
}
