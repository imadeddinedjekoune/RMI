/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import Classes.Avion;
import Classes.FlyState;
import Classes.Time;
import GUI.MainFrame;
import GUIMap.Maps.GoogleMapsProjection2;
import GUIMap.Maps.MapLoader;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import javax.swing.UIManager;

/**
 *
 * @author Alif computer
 */
public class ServeurRMI 
{
    public static MainFrame MF ;
    public static Time actuelTime = new Time(2022, 12, 28, 8, 15, 55);
    public static int sleep_amount = 10 ;
    
    public static void main(String[] args) 
    {
        
        try {
            LocateRegistry.createRegistry(1010);
            FlyService fs = new FlyService();
            Naming.rebind("rmi://localhost:1010/FR", fs);
            
        } catch (Exception e) {
        }
        
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
            UIManager.put( "Table.showHorizontalLines", true );
            UIManager.put( "Table.showVerticalLines", true );
            UIManager.put( "Table.showTrailingVerticalLine", true );
            UIManager.put( "Table.selectionBackground", new Color(0,0,0,30) );
            UIManager.put( "Table.selectionForeground",new Color(0,0,0));
            
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
            
        }
        
        FlyService.avions.add(new Avion("Mat-01", "boe789", FlyState.New, 800,26000));
        
        MF = new MainFrame();
        
      
        new Thread(){
            public void run()
            {
                while (true)
                {
                    MF.cv.ap.MatchAvion(FlyService.avions);
                    //debug();
                    
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        
        
        new Thread(){
            public void run()
            {
                while (true)
                {
                    actuelTime = new Time(actuelTime.ajouterTemp(1000).getTime());
                    
                    if(MF.cv.tp.getSelectedIndex() == 2
                       && MF.cv.vp.tp.getSelectedIndex() == 0
                            )
                    {
                        MF.cv.vp.avp.time.setText(""+actuelTime);
                    }
                    
                    try {
                        Thread.sleep(sleep_amount);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        
        
    }
    
    
}
