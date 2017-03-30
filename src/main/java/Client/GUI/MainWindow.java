package Client.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.HashMap;
import javax.swing.*;

public class MainWindow extends BasicFrame {
    private JButton newOrderButton;
    private JButton recentOrdersButton;
    Insets inset;
    GridBagConstraints constraints;

    public MainWindow() throws HeadlessException, MalformedURLException {
        Dimension buttonSize = new Dimension(screenSize.width/15, screenSize.height/20);
        this.constraints = new GridBagConstraints();
        this.inset = new Insets(0, 0, 100, 400);
        this.constraints.insets = this.inset;
        this.constraints.gridwidth = 0;
        this.constraints.gridheight = 0;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.ipadx = 0;
        this.constraints.ipady = 0;
        this.newOrderButton = new JButton("New Order");
        this.newOrderButton.setName("newOrderButton");
        this.newOrderButton.setPreferredSize( buttonSize );
        this.newOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.mainPanel.add(this.newOrderButton, this.constraints);
        this.inset = new Insets(0, 400, 100, 0);
        this.constraints.insets = this.inset;
        this.constraints.gridwidth = 0;
        this.constraints.gridheight = 0;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.ipadx = 0;
        this.constraints.ipady = 0;
        this.recentOrdersButton = new JButton("Recent Orders");
        this.recentOrdersButton.setName("recentOrdersButton");
        this.recentOrdersButton.setPreferredSize(buttonSize);
        this.recentOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.mainPanel.add(this.recentOrdersButton, this.constraints);

        this.init();
    }
}
