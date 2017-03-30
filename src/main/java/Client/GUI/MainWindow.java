package Client.GUI;

import Client.RequestOrganization.OrderInstruction;
import Client.UserInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.*;

public class MainWindow extends BigBasicFrame {
    private JButton newOrderButton;
    private JButton recentOrdersButton;
    Insets inset;
    GridBagConstraints constraints;
    private JList ordersList;
    JScrollPane ordersScrollList;

    public MainWindow() throws HeadlessException, MalformedURLException {

        Dimension buttonSize = new Dimension(screenSize.width/15, screenSize.height/20);
        this.constraints = new GridBagConstraints();
        this.inset = new Insets(0, 0, 700, 300);
        this.constraints.insets = this.inset;
        this.constraints.gridwidth = 0;
        this.constraints.gridheight = 0;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.ipadx = 0;
        this.constraints.ipady = 0;
        this.newOrderButton = new JButton("New Order");
        this.newOrderButton.setName("newOrderButton");
        this.newOrderButton.setPreferredSize(buttonSize);
        this.newOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.mainPanel.add(this.newOrderButton, this.constraints);
        this.inset = new Insets(0, 310, 700, 0);
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
                try {
                    NewOrderWindow newOrderWindow = new NewOrderWindow();
                    newOrderWindow.setVisible( true );
                    dispose();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.mainPanel.add(this.recentOrdersButton, this.constraints);
        this.inset = new Insets(0, 0, 200, 0);
        this.constraints.insets = this.inset;
        //DefaultListModel listModel = new DefaultListModel<String>(UI.getOrders());
       // OrderInstruction[] orderInstructionArray = (OrderInstruction[]) (UI.getOrders()).values().toArray();
        //for tests
        Object[] testArray = {"order1", "order2","order3"};
        this.ordersList = new JList(testArray);
        ordersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ordersList.setLayoutOrientation(JList.VERTICAL);
        ordersList.setVisibleRowCount(-1);
        ordersScrollList = new JScrollPane(ordersList);
        ordersScrollList.setPreferredSize(new Dimension(300, 250));
        ordersScrollList.setFont(font);
        this.mainPanel.add(this.ordersScrollList, this.constraints);


        this.init();
    }
}
