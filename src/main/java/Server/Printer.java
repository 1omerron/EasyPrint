package Server;

import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import Client.RequestOrganization.PageRangeInstruction;
import Server.API.Connections;
import Server.API.Packets.Packet;
import com.sun.org.apache.xerces.internal.dom.RangeImpl;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.Sides;
import java.util.LinkedList;

/**
 * Created by chen on 28-Mar-17.
 */
public class Printer {
    private int connectionId;
    private Connections<Packet> connections;



    public void sendOrderInstruction(OrderInstruction order){
        for (FileInstruction instruction : order.getInstructionsList()
                ) {
            printFile(instruction);
        }
    }

    /**
     * prints a file according to the attributes given in the
     * @param instruction
     */
    private void printFile(FileInstruction instruction) {
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new Copies(1));
        aset.add(MediaSize.ISO.A4);
        aset.add(Sides.DUPLEX);
        if(instruction.getRanges() != null) {
            aset.add(new PageRanges(instruction.getRanges()));
        }
    }


    /**
     * this method returns the ranges of a file instruction in the appropriate syntax
     * according to the string form (see {@link javax.print.attribute.SetOfIntegerSyntax})
     * @param ranges
     * @return  the string representing the ranges
     */
    private String buildRangesAttribute(LinkedList<PageRangeInstruction> ranges) {
        String ans = "";
        for (PageRangeInstruction range : ranges
                ) {
            ans = String.format("%f:%d,",range.getFirstPage(), range.getLastPage());
        }
        return ans.substring(0,ans.length()-1);     //without the last comma
    }

    /**
     * method of the gui - checks if the user entered a valid string of {@link PageRanges}
     */
    private static boolean isValidRangeInput(String input){
        try{
            PageRanges ranges = new PageRanges(input);
        }
        catch (IllegalArgumentException iae){
            return false;
        }
        catch (NullPointerException e){
            System.out.println("null string of range");
            return false;
        }

        return true;
    }


}