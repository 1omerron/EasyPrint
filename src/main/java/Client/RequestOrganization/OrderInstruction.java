package Client.RequestOrganization;

import Client.User.SingeltonIdMonitor;
import Client.User.User;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by 1omer on 23/03/2017.
 */
public class OrderInstruction
{
    private String orderId;



    private String orderName;

    public OrderInstruction(User user) {

    }

    public String getOrderId() {
        return orderId;
    }


    public OrderInstruction(LinkedList<FileInstruction> instructionsList,String userId) {
        this.orderId = userId;

        this.instructionsList = instructionsList;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public static void setPathClient(String pathClient) {
        OrderInstruction.pathClient = pathClient;
    }

    public void setInstructionsList(LinkedList<FileInstruction> instructionsList) {
        this.instructionsList = instructionsList;
    }

    public static String getPathClient() {
        return pathClient;
    }

    public static String pathClient;
    private LinkedList<FileInstruction> instructionsList;

    /**
     * Constructs new EMPTY OrderInstruction
     */
    public OrderInstruction()
    {
        instructionsList = new LinkedList<>();
    }

    /**
     * Constructs new OrderInstruction from an existing FileInstructionS Collection
     * @param fileInstructions an existing Collection of FileInstructionS
     */
    public OrderInstruction(Collection<FileInstruction> fileInstructions)
    {
        instructionsList = new LinkedList<>();
        for(FileInstruction instruction : fileInstructions)
        {
            this.instructionsList.add(instruction);
        }
        //todo for testing
        orderId = "order";
    }

    /**
     * Constructs new OrderInstruction with a single FileInstructionS (First File)
     * @param fileInstruction
     */
    public OrderInstruction(FileInstruction fileInstruction) {
        this.instructionsList = new LinkedList<>();
        this.instructionsList.add(fileInstruction);
    }

    public LinkedList<FileInstruction> getInstructionsList() {
        return instructionsList;
    }

    /**
     * add new file instruction to this order
     */
    public void addFileInstruction(FileInstruction file)
    {
        instructionsList.addLast(file);
    }
}
