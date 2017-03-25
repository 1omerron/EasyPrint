package Client.API;

/**
 * Created by 1omer on 23/03/2017.
 */
public interface IdMonitor<T>
{
    /**
     * @return the next ID
     */
    T getId();

    /**
     * restores ID to the available IDs (from a 'released' id)
     * @param returnedId the ID to be returned to the available IDs
     */
    void returnId(T returnedId);
}
