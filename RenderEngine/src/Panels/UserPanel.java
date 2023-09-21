/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Alif computer
 */
public class UserPanel extends JPanel {
    
    JTabbedPane tp = new JTabbedPane();
    public AddPlanePanel p1 ; 
    AddVolPanel p2 ;
    
    
    public MainPanel p3 ;
    public UserPanel()
    {
        p1 = new AddPlanePanel();
        p2 = new AddVolPanel();
        p3 = new MainPanel();
        
        MainPanel.active = false;
        
        tp.add("Avion",p1);
        tp.add("Vol",p2);
        
        p3.setBounds(-2000, -2000, 1265, 680);
        tp.setBounds(0, 0, 1265, 680);
        
        
        setLayout(null);
        add(p3,BorderLayout.CENTER);
        add(tp,BorderLayout.CENTER);
        
    }
    
    public void switchOnce()
    {
        MainPanel.active = true;
        tp.setBounds(-2000, -2000, 1265, 680);
        p3.setBounds(0, 0, 1265, 680);
    }
    public void switchTwice()
    {
        MainPanel.active = false;
        p3.setBounds(-2000, -2000, 1265, 680);
        tp.setBounds(0, 0, 1265, 680);
    }
    
    
}
