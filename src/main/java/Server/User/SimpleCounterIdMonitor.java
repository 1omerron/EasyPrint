package Server.User;

import Client.API.IdMonitor;

/**
 * Created by 1omer on 23/03/2017.
 */
public class SimpleCounterIdMonitor implements IdMonitor<Integer>
{
    private Integer counter;

    public SimpleCounterIdMonitor()
    {
        counter = 0;
    }

    /**
     * @return the next ID
     */
    @Override
    public Integer getId()
    {
        return counter++;
    }

    /**
     * restores ID to the available IDs (from a 'released' id)
     * @param returnedId the ID to be returned to the available IDs
     */
    @Override
    public void returnId(Integer returnedId)
    {
        // Do nothing
    }
}
