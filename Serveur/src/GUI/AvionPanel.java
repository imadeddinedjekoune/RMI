/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Avion;
import Classes.FlyState;
import RMI.FlyService;
import static RMI.ServeurRMI.MF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Alif computer
 */
public class AvionPanel extends JPanel 
{
    JTable  ta ;
    JScrollPane sp ;
    JLabel title ;
    JLabel selected ;
    DefaultTableModel model = new DefaultTableModel(); 
    public ArrayList<Avion> mAvions = new ArrayList<>();
    private int selectedRow = - 1;
    
    public AvionPanel()
    {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemAdd = new JMenuItem("Modifier Cette Avion");
        JMenuItem menuItemRemove = new JMenuItem("Effacer Cette Avion");

        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        
        menuItemAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyAvionFrame(new Avion((String)ta.getValueAt(selectedRow, 0),(String)ta.getValueAt(selectedRow, 1), 
                        FlyState.New, (Float)ta.getValueAt(selectedRow, 3),
                        (Float)ta.getValueAt(selectedRow, 4)
                ));
            }
        });
        
        menuItemRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Avion avionEffacer = new Avion((String)ta.getValueAt(selectedRow, 0));
                FlyService.avions.remove(avionEffacer);
                MatchAvion(FlyService.avions);
            }
        });
        
        title = new JLabel("Gestion Des Avions");
        title.setBounds(420, 0, 500, 100);
        title.setFont(new Font("Serif", Font.PLAIN, 48));
        
        selected = new JLabel("Row Selected : ");
        
                
        ta=new JTable(model);
        
        //model.addColumn("IP-USER"); 
        model.addColumn("ID-Avion"); 
        model.addColumn("Model"); 
        model.addColumn("Etat"); 
        model.addColumn("Speed"); 
        model.addColumn("Reservoire"); 
        
        ta.setDefaultEditor(Object.class, null);
        ta.setRowHeight(25);
        
        ta.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JTable target = (JTable)e.getSource();
                try {
                    int row = target.getSelectedRow();
                    selectedRow = row ;
                    selected.setText("Row Selected : "+row+" , ID : ("+ta.getValueAt(row, 1)+")");
                    selected.repaint();
                    repaint();
                } catch (Exception ee) {
                }
            }
      });
        
        
        sp = new JScrollPane(ta);
        
        ta.setFont(new Font("Monospaced", Font.ITALIC, 18));
        sp.setBounds(10, 150, 1240, 550);
        selected.setBounds(15, 110, 200, 30);
        selected.setForeground(new Color(0, 0, 0, 255));
        selected.setFont(new Font("Serif", Font.BOLD, 14));
        sp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),BorderFactory.createEtchedBorder()));
        ta.setComponentPopupMenu(popupMenu);
        
        
        setLayout(null);
        add(sp);
        add(title);
        add(selected);

    }
    
    public void RemoveIfDoesntExist (ArrayList<Avion> aa)
    {
        for (int i = 0 ; i < mAvions.size() ; i++)
        {
            if (!aa.contains(mAvions.get(i)))
            {
                for (int j = 0 ; j < model.getRowCount() ; j++)
                {
                    if(mAvions.get(i).getMatricule().equals(model.getValueAt(j, 0)))
                    {
                        model.removeRow(j);
                    }
                }
                mAvions.remove(i);
            }
        }
    }
    
    public void addIfDoesntExist(ArrayList<Avion> aa)
    {
        for (Avion a :aa)
        {
            if(!mAvions.contains(a))
            {
                model.addRow(new Object[]{a.getMatricule(), a.getModel() , a.getEtat() , a.getSpeed() , a.getReservoir()});
                mAvions.add(a);
            }else{
                for (int j = 0 ; j < model.getRowCount() ; j++)
                {
                    if(model.getValueAt(j, 0).equals(a.getMatricule()))
                    {
                        if((Float)(model.getValueAt(j, 3)) != a.getSpeed())
                        {
                            model.setValueAt(a.getSpeed(), j, 3);
                        }
                        if((Float)(model.getValueAt(j, 4)) != a.getReservoir())
                        {
                            model.setValueAt(a.getReservoir(), j, 4);
                        }
                        if(!((String)(model.getValueAt(j, 1))).equals(a.getModel()))
                        {
                            model.setValueAt(a.getModel(), j, 1);
                        }
                    }
                }
            }
        }
    }
    
    public void MatchAvion(ArrayList<Avion> aa)
    {
        RemoveIfDoesntExist(aa);
        addIfDoesntExist(aa);
        MF.cv.vp.avp.MatchList(model);
        
    }
    
}

