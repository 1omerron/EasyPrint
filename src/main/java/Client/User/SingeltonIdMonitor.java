package Client.User;

/**
 * Created by nimrod on 29/03/2017.
 */
public class SingeltonIdMonitor
{
    private static SingeltonIdMonitor idMonitor = new SingeltonIdMonitor( );
    private Integer counter;
    private String id;

    /** A private Constructor prevents any other
     * class from instantiating.
     */
    private SingeltonIdMonitor()
    {
        counter = 0;
        id = counter.toString();
    }

    /** Static 'instance' method */
    public static SingeltonIdMonitor getInstance( ) {
        return idMonitor;
    }

    /** Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
        System.out.println("demoMethod for singleton");
    }

    /**
     * @return available userId
     */
    public String getAvailableId()
    {
        String availableId = id;
        counter++;
        id  = counter.toString();
        return availableId;
    }
}
