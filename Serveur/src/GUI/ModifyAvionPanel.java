/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Avion;
import RMI.FlyService;
import static RMI.ServeurRMI.MF;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alif computer
 */
public class ModifyAvionPanel extends JPanel{
    
    JLabel lId = new JLabel("ID : ");
    JTextField tId ;
    
    JLabel lModel = new JLabel("Model : ");
    JComboBox<String> comboModel = new JComboBox<>();
    
    JLabel lSpeed = new JLabel("Speed : ");
    JTextField tSpeed ;
    
    JLabel lReservoir = new JLabel("Reservoir : ");
    JTextField tReservoir ;
    
    JButton confirm = new JButton("Confirm");
    
    public ModifyAvionPanel(Avion a)
    {
        tId = new JTextField(a.getMatricule());
        tId.setEditable(false);
        tSpeed = new JTextField(""+a.getSpeed());
        tReservoir = new JTextField(""+a.getReservoir());
        
        lId.setBounds(20, 30, 100, 30);
        tId.setBounds(80, 30, 190, 30);
        
        lModel.setBounds(20, 70, 100, 30);
        comboModel.setBounds(80, 70, 190, 30);
        
        comboModel.addItem("pa28");
        comboModel.addItem("q400");
        comboModel.addItem("boe789");
        
        comboModel.setSelectedItem(a.getModel());
        
        lSpeed.setBounds(20, 110, 100, 30);
        tSpeed.setBounds(80, 110, 190, 30);
        
        lReservoir.setBounds(20, 150, 100, 30);
        tReservoir.setBounds(80, 150, 190, 30);
        
        confirm.setBounds(180, 190, 90, 30);
        
        
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = FlyService.avions.indexOf(a);
                FlyService.avions.get(index).setModel(""+comboModel.getSelectedItem());
                FlyService.avions.get(index).setSpeed(Float.parseFloat(tSpeed.getText()));
                FlyService.avions.get(index).setReservoir(Float.parseFloat(tReservoir.getText()));
                
                MF.cv.ap.MatchAvion(FlyService.avions);
            }
        });
                
        setLayout(null);
        add(lId);
        add(tId);
        add(lModel);
        add(comboModel);
        add(lSpeed);
        add(tSpeed);
        add(lReservoir);
        add(tReservoir);
        add(confirm);
        
    }
    
}
