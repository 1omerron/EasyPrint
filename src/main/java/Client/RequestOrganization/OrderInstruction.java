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
     * Constructs new OrderInstruction from an existing FileInstruction Collection
     * @param fileInstructions an existing Collection of FileInstruction
     */
    public OrderInstruction(Collection<FileInstruction> fileInstructions)
    {
        for(FileInstruction instruction : fileInstructions)
        {
            this.instructionsList.add(instruction);
        }
    }

    /**
     * Constructs new OrderInstruction with a single FileInstruction (First File)
     * @param fileInstruction
     */
    public OrderInstruction(FileInstruction fileInstruction)
    {
        this.instructionsList = new LinkedList<>();
        this.instructionsList.add(fileInstruction);
    }
}
