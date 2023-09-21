/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Aeroport;
import RMI.FlyService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alif computer
 */
public class ListeAeroportPanel extends JPanel {
    JTable  ta ;
    JScrollPane sp ;
    DefaultTableModel model = new DefaultTableModel(); 
    
    public ListeAeroportPanel()
    {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemRemove = new JMenuItem("Effacer Cette Aeroport");

        popupMenu.add(menuItemRemove);
        
        
        menuItemRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = ta.getSelectedRow();
                String NomAr = (String) ta.getValueAt(row, 0);
                
                for (int i = 0 ; i < FlyService.aeroports.size() ; i++)
                {
                    if (FlyService.aeroports.get(i).getName().equals(NomAr))
                    {
                        FlyService.aeroports.remove(i);
                        model.removeRow(row);
                        RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip8.repaint();
                        RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.repaint();
                        break;
                    }
                }
                
            }
        });
        
        ta=new JTable(model);
        
        model.addColumn("Nom Aeroport"); 
        model.addColumn("Lat / Lon"); 
        model.addColumn("Cap Reservoire"); 
        model.addColumn("Act Reservoire"); 
        model.addColumn("Cap Place");
        model.addColumn("Place Libre");
        
        ta.setDefaultEditor(Object.class, null);
        ta.setRowHeight(25);
        
        sp = new JScrollPane(ta);
        
        ta.setFont(new Font("Monospaced", Font.ITALIC, 18));
        sp.setBounds(0, 30, 1240, 597);
        
        sp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),BorderFactory.createEtchedBorder()));
        ta.setComponentPopupMenu(popupMenu);
        
        setLayout(null);
        add(sp);
    }
    
    public void AddLine(Aeroport a)
    {
        model.addRow(new Object[]{a.getName(),a.getP(),a.getCapaciteDeReservoire(),a.getReservoireActuel(),a.getCapaciteDePlace(),a.getPlacePrise()});
    }
    
}

