/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Alif computer
 */
public class AeroportPanel extends JPanel {
    JLabel title ;
    JTabbedPane tp ;
    public AddAeroportPanel addPanel ;
    public ListeAeroportPanel listePanel ;
    
    public AeroportPanel()
    {
        title = new JLabel("Gestion Des Aeroports");
        title.setBounds(450, 0, 500, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        
        
        addPanel = new AddAeroportPanel();
        listePanel = new ListeAeroportPanel();
        
        tp = new JTabbedPane();
        tp.add("Liste", listePanel);
        tp.add("Ajouter", addPanel);
        
        
        tp.setBounds(10, 40, 1240, 680);
        
        tp.addChangeListener(new ChangeListener() 
        {
            public void stateChanged(ChangeEvent e) 
            {
                if(tp.getSelectedIndex() == 1)
                {
                    try {
                       
                        
                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY();
                    //tp.requestFocusInWindow();
                    
                    click(addPanel.extern.getLocationOnScreen().x+320, addPanel.extern.getLocationOnScreen().y+320);
                    //addPanel.repaint();
                    
                } catch (Exception ex) {
                    Logger.getLogger(AeroportPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
                
                
            }
        });
        
        setLayout(null);
        
        add(title);
        add(tp);
    }
    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);    
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        
    }
    
    
}
