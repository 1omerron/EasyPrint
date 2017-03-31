package Client.User;

import Client.RequestOrganization.OrderInstruction;
import java.util.HashMap;

/**
 * Created by nimrod on 29/03/2017.
 */
public class User
{
    private String userId;

    public long getIdCounter() {
        return idCounter;
    }

    private long idCounter;
    private HashMap<String,OrderInstruction> orderInstructions;
    private HashMap<String,String> orderInstructionsById;


    public User()
    {
        userId = "1234678";//todo change to stdin its only for tests.
        orderInstructions = new HashMap<>();
    }

    public User(HashMap<String, OrderInstruction> orderInstructions)
    {
        userId = SingeltonIdMonitor.getInstance().getAvailableId();
        this.orderInstructions = orderInstructions;
    }
    //for test
    public User(String id){
        userId = id;
        this.orderInstructions = new HashMap<>();
        this.orderInstructionsById = new HashMap<>();
        this.idCounter = 0; //TODO take care  that every valid ctor initialize the counter
    }
    public void setOrderInstructions(HashMap<String, OrderInstruction> orderInstructions) {
        this.orderInstructions = orderInstructions;
    }

    public String getUserId() {

        return userId;
    }

    public HashMap<String,String > getOrderInstructionsById(){return orderInstructionsById;}
    public HashMap<String, OrderInstruction> getOrderInstructions() {
        return orderInstructions;
    }
    public void addOrderInstruction(OrderInstruction instruction){
        orderInstructionsById.put( instruction.getOrderName(), instruction.getOrderId() );
        orderInstructions.put( instruction.getOrderId(),instruction );
    }

    public void toPrint()
    {
        for(String orderName : orderInstructions.keySet() )
        {
            System.out.println(orderName);
        }
    }
}
