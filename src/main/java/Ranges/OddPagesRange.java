package Ranges;

import API.Range;

/**
 * Created by 1omer on 23/03/2017.
 * Range Type : printing only ODD numbered Pages in the given range (firstPage, lastPage)
 */
public class OddPagesRange extends Range
{
    public OddPagesRange(int firstPage, int lastPage, int quantity)
    {
        for(int i=firstPage; i<=lastPage; i++)
        {
            if(i%2==1)
                addPage(i);
        }
        this.printQuantity = quantity;
    }
}
