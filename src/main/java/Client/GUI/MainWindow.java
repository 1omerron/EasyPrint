package Client.GUI;

import Client.RequestOrganization.OrderInstruction;
import Client.User.User;
import Client.UserInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainWindow extends BigBasicFrame {
    private JButton newOrderButton;
    private JButton recentOrdersButton;
    Insets inset;
    GridBagConstraints constraints;
    private JList ordersList;
    JScrollPane ordersScrollList;

    public MainWindow(User u) throws HeadlessException, MalformedURLException {
        if(UI== null){setUI(u);}
        Dimension buttonSize = new Dimension(screenSize.width/15, screenSize.height/20);
        this.constraints = new GridBagConstraints();
        this.inset = new Insets(0, 0, screenSize.height/2, screenSize.width/6);
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
        newOrderButton.setFont(font);
        this.newOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MainWindow re = new MainWindow(UI.getUser());
                    System.out.println("num of orders: "+ UI.getUser().getOrderInstructionsById().size());
                    re.setVisible(true);
                    dispose();
                } catch (MalformedURLException var2) {
                    var2.printStackTrace();
                }
            }
        });
        this.mainPanel.add(this.newOrderButton, this.constraints);
        this.inset = new Insets(0, screenSize.width/6, screenSize.height/2, 0);
        this.constraints.insets = this.inset;
        this.constraints.gridwidth = 0;
        this.constraints.gridheight = 0;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.ipadx = 0;
        this.constraints.ipady = 0;
        this.recentOrdersButton = new JButton("Edit");
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
        this.inset = new Insets(0, 0, 0, 0);
        this.constraints.insets = this.inset;
        //DefaultListModel listModel = new DefaultListModel<String>(UI.getOrders());
        Object[] orderNameArray;
        Object[][] data;
        if(UI.getUser().getOrderInstructionsById()==null||UI.getUser().getOrderInstructionsById().isEmpty()){
            System.out.println("no orders "+ UI.getUser().getOrderInstructionsById().size());
            data = new Object[0][0];
        }
        else{
            orderNameArray = UI.getUser().getOrderInstructionsById().keySet().toArray();
            data = new Object[orderNameArray.length][1];
            for(int i=0; i<orderNameArray.length;i++){
                data[i][0] = orderNameArray[i];
            }
        }

        //for tests
       // Object[] testArray = {"test1", "test2","test3"};
        //Object[] testName = {"order1", "order2","order3"};
      /*  String[] columnNames = {
                "orders",
                };
        Object[][] data = {
                {"order1",},
                {"order2"},
                {"order3"}

        };*/
        String[] columnNames = {
                "orders",
        };
        JTable table = new JTable(data, columnNames);
        table.setFont(font);
        table.setRowSelectionAllowed(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                orderToEdit = table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
            }
        });

       // this.ordersList = new JList(testArray);
      //  ordersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        //ordersList.setLayoutOrientation(JList.VERTICAL_S);
        //ordersList.setVisibleRowCount(-1);
        ordersScrollList = new JScrollPane(table);
        ordersScrollList.setPreferredSize(new Dimension(screenSize.width/4, screenSize.height/3));
        this.mainPanel.add(this.ordersScrollList, this.constraints);


        this.init();
    }



}
