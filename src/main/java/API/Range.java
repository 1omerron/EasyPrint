package API;

/**
 * Created by 1omer on 23/03/2017.
 */
public abstract class Range
{
    private int firstPage;
    private int lastPage;
    private int printQuantity;

    /**
     * @return the number of pages to print
     */
    public int getNumberOfPages()
    {
        return lastPage - firstPage + 1;
    }
}
