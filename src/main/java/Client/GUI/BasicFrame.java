package Client.GUI;

import Client.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * Created by Barak on 30-Mar-17.
 */
public class BasicFrame extends JFrame {
    protected static UserInterface UI;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static Font font = new Font("Broadway", 0, 600);
    protected JPanel contentpane;
    JPanel mainPanel;

    public BasicFrame() throws HeadlessException, MalformedURLException {
    this.contentpane = new JPanel(new BorderLayout());

    }

    protected void init() {
        this.contentpane.add(this.mainPanel, "Center");
        this.setContentPane(this.contentpane);
    }

    public static void main(String[] args) {
        try {
            MainWindow e = new MainWindow();
            e.setVisible(true);
        } catch (MalformedURLException var2) {
            var2.printStackTrace();
        }
    }
}
