package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Barak on 30-Mar-17.
 */
public class EditOrderWindow extends BigBasicFrame {

    private JButton openFileButton;
    private JButton submitNewFileButton;
    private JButton printButton;
    private JButton backToMainButton;
    private JTextArea orderNamelabel;
    private JPanel instructionPanel;
    private Insets inset;
    private GridBagConstraints constraints;

    public EditOrderWindow(String orderId) throws HeadlessException, MalformedURLException {
        Dimension buttonSize1 = new Dimension( screenSize.width/15,screenSize.height/20 );
        Dimension buttonSize2 = new Dimension( screenSize.width/20,screenSize.height/20 );
        this.mainPanel = new JPanel( new GridBagLayout() );
        this.constraints = new GridBagConstraints(  );
        constraints.fill = GridBagConstraints.HORIZONTAL;//not sure what it's doing
        constraints.anchor = GridBagConstraints.CENTER; //same..

        //JFileChooser init
        JFileChooser fc = new JFileChooser();

        //openFileButton init
        this.inset = new Insets(0, 20, 0, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;
        //this.constraints.gridwidth = 1;
        this.constraints.insets = this.inset;
        openFileButton = new JButton( "Add File" );
        openFileButton.setPreferredSize( buttonSize1 );
        JPanel add = new JPanel();
        add.add( openFileButton );
        openFileButton.addActionListener( e -> {
            int returnVal = fc.showOpenDialog( EditOrderWindow.this ); //TODO make sure to chnge here from EditOrderWindow to newer one.

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                    //TODO

            } else {}
        });
        mainPanel.add( add, constraints );

        //submitNewFileButton init
        this.inset = new Insets(0, 20, 400, 0);
        constraints.gridy = 1;
        this.constraints.insets = this.inset;
        submitNewFileButton = new JButton( "Submit" );
        submitNewFileButton.setPreferredSize( buttonSize1 );
        submitNewFileButton.addActionListener(e -> {
            //TODO
        });
        mainPanel.add( submitNewFileButton, constraints );


        //printButton init
        this.inset = new Insets(0, 0, 0, 0);
        //constraints.gridy = 1;
        this.constraints.insets = this.inset;
        printButton = new JButton( "Print" );
        printButton.setPreferredSize( buttonSize1 );
        printButton.addActionListener(e -> {
            //TODO
        });
        mainPanel.add( printButton, constraints );


        //backToMainButton init
        this.inset = new Insets(0, 0, 0, 0);
        //constraints.gridy = 1;
        this.constraints.insets = this.inset;
        backToMainButton = new JButton( "Back to Main" );
        backToMainButton.setPreferredSize( buttonSize1 );
        backToMainButton.addActionListener(e -> {
            //TODO
        });
        mainPanel.add( backToMainButton, constraints );

        //orderNameLabel
        orderNamelabel = new JTextArea( UI.getUser().getOrderInstructions().get( orderId ).getOrderName() );
        System.out.println(UI.getUser().getOrderInstructions().get( orderId ).getOrderName());
        mainPanel.add( orderNamelabel, constraints );




        init();
    }
}
