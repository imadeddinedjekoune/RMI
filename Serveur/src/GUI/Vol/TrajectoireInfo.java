/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Vol;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Alif computer
 */
public class TrajectoireInfo extends JPanel {
    JLabel NomAr ;
    public TrajectoireInfo(String str)
    {
        NomAr = new JLabel(str, SwingConstants.CENTER);
        NomAr.setBounds(0, 0, 340, 30);
        NomAr.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 16));
        NomAr.setBorder(BorderFactory.createEtchedBorder());
        setLayout(null);
        add(NomAr);
    }
    
}
