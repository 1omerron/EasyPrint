package RequestOrganization;

import API.Range;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by 1omer on 23/03/2017.
 */
public class PrintRequest
{
    private PFile file;
    private LinkedList<Range> ranges;

    /**
     * Constructs new PrintRequest with file only
     * @param file PFile from an uploaded file by the user
     */
    public PrintRequest(PFile file)
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
     * Constructs new PrintRequest with file, and a list of ranges to print
     * @param file PFile from an uploaded file by the user
     * @param rangesToAdd Collection of ranges (printing instructions) which were set by the user
     */
    public PrintRequest(PFile file, Collection<Range> rangesToAdd)
    {
        this.file=file;
        ranges = new LinkedList<>();
        for(Range range : rangesToAdd)
            this.ranges.add(range);
    }
}
