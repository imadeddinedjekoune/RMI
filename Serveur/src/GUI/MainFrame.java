/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import RMI.IFlyRemote;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.rmi.Naming;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Alif computer
 */
public class MainFrame extends JFrame {
    private static int width = 1278 , height = 800; 
    public CoverPanel cv = new CoverPanel();
    public MainFrame()
    {
        
        setTitle("Server");
        setSize(width,height);
	setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);	
        setLayout(new BorderLayout());
        add(cv,BorderLayout.CENTER);
        
    }
    
    
}
