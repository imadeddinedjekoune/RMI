/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import Classes.Avion;
import Classes.FlyState;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alif computer
 */
public class QuickAddPanel extends JPanel 
{
    public JComboBox<String> cp = new JComboBox<String>();
    JLabel l1 = new JLabel("ID : ");
    JLabel l2 = new JLabel("Model : ");
    JLabel l3 = new JLabel("Speed : ");
    JLabel l4 = new JLabel("Essance : ");
    
    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField();
    JTextField tf3 = new JTextField();
    
    JButton btn= new JButton("Ajouter");
    
    public QuickAddPanel()
    {
        l1.setBounds(140, 50, 70, 30);
        tf1.setBounds(250, 50, 200, 30);
        
        l2.setBounds(140, 50+40*1, 70, 30);
        cp.setBounds(250, 50+40*1, 200, 30);
        
        l3.setBounds(140, 50+40*2, 70, 30);
        tf2.setBounds(250, 50+40*2, 200, 30);
        
        l4.setBounds(140, 50+40*3, 70, 30);
        tf3.setBounds(250, 50+40*3, 200, 30);
        
        btn.setBounds(480, 50+40*3+70, 70, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String[] infos = {tf1.getText() ,(String) cp.getSelectedItem() , tf3.getText() , tf2.getText()};
                renderengine.RenderEngine.MF.up.p1.ti.addNewInfo(new PlaneInfo("./res/airplaines/"+infos[1]+".png",infos[0],infos[3],"New",infos[2]));
                renderengine.RenderEngine.MF.up.p1.rep();
                try {
                    renderengine.RenderEngine.stub.ajouterAvion(new Avion(infos[0], infos[1], FlyState.New,Float.parseFloat(infos[3]),Float.parseFloat(infos[2])));
                } catch (RemoteException ex) {
                    
                }
            }
        });
        
        
        setLayout(null);
        add(l1);
        add(tf1);
        add(l2);
        add(cp);
        add(l3);
        add(tf2);
        add(l4);
        add(tf3);
        add(btn);
        
        
    }
}
