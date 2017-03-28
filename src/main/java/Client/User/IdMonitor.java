package Client.User;


/**
 * Created by 1omer on 23/03/2017.
 */
public class IdMonitor
{
    private Integer counter;
    private String id;

    public IdMonitor()
    {
        counter = 0;
        id = counter.toString();
    }

    /**
     *
     * @return available userId
     */
    public String getAvailableId()
    {
        String availableId = id;
        counter++;
        id  = counter.toString();
        return availableId;
    }

    /**
     * restores ID to the available IDs (from a 'released' id)
     * @param returnedId the ID to be returned to the available IDs
     */

    public void returnId(Integer returnedId)
    {
        // Do nothing
    }
}
