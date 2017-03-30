package Client.GUI;

import Client.GUI.BasicFrame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.JButton;

public class MainWindow extends BasicFrame {
    private JButton newOrderButton;
    private JButton recentOrdersButton;
    Insets inset;
    GridBagConstraints constraints;

    public MainWindow() throws HeadlessException, MalformedURLException {
        Dimension buttonSize = new Dimension(screenSize.width, screenSize.height);
        this.constraints = new GridBagConstraints();
        this.inset = new Insets(0, 0, 150, 0);
        this.constraints.insets = this.inset;
        this.constraints.gridwidth = 2;
        this.constraints.gridheight = 2;
        this.constraints.gridx = 2;
        this.constraints.gridy = 0;
        this.newOrderButton = new JButton("New Order");
        this.newOrderButton.setName("newOrderButton");
        this.newOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.mainPanel.add(this.newOrderButton, this.constraints);
        this.inset = new Insets(150, 0, 0, 0);
        this.constraints.insets = this.inset;
        this.constraints.gridx = 2;
        this.constraints.gridy = 3;
        this.constraints.ipadx = 1;
        this.constraints.ipady = 1;
        this.recentOrdersButton = new JButton("Recent Orders");
        this.recentOrdersButton.setName("recentOrdersButton");
        this.recentOrdersButton.setSize(buttonSize);
        this.recentOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.mainPanel.add(this.recentOrdersButton, this.constraints);
        this.start();
    }
}
