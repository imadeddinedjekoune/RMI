/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Aeroport;
import Classes.GeoPoint;
import GUIMap.Maps.MapPanel;
import RMI.FlyService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Alif computer
 */
public class AddAeroportPanel extends JPanel {
    public MapPanel mp = new MapPanel();
    public JPanel extern = new JPanel();
    JLabel l1 = new JLabel("Nom Aeroport : ");
    JTextField t1 = new JTextField();
    
    JLabel l2 = new JLabel("Position GPS : ");
    public JTextField t2 = new JTextField();
    
    JLabel l3 = new JLabel("Cap Reservoire : ");
    JTextField t3 = new JTextField();
    
    JLabel l4 = new JLabel("Cap Place : ");
    JTextField t4 = new JTextField();
    
    JButton save = new JButton("Ajouter");
    
    JCheckBox show = new JCheckBox("Afficher Les Aeroports ");  
    JPanel pTitle = new JPanel();
    
    public AddAeroportPanel()
    {
        extern.setBounds(570, 10, 640, 640);
        
        extern.setLayout(new BorderLayout());
        extern.add(mp,BorderLayout.CENTER);
        
        
        extern.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEtchedBorder()));
        
        TitledBorder b = BorderFactory.createTitledBorder("   Information De LAeroport :   ");
        b.setTitleFont(new Font("Monospaced", Font.ITALIC, 16));
        pTitle.setBorder(b);
        
        pTitle.setBounds(30, 80, 510, 500);
        
        int off = 100;
        
        l1.setBounds(50, 100+off, 200, 30);
        l1.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        t1.setBounds(260, 100+off, 250, 30);
        t1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        l2.setBounds(50, 170+off, 200, 30);
        l2.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        t2.setBounds(260, 170+off, 250, 30);
        t2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        l3.setBounds(50, 240+off, 250, 30);
        l3.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        t3.setBounds(260, 240+off, 250, 30);
        t3.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        l4.setBounds(50, 310+off, 250, 30);
        l4.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        t4.setBounds(260, 310+off, 250, 30);
        t4.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        
        t2.addKeyListener(new KeyListener(){

            public void keyPressed(KeyEvent e){

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String latLon = t2.getText();
                    double lat = Double.parseDouble(latLon.split(",")[0]);
                    double lon = Double.parseDouble(latLon.split(",")[1]);
                    
                    if(MapPanel.selectedPanel == 0)
                    {
                        MapPanel.aePos = new Object[]{new double[]{lat, lon},8};
                        RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip8.repaint();
                        RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.repaint();
                    }else{
                        MapPanel.aePos = new Object[]{new double[]{lat, lon},11};
                        RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.repaint();
                    }
                    
                }    
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        save.setBounds(420, 390+off, 80, 30);
        show.setBounds(40,  480+off, 200, 30);
        
        show.setSelected(true);
        show.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {                 
                MapPanel.showAr = (e.getStateChange()==1); 
                RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip8.repaint();
                RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.repaint();
             }    
          });    
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MapPanel.aePos = null ;
                RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip8.repaint();
                RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.repaint();
                float lat = Float.parseFloat(t2.getText().split(",")[0]);
                float lon = Float.parseFloat(t2.getText().split(",")[1]);
                
                GeoPoint p = new GeoPoint(lat,lon);
                Aeroport created = new Aeroport(t1.getText(),
                       p, t1.getText(), Integer.parseInt(t4.getText()), 
                        Integer.parseInt(t3.getText())
                );
                FlyService.aeroports.add(created);
                RMI.ServeurRMI.MF.cv.aep.listePanel.AddLine(created);
                setUnfocus();
                RMI.ServeurRMI.MF.cv.vp.avp.rep();
                
            }
        });
        
        setLayout(null);
        add(extern);
        
        
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(save);
        add(pTitle);
        add(show);
    }
    
    
    public void setUnfocus()
    {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
    }
    
    
}
