package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

/**
 * Created by Barak on 30-Mar-17.
 */
public class NewOrderWindow extends BasicFrame {
    private JButton addFile;
    private JButton submitNewOrder;
    private Insets inset;
    private GridBagConstraints constraints;

    public NewOrderWindow() throws HeadlessException, MalformedURLException {
        this.setBounds(screenSize.width/4, 0, screenSize.width/2,  (int)(screenSize.height/4));
        Dimension buttonSize = new Dimension( screenSize.width/20,screenSize.height/25 );
        addFile = new JButton( "Add File" );
        addFile.setPreferredSize( buttonSize );
        submitNewOrder = new JButton( "Submit" );
        submitNewOrder.setPreferredSize( buttonSize );


        mainPanel.setLayout( new GridBagLayout(  ) );
        init();




    }
}
