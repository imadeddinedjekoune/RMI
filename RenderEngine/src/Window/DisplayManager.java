/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Window;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author Alif computer
 */
public class DisplayManager {
    private static final int WIDTH = 1580;
    private static final int HEIGHT = 850;
    private static final int FPS_CAP = 120;

    public static void createDisplay(){

        ContextAttribs attribs = new ContextAttribs(3,2)
        .withForwardCompatible(true)
        .withProfileCore(true);

        try 
        {	
            Display.setLocation(-1000, -1000);
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat().withSamples(2), attribs);
            Display.setTitle("Test");
            
            Display.setParent(renderengine.RenderEngine.MF.up.p3.c);

            //renderengine.RenderEngine.MF.toFront();
            
        } catch (LWJGLException e){

            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }
    public static void updateDisplay()
    {
        Display.sync( FPS_CAP );
        Display.update();
    }
    public static void closeDisplay()
    {
        Display.destroy();
    }
}
