package API;

import java.util.HashSet;

/**
 * omer is gay on 23/03/2017.
 */
public abstract class Range
{
    private HashSet<Integer> pages;
    protected int printQuantity;

    protected Range()
    {
        pages = new HashSet<>();
        printQuantity = 0;
    }

    /**
     * @return the number of pages to print
     */
    public int getNumberOfPages()
    {
        return pages.size();
    }

    /**
     * adds a new page to the pages to print set
     * @param pageNum page number
     * @return true if the page was succesfully added, and false otherwise (if the page already included)
     */
    protected boolean addPage(int pageNum)
    {
        return pages.add(pageNum);
    }
}
