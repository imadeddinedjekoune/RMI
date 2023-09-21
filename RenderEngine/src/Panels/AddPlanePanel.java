/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class AddPlanePanel extends JPanel {
    public TableInfo ti = new TableInfo(new ArrayList<>());
    JLabel title ;
    JButton ajouter = new JButton("Ajouter");
    JButton qa = new JButton("Quick Add");
    public QuickAddPanel qap = new QuickAddPanel() ;
    public FrameQuickAdd fqa ;
    
    public AddPlanePanel()
    {
        title = new JLabel("Gestoin Des Avions");
        title.setBounds(420, 0, 500, 100);
        ti.setBounds(10, 120, 1230, 500);
        ajouter.setBounds(1150, 95, 75, 30);
        qa.setBounds(1030, 95, 100, 30);
        
        title.setFont(new Font("Serif", Font.PLAIN, 48));
        
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                renderengine.RenderEngine.MF.up.switchOnce();
            }
        });
        
        qa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                fqa = new FrameQuickAdd(qap);
            }
        });
        
        setLayout(null);
        
        add(title);
        add(ajouter);
        add(qa);
        add(ti);
        
    }
    
    public void rep()
    {
        removeAll();
        
        title.setBounds(420, 0, 500, 100);
        ti.setBounds(10, 120, 1230, 500);
        ajouter.setBounds(1150, 95, 75, 30);
        qa.setBounds(1030, 95, 100, 30);
        
        add(title);
        add(ajouter);
        add(qa);
        add(ti);
        
        repaint();
        revalidate();
    }
 
}
