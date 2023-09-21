/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author Alif computer
 */
public class FrameQuickAdd extends JFrame {
    
    private static int width = 640 , height = 320; 
    
    
    public FrameQuickAdd(QuickAddPanel qa)
    {
        setTitle("Quick Add");
        
        setSize(width,height);
	setVisible(true);
        setResizable(false);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);	
        setLayout(new BorderLayout());
        add(qa,BorderLayout.CENTER);
    }

   
    
}
