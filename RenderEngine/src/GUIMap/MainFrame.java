/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap;

import GUIMap.Maps.ImagePanel;
import GUIMap.Maps.MapPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Alif computer
 */
public class MainFrame extends JFrame {
    public static int width = 654;
    public static int height = 677;
    
    public MapPanel mp = new MapPanel();
    public RadarPanel rd = new RadarPanel();
    
    public MainFrame()
    {
        setTitle("RSD");
        setSize(width,height);
        setVisible(false);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);
        setResizable(true);
        
        setLayout(new BorderLayout());
        add(mp,BorderLayout.CENTER);
    }
    
}
