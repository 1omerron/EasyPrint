package Client.RequestOrganization;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FileInstruction
{
    private FileInfo file;
    private String ranges;

    public FileInfo getFile() {
        return file;
    }

    /**
     * Constructs new FileInstructionS with file only
     * @param file FileInfo from an uploaded file by the user
     */
    public FileInstruction(FileInfo file) { this.file = file;}

    /**
     * Constructs new FileInstructionS with file, and a list of ranges to print
     * @param file FileInfo from an uploaded file by the user
     * @param rangesToAdd Collection of ranges (printing instructions) which were set by the user
     */
    public FileInstruction(FileInfo file, String rangesToAdd)
    {
        this.file=file;
        this.ranges = rangesToAdd;
    }

    public void setFile(FileInfo file) {
        this.file = file;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

/*    public FileInstruction(FileInfo file, PageRangeInstruction rangesToAdd)
    {
        this.file=file;
        ranges = new LinkedList<>();
        ranges.addLast(rangesToAdd);
    }*/

    public void addPageRangeInstruction(String rangeToAdd)
    {
        ranges += ", " + rangeToAdd;
    }

    public String getRanges() {
        return ranges;
    }
}
