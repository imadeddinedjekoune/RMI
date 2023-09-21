/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import GUIMap.Maps.ImagePanelZ8;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author Alif computer
 */
public class AddVolPanel extends JPanel {
    JLabel title ;
    JTable jt ;
    JScrollPane sp ;
    public AddVolPanel()
    {
        
        title = new JLabel("Liste Des Vols");
        title.setBounds(420, 0, 500, 100);
        title.setFont(new Font("Serif", Font.PLAIN, 48));
        
        String data[][]={ 
                    {"Vol-001","Avion-001","Escale","Alg","Ora","08:00:00","09:00:00","Alg-Tel-Ora","35.683258, -0.660035"},
                    {"Vol-002","Avion-001","Direct","Alg","Ora","08:00:00","09:00:00","Alg-Ora","Pos"},
                    {"Vol-003","Avion-002","Escale","Alg","Ora","08:00:00","09:00:00","Alg-Tel-Ora","Pos"},
                };    
        String column[]={"ID Vol","ID Avion","Type","Aeroport Depart","Aeroport Fin","Temp de Depart","Temp Arriver","Trajection","Position Actuelle"};         
        jt=new JTable(data,column);   
        jt.setDefaultEditor(Object.class, null);
        
        
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    if(column == 8)
                    {
                        String latlon[] = ((String)(jt.getValueAt(row, column))).split(",");
                        double lat = Double.parseDouble(latlon[0]);
                        double lon = Double.parseDouble(latlon[1]);
                        
                        renderengine.RenderEngine.mfMap.setVisible(true);
                        ImagePanelZ8.setTXY(lat, lon);
                        renderengine.RenderEngine.mfMap.mp.ip8.repaint();
                        renderengine.RenderEngine.mfMap.mp.repaint();
                        
                    }
                }
            }
      });
        
        
        sp = new JScrollPane(jt);
        sp.setBounds(10, 120, 1240, 510);
        jt.setRowHeight(40);
        //
        setLayout(null);
        add(title);
        add(sp);
        
        
        
        
    }
}
