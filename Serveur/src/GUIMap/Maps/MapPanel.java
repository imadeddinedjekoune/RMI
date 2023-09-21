/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap.Maps;


import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class MapPanel extends JPanel 
{
    public ImagePanel ip ;
    public static int selectedPanel = 0 ;
    public ImagePanelZ8 ip8 ;
    public static Object[] aePos = null;
    public static boolean showAr = true ; 
    public MapPanel ()
    {
        MapLoader.loadMapGrid();
        
        ip = new ImagePanel();
        ip8 = new ImagePanelZ8();
        setLayout(null);
        
        
        ip8.setBounds(0, 0,640, 640);
        ip.setBounds(0, 0,640, 640);
        
        add(ip8);
        
    }
    
    
    public void switchToZ8()
    {
        removeAll();
        
        add(ip8);
        selectedPanel = 0 ;
        repaint();
        revalidate();
    }
    
    public void switchToZ11()
    {
        removeAll();
        add(ip);
        ip.rep();
        selectedPanel = 1 ;
        repaint();
        revalidate();
    }
    
    
    
    
}
