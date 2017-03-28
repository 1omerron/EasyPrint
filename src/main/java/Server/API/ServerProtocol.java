package Server.API;

import Client.API.PageRangeInstruction;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import Server.API.Connections;
import Server.API.Packets.Packet;
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
public class ServerProtocol {
    private int connectionId;
    private Connections<Packet> connections;


    /**
     * starts the protocol
     * @param connectionId - the id of the specific client connection
     * @param connections - TODO check if needed as we don't need the broadcast feature
     */
    public void start(int connectionId, Connections<Packet> connections) {
        this.connectionId = connectionId;
        this.connections = connections;
    }

    /**
     * process the given message
     *
     * @param msg the received message
     * @return the response to send or null if no response is expected by the client
     */
    public Packet process(Packet msg) {
        return null;
    }

    /**
     * @return true if the connection should be terminated
     * //TODO when should it be..?
     */
    public boolean shouldTerminate() {
        return false;

    }

    private void sendOrderInstruction(OrderInstruction order){
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
        //aset.add(new Copies(5));
        //aset.add(MediaSize.ISO.A4);
        //aset.add(Sides.DUPLEX);
        if(!instruction.getRanges().isEmpty()) {
            PageRanges ranges = new PageRanges(buildRangesAttribute(instruction.getRanges()));
            aset.add(ranges);
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
            ans += String.format("%f:%d,",range.getFirstPage(), range.getLastPage());
        }
        return ans.substring(0,ans.length()-1);     //without the last comma
    }


}