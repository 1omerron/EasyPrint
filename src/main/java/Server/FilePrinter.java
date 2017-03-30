package Server;

/**
 * Created by חן on 26-Mar-17.
 */
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.*;


public class FilePrinter {
    public static void main(String[] args) {
        // Input the file
        FileInputStream textstream = null;
        try {
            textstream = new FileInputStream("C:\\Users\\חן\\IdeaProjects\\EasyPrint\\test.txt");
        } catch (FileNotFoundException ffne) {
            System.out.println("exception 1");
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
        aset.add(new Copies(1));
        aset.add(MediaSize.ISO.A4);
        //aset.add(Sides.DUPLEX);
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

            DocPrintJob job = services[0].createPrintJob();
            PrintJobWatcher pjw = new PrintJobWatcher(job);

            try {
                job.print(myDoc, aset);
                pjw.waitForDone();
                textstream.close();
            } catch (PrintException pe) {
                System.out.println("exception 2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStream ff = new ByteArrayInputStream("\f".getBytes());
        Doc docff = new SimpleDoc(ff, myFormat, null);
        DocPrintJob jobff = services[0].createPrintJob();
        try {
            jobff.print(docff, null);
        } catch (PrintException e) {
            e.printStackTrace();
        }
    }
}

class PrintJobWatcher {
    boolean done = false;

    PrintJobWatcher(DocPrintJob job) {
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printJobCanceled(PrintJobEvent pje) {
                allDone();
            }

            public void printJobCompleted(PrintJobEvent pje) {
                allDone();
            }

            public void printJobFailed(PrintJobEvent pje) {
                allDone();
            }

            public void printJobNoMoreEvents(PrintJobEvent pje) {
                allDone();
            }

            void allDone() {
                synchronized (PrintJobWatcher.this) {
                    done = true;
                    System.out.println("Printing done ...");
                    PrintJobWatcher.this.notify();
                }
            }
        });
    }

    public synchronized void waitForDone() {
        try {
            while (!done) {
                wait();
            }
        } catch (InterruptedException e) {
        }
    }
}
