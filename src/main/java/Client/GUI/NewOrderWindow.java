package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * Created by Barak on 30-Mar-17.
 */
public class NewOrderWindow extends BasicFrame {
    JButton createOrderButton;
    private Insets insets;
    private GridBagConstraints constraints;
    String newName = "not initialized yet";

    public NewOrderWindow() throws HeadlessException, MalformedURLException {
        this.setBounds(screenSize.width/4, 0, screenSize.width/2,  (int)(screenSize.height/6));
        Dimension buttonSize = new Dimension( screenSize.width/20,screenSize.height/25 );
        mainPanel = new JPanel( new GridBagLayout() );
        constraints = new GridBagConstraints(  );
        constraints.fill = GridBagConstraints.HORIZONTAL;//not sure what it's doing
        constraints.anchor = GridBagConstraints.CENTER; //same..

        //JTextArea init
        JTextField newNameTextBox = new JTextField(30);
        /*
        newNameTextBox.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                newNameTextBox.setText( "" );
            }

            @Override
            public void focusLost(FocusEvent e) {
            //e.get
            }
        } );
        */
        //this.insets = new Insets(50,5,5,50);
        //constraints.insets = insets;
        newNameTextBox.setMargin(new Insets(15,5,15,300));
        newNameTextBox.setEditable(true);
        newNameTextBox.setFont( font );
        constraints.gridx = 0;
        mainPanel.add( newNameTextBox );

        //createOrderButton init
        this.insets = new Insets(0, 30, 0, 0);
        this.constraints.gridx = 1;
        this.constraints.insets = this.insets;
        createOrderButton = new JButton( "Create" );
        createOrderButton.setPreferredSize( buttonSize );
        createOrderButton.addActionListener( e ->{
            newName = newNameTextBox.getText();
           // UI.createOrderInstruction();

        });
        mainPanel.add(createOrderButton, constraints);

        init();
    }
}
