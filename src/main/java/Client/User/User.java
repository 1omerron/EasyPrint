package Client.User;

import Client.RequestOrganization.OrderInstruction;

import java.util.HashMap;

/**
 * Created by nimrod on 29/03/2017.
 */
public class User
{
    private String userId;
    private HashMap<String,OrderInstruction> orderInstructions;

    public User(HashMap<String, OrderInstruction> orderInstructions)
    {
        userId = SingeltonIdMonitor.getInstance().getAvailableId();
        this.orderInstructions = orderInstructions;
    }

    public void setOrderInstructions(HashMap<String, OrderInstruction> orderInstructions) {
        this.orderInstructions = orderInstructions;
    }

    public String getUserId() {

        return userId;
    }

    public HashMap<String, OrderInstruction> getOrderInstructions() {
        return orderInstructions;
    }
}
