/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class WaitingFrame extends JFrame {
    private static int width = 654 , height = 500; 
    
    public WaitingPanel up = new WaitingPanel();
    
    public WaitingFrame()
    {
     
        setTitle("");
        
        setSize(width,height);
	setVisible(true);
        setResizable(false);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);	
        setLayout(new BorderLayout());
        add(up,BorderLayout.CENTER);
        
    }
    
    
}
