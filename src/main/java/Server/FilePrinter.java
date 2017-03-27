package Server;

/**
 * Created by חן on 26-Mar-17.
 */
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class FilePrinter {
    public static void main(String[] args) {
        // Input the file
        FileInputStream textstream = null;
        try {
            textstream = new FileInputStream("card.txt");
        } catch (FileNotFoundException ffne) {
        }
        if (textstream == null) {
            System.out.println("is null");
            return;
        }
// Set the document type
        System.out.println("isn't null");

        DocFlavor myFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
// Create a Doc
        Doc myDoc = new SimpleDoc(textstream, myFormat, null);
// Build a set of attributes
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new Copies(5));
        aset.add(MediaSize.ISO.A4);
        aset.add(Sides.DUPLEX);
// discover the printers that can print the format according to the
// instructions in the attribute set
        PrintService[] services =
                PrintServiceLookup.lookupPrintServices(myFormat, null);
// Create a print job from one of the print services
        System.out.println(services.length);
        if (services.length > 0) {
            for (int i = 0; i <services.length ; i++) {
                System.out.println(services[i]);

            }

            DocPrintJob job = services[1].createPrintJob();
            try {
                job.print(myDoc, null);
            } catch (PrintException pe) {}
        }
    }
}

