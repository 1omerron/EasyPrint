package RequestOrganization;

import API.Range;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FileInstruction
{
    private FileInfo file;
    private LinkedList<Range> ranges;

    /**
     * Constructs new FileInstruction with file only
     * @param file FileInfo from an uploaded file by the user
     */
    public FileInstruction(FileInfo file)
    {
        ranges = new LinkedList<>();
        this.file = file;
        ranges.add(new Range() {
            /**
             * @return the number of pages to print
             */
            @Override
            public int getNumberOfPages() {
                return super.getNumberOfPages();
            }
        });
    }

    /**
     * Constructs new FileInstruction with file, and a list of ranges to print
     * @param file FileInfo from an uploaded file by the user
     * @param rangesToAdd Collection of ranges (printing instructions) which were set by the user
     */
    public FileInstruction(FileInfo file, Collection<Range> rangesToAdd)
    {
        this.file=file;
        ranges = new LinkedList<>();
        for(Range range : rangesToAdd)
            this.ranges.add(range);
    }
}
