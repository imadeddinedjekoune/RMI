/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.Vol.AddVolPanel;
import GUI.Vol.MapVol;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Alif computer
 */
public class VolPanel extends JPanel {
    public JTabbedPane tp = new JTabbedPane();
    public AddVolPanel avp ;
    public MapVol mv ; 
    public VolPanel()
    {
        avp = new AddVolPanel();
        mv = new MapVol();
        
        setLayout(new BorderLayout());
        tp.add(avp,"Ajouter Vol");
        tp.add(mv,"Map Vol");
        
        add(tp);
    }
}
