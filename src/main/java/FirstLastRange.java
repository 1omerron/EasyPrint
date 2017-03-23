import API.Range;

/**
 * Created by 1omer on 23/03/2017.
 */
public class FirstLastRange extends Range
{
    public FirstLastRange(int firstPage, int lastPage, int quantity)
    {
        for(int i=firstPage; i<=lastPage; i++)
        {
            addPage(i);
        }
        this.printQuantity = quantity;
    }
}
