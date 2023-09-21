/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Classes.Avion;
import Panels.PlaneInfo;
import Panels.TableInfo;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Alif computer
 */
public class CheckPanels 
{
    public static void checkAvionPanel()
    {
        ArrayList<Avion> serverAvions = null ;
        try {
            serverAvions = renderengine.RenderEngine.stub.getAllAvions();
            
            for (int i = 0 ; i < renderengine.RenderEngine.MF.up.p1.ti.plans.size() ; i++)
            {
                Avion actuelle = new Avion(renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).aID.getMatricule());
                
                if(!serverAvions.contains(actuelle))
                {
                    renderengine.RenderEngine.MF.up.p1.ti.removeInfo(renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).getId());
                }else{
                    
                    int index = serverAvions.indexOf(actuelle);
                    if(
                            renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).aID.getSpeed() != serverAvions.get(index).getSpeed() ||
                            renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).aID.getReservoir()!= serverAvions.get(index).getReservoir() ||
                            !renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).aID.getModel().equals(serverAvions.get(index).getModel()) 
                        )
                    {
                        ArrayList<PlaneInfo> pla = new ArrayList<>();
                        
                        for (int k = 0 ; k < renderengine.RenderEngine.MF.up.p1.ti.plans.size() ; k++)
                        {
                            if ( i == k)
                            {
                                PlaneInfo temp = renderengine.RenderEngine.MF.up.p1.ti.plans.get(k);
                                pla.add(new PlaneInfo("./res/airplaines/"+serverAvions.get(index).getModel()+".png", temp.saved[1], ""+serverAvions.get(index).getSpeed(), temp.saved[3], ""+serverAvions.get(index).getReservoir()));
                            }else{
                                pla.add(renderengine.RenderEngine.MF.up.p1.ti.plans.get(k));
                            }
                        }
                        
                        renderengine.RenderEngine.MF.up.p1.ti = new TableInfo(pla);
                        renderengine.RenderEngine.MF.up.p1.ti.repaint();
                        renderengine.RenderEngine.MF.up.p1.rep();
                        
                        renderengine.RenderEngine.MF.up.p1.ti.plans.get(i).aID.setSpeed(serverAvions.get(index).getSpeed());
                    }
                    
                }
            }
            
        } catch (Exception e) {
        }
        
        
        
    }
}
