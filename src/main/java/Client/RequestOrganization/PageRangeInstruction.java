package Client.RequestOrganization;

/**
 * created by 1omerron on 23/03/2017.
 */
public class PageRangeInstruction
{
    private int firstPage;
    private int lastPage;
    private int printQuantity;

    public PageRangeInstruction(int firstPage, int lastPage, int printQuantity)
    {
        this.lastPage = lastPage;
        this.firstPage = firstPage;
        this.printQuantity = printQuantity;
    }

    /**
     * @return the number of pages to print
     */
    public int getNumberOfPages()
    {
        return lastPage - firstPage + 1;
    }

    /**
     * sets a new first page
     * @param firstPage first page to set
     */
    public void setFirstPage(int firstPage)
    {
        this.firstPage = firstPage;
    }

    /**
     * sets a new last page
     * @param lastPage last page to set
     */
    public void setLastPage(int lastPage)
    {
        this.lastPage = lastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }
}
