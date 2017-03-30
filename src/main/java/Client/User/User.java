package Client.User;

import Client.Client;
import Client.RequestOrganization.OrderInstruction;

import Client.JsonHandler;
import java.io.File;
import java.util.HashMap;

/**
 * Created by nimrod on 29/03/2017.
 */
public class User
{
    private String userId;
    private HashMap<String,OrderInstruction> orderInstructions;

    public User()
    {
        String jsonName = "";
        User oldUser = null;
        boolean exist = false;
        File directory = new File(Client.pathClient);
        String[] files = directory.list();
        for(String s : files)
        {
            if(s.endsWith("User.json"))
            {
                oldUser = JsonHandler.fromJson(s);
                exist=true;
            }
        }
        if(oldUser == null)//old user
        {

        }
    }

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
