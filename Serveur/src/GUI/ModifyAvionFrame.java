/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Avion;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Alif computer
 */
public class ModifyAvionFrame extends JFrame {
    
    private static int width = 300 , height = 280; 
    ModifyAvionPanel map ;
    public ModifyAvionFrame(Avion a)
    {
        map = new ModifyAvionPanel(a);
        setTitle("Modifier Avion");
        setSize(width,height);
	setVisible(true);
        setResizable(false);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);	
        setLayout(new BorderLayout());
        add(map,BorderLayout.CENTER);
    }
    
}
