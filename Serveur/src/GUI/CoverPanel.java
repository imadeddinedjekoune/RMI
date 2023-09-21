/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Alif computer
 */
public class CoverPanel extends JPanel {
    public JTabbedPane tp ;
    public AvionPanel ap ;
    public AeroportPanel aep ;
    public VolPanel vp ;
    public CoverPanel()
    {
        ap = new AvionPanel();
        aep = new AeroportPanel();
        vp = new VolPanel();
        
        tp = new JTabbedPane();
        
        tp.add(ap,"Avion");
        tp.add(aep,"Aeroport");
        tp.add(vp,"Aeroport");
        
        
        setLayout(new BorderLayout());
        add(tp,BorderLayout.CENTER);
    }
    
}
