/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import Mesh.AirPlain;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Utils.AvionGestionFiles;


/**
 *
 * @author Alif computer
 */
public class MainPanel extends JPanel{
    public MyCanvas c = new MyCanvas();
    public JTextField tf1 = new JTextField();
    public JTextField tf2 = new JTextField();
    public JTextField tf3 = new JTextField();
    public static boolean active = true ;
    public static int choice = -1 ;
    
    public JComboBox<String> comboLanguage = new JComboBox<String>();
    
    public String[] getInfo()
    {
        String ar[] = {tf1.getText() , (String) comboLanguage.getSelectedItem() , tf2.getText() , tf3.getText()};
        return ar;
    }
    
    public MainPanel()
    {
        setLayout(null);
        c.setBounds(0, 0, 1580, 850);
        
        tf1.setBounds(1500, 120, 160, 30);
       
        
        tf2.setBounds(1500, 185+140, 160, 30);
        tf3.setBounds(1500, 185+70, 160, 30);
        comboLanguage.setBounds(1500, 185, 160, 30);
        
 
        comboLanguage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String selectedItem = (String) comboLanguage.getSelectedItem();
                
                for (int i = 0 ; i < AvionGestionFiles.stuct.size() ; i++)
                {
                    if(selectedItem.equals(AvionGestionFiles.getNameIn(i)))
                    {
                        choice = i+1  ;
                    }
                }
            }
        });
        
    

        comboLanguage.setVisible(true);
      
        add(tf1);
        add(tf2);
        add(tf3);
        add(comboLanguage);
        
        add(c);
    }
    
    public void noshow()
    {
        tf1.setBounds(1500, 120, 160, 30);
        tf2.setBounds(1500, 185+140, 160, 30);
        tf3.setBounds(1500, 185+70, 160, 30);
        comboLanguage.setBounds(1500, 185, 160, 30);
        repaint();
    }
    
    public void show()
    {
        tf1.setBounds(150, 120, 160, 30);
        tf2.setBounds(150, 185+140, 160, 30);
        tf3.setBounds(150, 185+70, 160, 30);
        comboLanguage.setBounds(150, 190, 160, 30);
        repaint();
    }
    
    
}
