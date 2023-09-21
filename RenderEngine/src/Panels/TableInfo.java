/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Alif computer
 */
public class TableInfo extends JPanel{
    JPanel columnpanel;
    public ArrayList<PlaneInfo> plans = new ArrayList<>();
    JPanel borderlaoutpanel ;
    public TableInfo(ArrayList<PlaneInfo> pi)
    {
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 1220, 490);
        add(scrollPane);
        
        borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));
        
        columnpanel= new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);
        
        for(int i=0;i<pi.size();i++) 
        {
            pi.get(i).setPreferredSize(new Dimension(300,170));
            plans.add(pi.get(i));
            columnpanel.add(pi.get(i));
            pi.get(i).setLayout(new BorderLayout());
        }
        
    }
    
    public void addNewInfo(PlaneInfo p)
    {
        p.setPreferredSize(new Dimension(300,170));
        columnpanel.add(p);
        p.setLayout(new BorderLayout());
        
        repaint();
        columnpanel.repaint();
        borderlaoutpanel.repaint();
        plans.add(p);
    }
    
    public void removeInfo(int pi)
    {
        for (int i = 0 ; i < plans.size() ; i++)
        {
            if (plans.get(i).getId() == pi)
            {
                plans.remove(i);
                break;
            }
        }
        removeAll();
        borderlaoutpanel.removeAll();
        columnpanel.removeAll();
        
        
        setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 1220, 490);
        add(scrollPane);
        
        borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));
        
        
        columnpanel= new JPanel();

        borderlaoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);
        
        for(int i=0;i<plans.size();i++) {
            plans.get(i).setPreferredSize(new Dimension(300,170));
            columnpanel.add(plans.get(i));
            plans.get(i).setLayout(new BorderLayout());

        }
        repaint();
        borderlaoutpanel.repaint();
        columnpanel.repaint();
    }
   
    
    
}
