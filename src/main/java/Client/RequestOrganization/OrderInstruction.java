package Client.RequestOrganization;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by 1omer on 23/03/2017.
 */
public class OrderInstruction
{
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
}
