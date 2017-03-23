package Ranges;

import API.Range;

/**
 * Created by 1omer on 23/03/2017.
 */
public class EvenPagesRange extends Range
{
    public EvenPagesRange(int firstPage, int lastPage, int quantity)
    {
        for(int i=firstPage; i<=lastPage; i++)
        {
            if(i%2==0)
                addPage(i);
        }
        this.printQuantity = quantity;
    }
}
