/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Vol;

import GUIMap.Maps.ImagePanelZ8;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Alif computer
 */
public class MapVol extends JPanel{
    public ImagePanelZ8 ip8 = new ImagePanelZ8();
    public MapVol()
    {
        setLayout(new BorderLayout());
        add(ip8,BorderLayout.CENTER);
    }
    
}
