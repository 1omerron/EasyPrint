package Client;
import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import Client.User.User;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by nimrod on 28/03/2017.
 */
public class UserInterface
{
    private String currentOrderId;
    private User user;

    public UserInterface()
    {
        String jsonName = "";
        User oldUser = null;
        boolean exist = false;
        File directory = new File(Client.pathClient);
        String[] files = directory.list();
        for(int i=0; i<files.length ; i++)
        {
            if(files[i].endsWith("_User.json"))
            {
                oldUser = JsonHandler.fromJson(files[i]);
                System.out.println("user in the system");//todo remove.
                exist=true;
                currentOrderId = null;
                this.user = oldUser;
            }
        }
        if(oldUser == null)// new user
        {
            this.user = new User();
        }
    }
    public void setCurrentOrderId(String currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(HashMap<String, OrderInstruction> orders) {
        this.user.setOrderInstructions(orders);
    }

    public String getCurrentOrderId() {

        return currentOrderId;
    }

    public User getUser() {
        return user;
    }

    public HashMap<String, OrderInstruction> getOrders() {
        return user.getOrderInstructions();
    }

    public UserInterface(User user)
    {
        this.user = user;

        currentOrderId = null;
    }

    /**
     *
     * @return New order's ID
     */
    public String createOrderInstruction(String orderName){
        OrderInstruction temp =  new OrderInstruction( orderName, user );
        user.addOrderInstruction( temp );
        return temp.getOrderId();
    }

    /**
     * @param orderId to set
     */
    public void updateCurrentOrderId(String orderId)
    {
        currentOrderId = orderId;
    }

    /**
     * @param path to the file
     * @return Fileinfo
     */
    public FileInfo createFileInfo(String path)
    {
        FileInfo fileInfo = new FileInfo(path);
        return fileInfo;
    }
    /**
     * create new file instruction
     */
    public FileInstruction createFileInstruction(FileInfo fileInfo, String range)
    {
        FileInstruction fileIns = new FileInstruction(fileInfo, range);
        return fileIns;
    }

    /**
     * @param fileIns the file instruction to add to.
     * @param pageRange the PageRange Instruction to add.
     */
    public void addPageRangeInstruction(FileInstruction fileIns, String pageRange)
    {
        fileIns.addPageRangeInstruction(pageRange);
    }

    /**
     * add file instruction the given order or create new order if not exist
     * @param fileIns the file instruction to add to order
     */
    public void addFileInstruction(FileInstruction fileIns)
    {
        if(!(user.getOrderInstructions().containsKey(currentOrderId)))
        {//create this order
            LinkedList<FileInstruction> list = new LinkedList<>();
            list.addLast(fileIns);
            OrderInstruction newOrder = new OrderInstruction(fileIns);
            user.getOrderInstructions().put(currentOrderId,newOrder);
            user.getOrderInstructionsById().put(fileIns.getFile().getFileName(),fileIns.getFile().getFileId());


        }
        else
        {//order exist
            user.getOrderInstructions().get(currentOrderId).getInstructionsList().addLast(fileIns);
        }
    }
    public void exit()
    {
        JsonHandler.toJson(user);
    }

    public void addOrderInstruction(OrderInstruction order)
    {
        user.getOrderInstructions().put(order.getOrderId(), order);
        //todo add to the gui hash
    }
}
