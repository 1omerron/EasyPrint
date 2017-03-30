package Client.GUI;

import Client.GUI.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class BasicFrame extends JFrame {
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static Font font = new Font("Broadway", 0, 40);
    protected URL logoLocation = this.getClass().getClassLoader().getResource("Icons/logo.jpg");
    protected JPanel contentpane;
    protected JPanel mainPanel;
    protected JLabel background;

    public BasicFrame() throws HeadlessException, MalformedURLException {
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.contentpane = new JPanel(new BorderLayout());
        JPanel topLogoPanel = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon(this.logoLocation));
        topLogoPanel.add(logoLabel);
        this.contentpane.add(topLogoPanel, "North");
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(3);
    }

    protected void start() {
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
