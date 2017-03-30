package Client.GUI;

import Client.RequestOrganization.FileInfo;
import Client.RequestOrganization.FileInstruction;
import Client.RequestOrganization.OrderInstruction;
import Client.User.User;
import Client.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Barak on 30-Mar-17.
 */
public class BasicFrame extends JFrame {
    protected static UserInterface UI;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static Font font = new Font("Broadway", 0, 14);
    protected JPanel contentpane;
    JPanel mainPanel;
    protected static String orderToEdit="";

    public BasicFrame() throws HeadlessException, MalformedURLException {
        this.contentpane = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(3);   //TODO choose the right one above close possibilities

    }

    protected void init() {
        this.contentpane.add(this.mainPanel, "Center");
        this.setContentPane(this.contentpane);
    }
    void setUI(User user){
        UI = new UserInterface(user);
    }

    public static void main(String[] args) {
        User user = new User("12345");
        try {
            MainWindow e = new MainWindow();
            e.setVisible(true);
        } catch (MalformedURLException var2) {
            var2.printStackTrace();
        }
        FileInfo inf = new FileInfo();
        FileInstruction f = new FileInstruction(inf);
       // OrderInstruction order = new OrderInstruction(f);
        UI.addFileInstruction(f);
        System.out.println("num of orders: "+ UI.getUser().getOrderInstructionsById().size());

    }
}
