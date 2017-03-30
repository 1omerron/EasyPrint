package Server;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by nimrod on 30/03/2017.
 */
public class TryPrint
{
    public static void main(String[] args)
    {
        // Input the file
        FileInputStream textStream=null;
        try {
            textStream = new FileInputStream("file.TXT");
        } catch (FileNotFoundException ffne) {
        }
        if (textStream == null) {
            return;
        }
        // Set the document type
        DocFlavor myFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // Create a Doc
        Doc myDoc = new SimpleDoc(textStream, myFormat, null);
        // Build a set of attributes
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new Copies(5));
        //aset.add(MediaSize.ISO_A4);
        aset.add(Sides.DUPLEX);
        // discover the printers that can print the format according to the
        // instructions in the attribute set
        PrintService[] services =
                PrintServiceLookup.lookupPrintServices(myFormat, aset);
        // Create a print job from one of the print services
        if (services.length > 0) {
            DocPrintJob job = services[0].createPrintJob();
            try {
                job.print(myDoc, aset);
            } catch (PrintException pe) {}
        }
    }
}
