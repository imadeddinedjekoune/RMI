/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import Panels.AddPlanePanel;
import Panels.MainPanel;
import Panels.MyCanvas;
import Panels.UserPanel;
import RMI.IFlyRemote;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Naming;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.lwjgl.opengl.Display;
import static renderengine.RenderEngine.stub;

/**
 *
 * @author Alif computer
 */
public class MainFrame extends JFrame{
    private static int width = 1278 , height = 717; 
    
    public UserPanel up = new UserPanel();
    public MainFrame()
    {
        try {
            stub = (IFlyRemote) Naming.lookup("rmi://localhost:1010/FR");
        } catch (Exception e) {
            
        }
        
        setTitle("Client");
        setEnabled(false);
        setSize(width,height);
	setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2-10);	
        setLayout(new BorderLayout());
        add(up,BorderLayout.CENTER);
        
        
    }
    
    
    
    
    
}

