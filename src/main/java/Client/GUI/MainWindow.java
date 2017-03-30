package Client.GUI;

import Client.User.User;
import Client.UserInterface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.*;

public class MainWindow extends BigBasicFrame {
    private JButton newOrderButton;
    private JButton editOrderWindow;
    Insets inset;
    GridBagConstraints constraints;
    private JList ordersList;
    JScrollPane ordersScrollList;

    public MainWindow() throws HeadlessException, MalformedURLException {
        User user = new User( "12345" );
        UI = new UserInterface( user );
        Dimension buttonSize = new Dimension(screenSize.width/15, screenSize.height/20);
        this.constraints = new GridBagConstraints();
        this.inset = new Insets(0, 0, screenSize.height/2, screenSize.width/6);
        this.constraints.insets = this.inset;

        this.newOrderButton = new JButton("New Order");
        this.newOrderButton.setName("newOrderButton");
        this.newOrderButton.setPreferredSize(buttonSize);
        newOrderButton.setFont(font);
        this.newOrderButton.addActionListener( e -> {
            try {
                NewOrderWindow newOrderWindow = new NewOrderWindow();
                newOrderWindow.setVisible(true);
                dispose();
            } catch (MalformedURLException var2) {
                var2.printStackTrace();
            }
        } );
        this.mainPanel.add(this.newOrderButton, this.constraints);
        this.inset = new Insets(0, screenSize.width/6, screenSize.height/2, 0);
        this.constraints.insets = this.inset;

        this.editOrderWindow = new JButton("Edit");
        this.editOrderWindow.setName("editOrderWindow");
        this.editOrderWindow.setPreferredSize(buttonSize);
        this.editOrderWindow.addActionListener( e -> {
            try {
                EditOrderWindow editOrderWindow = new EditOrderWindow();
                editOrderWindow.setVisible( true );
                dispose();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
        } );
        this.mainPanel.add(this.editOrderWindow, this.constraints);
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
        table.getSelectionModel().addListSelectionListener( event -> {
            orderToEdit = table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString();
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
