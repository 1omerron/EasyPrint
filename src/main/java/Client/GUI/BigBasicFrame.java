package Client.GUI;

import Client.GUI.MainWindow;
import Client.UserInterface;
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

public abstract class BigBasicFrame extends BasicFrame {
    protected URL logoLocation = this.getClass().getClassLoader().getResource("Icons/pandaprint.jpg");
    protected JLabel background;

    public BigBasicFrame() throws HeadlessException, MalformedURLException {
        this.setBounds(screenSize.width/4, 0, screenSize.width/2,  (int)(screenSize.height/1.1));

        JPanel topLogoPanel = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon(this.logoLocation));
        topLogoPanel.add(logoLabel);
        this.contentpane.add(topLogoPanel, "North");
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(3);   //TODO choose the right one above close possibilities
    }

}
