/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Classes.Avion;
import Classes.FlyState;
import Mesh.MathUtils;
import Panels.MainPanel;
import Panels.PlaneInfo;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Alif computer
 */
public class GUIUtils {
    
    public static ArrayList<GuiTexture> checkBounds(List<GuiTexture> l)
    {
        ArrayList<GuiTexture> hovered = new ArrayList<>();
        for (GuiTexture gui : l)
        {
            if (checkBound(gui))
            {
                hovered.add(gui);
            }
        }
        return hovered;
    }
    
    private static boolean mouseButton1 = false;
    
    public static void doSomthAboutClick(List<GuiTexture> l)
    {
        if(Mouse.isButtonDown(0)  && !mouseButton1 )
        {
            try {
                if(checkBounds(l).get(0).id == 1)
                {
                    renderengine.RenderEngine.guis.get(0).visible = !renderengine.RenderEngine.guis.get(0).visible;
                    renderengine.RenderEngine.guis.get(2).visible = !renderengine.RenderEngine.guis.get(2).visible;
                    renderengine.RenderEngine.guis.get(3).visible = !renderengine.RenderEngine.guis.get(3).visible;
                    renderengine.RenderEngine.guis.get(4).visible = !renderengine.RenderEngine.guis.get(4).visible;
                    renderengine.RenderEngine.guis.get(5).visible = !renderengine.RenderEngine.guis.get(5).visible;
                    renderengine.RenderEngine.guis.get(6).visible = !renderengine.RenderEngine.guis.get(6).visible;
                    
                    if(renderengine.RenderEngine.guis.get(5).visible)
                    {
                        renderengine.RenderEngine.MF.up.p3.show();
                    }else{
                        renderengine.RenderEngine.MF.up.p3.noshow();
                    }
                }
                
                if(checkBounds(l).get(0).id == 7)
                {
                    renderengine.RenderEngine.MF.up.switchTwice();
                    renderengine.RenderEngine.MF.up.p3.noshow();
                    
                    renderengine.RenderEngine.guis.get(0).visible = false;
                    renderengine.RenderEngine.guis.get(2).visible = false;
                    renderengine.RenderEngine.guis.get(3).visible = false;
                    renderengine.RenderEngine.guis.get(4).visible = false;
                    renderengine.RenderEngine.guis.get(5).visible = false;
                    renderengine.RenderEngine.guis.get(6).visible = false;
                }
                
                if(checkBounds(l).get(1).id == 6)
                {
                    renderengine.RenderEngine.MF.up.switchTwice();
                    renderengine.RenderEngine.MF.up.p3.noshow();
                    
                    renderengine.RenderEngine.guis.get(0).visible = false;
                    renderengine.RenderEngine.guis.get(2).visible = false;
                    renderengine.RenderEngine.guis.get(3).visible = false;
                    renderengine.RenderEngine.guis.get(4).visible = false;
                    renderengine.RenderEngine.guis.get(5).visible = false;
                    renderengine.RenderEngine.guis.get(6).visible = false;
                    
                    String infos[] = renderengine.RenderEngine.MF.up.p3.getInfo();
                    renderengine.RenderEngine.MF.up.p1.ti.addNewInfo(new PlaneInfo("./res/airplaines/"+infos[1]+".png",infos[0],infos[3],"New",infos[2]));
                    renderengine.RenderEngine.MF.up.p1.rep();
                    renderengine.RenderEngine.stub.ajouterAvion(new Avion(infos[0], infos[1], FlyState.New,Float.parseFloat(infos[3]),Float.parseFloat(infos[2])));
                }
                
            } catch (Exception e) {
            }
            
        }
        mouseButton1 = Mouse.isButtonDown(0);
    }
    
    public static void doSomthAboutHover(List<GuiTexture> l)
    {
        ArrayList<GuiTexture> hov = checkBounds(l) ;
        
        boolean menu = false ;
        boolean save = false ;
        boolean retour = false ;
        
        
        
        if (hov.size() != 0)
        {
            if(hov.get(0).id == 1)
            {
                menu = true;
            }
            try {
                if(hov.get(1).id == 6)
                {
                    save = true;
                }
            } catch (Exception e) {
            }
            if(hov.get(0).id == 7)
            {
                retour = true;
            }
            
        }
        
        if(menu)
        {
            renderengine.RenderEngine.guis.get(1).setScale(new Vector2f(0.042f,1.77f* 0.042f));
        }else{
            renderengine.RenderEngine.guis.get(1).setScale(new Vector2f(0.04f,1.77f* 0.04f));
        }
        
        if(retour)
        {
            renderengine.RenderEngine.guis.get(7).setScale(new Vector2f(0.042f,1.77f* 0.042f));
        }else{
            renderengine.RenderEngine.guis.get(7).setScale(new Vector2f(0.04f,1.77f* 0.04f));
        }
        
        if(save)
        {
            renderengine.RenderEngine.guis.get(6).setScale(new Vector2f(0.042f,1.77f* 0.042f));
        }else{
            renderengine.RenderEngine.guis.get(6).setScale(new Vector2f(0.05f,1.77f* 0.05f));
        }
        
    }
    
    
    public static boolean checkBound(GuiTexture  gui)
    {
        float x = Mouse.getX();
        float y = 848 - Mouse.getY();
        
        x = x - 1578 / 2  ;
        y = (y - 848 / 2)*-1 ;
        
        x = x / (1578 / 2 );
        y = y / (848 / 2 );
        
        
        float gui_x_u_l = gui.getPosition().x - gui.getScale().x;
        float gui_y_u_l  = gui.getPosition().y + gui.getScale().y;
        
        float gui_x_d_r = gui.getPosition().x + gui.getScale().x;
        float gui_y_d_r  = gui.getPosition().y - gui.getScale().y;
        
        if (x > gui_x_u_l && x < gui_x_d_r && y < gui_y_u_l && y > gui_y_d_r )
        {
            return true ;
        }else{
            return false;
        }
         
        //System.out.println(gui_x_u_l+" "+gui_y_u_l+" / "+gui_x_d_r+" "+gui_y_d_r+" / "+x+" "+y);
        
    }
}
