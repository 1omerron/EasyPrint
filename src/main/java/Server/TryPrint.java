package Server;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TryPrint {
    public static void main(String args[]) {

        /**
         * @param args
         */
            String fileName = "C:\\Users\\nimrod\\Desktop\\easyPrint\\nimrod.txt";
            // Open the file
            InputStream in = null;
            try {
                in = new FileInputStream(fileName);
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // Figure out what type of file we're printing
            DocFlavor myFormat = getFlavorFromFilename(fileName);
            // Create a Doc
            Doc myDoc = new SimpleDoc(in, myFormat, null);
            // Build a set of attributes
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(OrientationRequested.LANDSCAPE);
            aset.add(new Copies(2));
            aset.add(Sides.DUPLEX);
            aset.add(MediaSizeName.NA_LETTER);
            aset.add(new NumberUp(2));
            aset.add(Finishings.STAPLE);
            // discover the printers that can print the format according to the
            // instructions in the attribute set
            PrintService[] services = PrintServiceLookup.lookupPrintServices(
                    myFormat, null);

            // Create a print job from one of the print services
            if (services.length > 0) {
                System.out.println("The print sent to>>>" + services[0].getName());
                DocPrintJob job = services[0].createPrintJob();

                // Monitor the print job with a listener
                job.addPrintJobListener(new PrintJobAdapter() {
                    public void printDataTransferCompleted(PrintJobEvent e) {
                        System.out.println("Data transfer completed!");
                    }

                    public void printJobNoMoreEvents(PrintJobEvent e) {
                        System.out.println("No more events!");
                    }

                    public void printJobRequiresAttention(PrintJobEvent e) {
                        System.out.println("Requires Attention!");
                    }

                    public void printJobFailed(PrintJobEvent e) {
                        System.out.println("Print Job Failed!");
                    }

                    public void printJobCompleted(PrintJobEvent e) {
                        System.out.println("Print Job Completed!");
                    }

                    public void printJobCanceled(PrintJobEvent e) {
                        System.out.println("Print Job Cancelled!");
                    }
                });

                try {
                    job.print(myDoc, aset);
                } catch (PrintException pe) {
                    pe.printStackTrace();
                }
                System.out.println("The print job ........");
            }
        }

        // A utility method to return a DocFlavor object matching the
        // extension of the filename.
    public static DocFlavor getFlavorFromFilename(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1);
        extension = extension.toLowerCase();
        if (extension.equals("gif"))
            return DocFlavor.INPUT_STREAM.GIF;
        else if (extension.equals("jpeg"))
            return DocFlavor.INPUT_STREAM.JPEG;
        else if (extension.equals("jpg"))
            return DocFlavor.INPUT_STREAM.JPEG;
        else if (extension.equals("png"))
            return DocFlavor.INPUT_STREAM.PNG;
        else if (extension.equals("ps"))
            return DocFlavor.INPUT_STREAM.POSTSCRIPT;
        else if (extension.equals("txt"))
            return DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
            // Fallback: try to determine flavor from file content
        else
            return DocFlavor.INPUT_STREAM.AUTOSENSE;
    }
}