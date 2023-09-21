/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package renderengine;

import Panels.MainFrame;
import Utils.AvionGestionFiles;
import GUI.GUIUtils;
import static GUI.GUIUtils.checkBounds;
import GUI.GuiRenderer;
import GUI.GuiTexture;
import GUI.TextGenerator;
import GUIMap.Maps.MapLoader;
import Mesh.AirPlain;
import Mesh.Camera;
import Mesh.Light;
import Mesh.MathUtils;
import Mesh.Mesh;
import Mesh.MeshLoader;
import Mesh.Mouvement;
import Mesh.Texture;
import Panels.MainPanel;
import Panels.WaitingFrame;
import RMI.IFlyRemote;
import Shader.ShaderProgram;
import SkyBox.SkyBoxRenderer;
import Utils.CheckPanels;
import Utils.RendererCore;
import Window.DisplayManager;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Alif computer
 */
public class RenderEngine 
{
    public static MainFrame MF ;
    public static GUIMap.MainFrame mfMap;
    public static WaitingFrame wf;
    public static List<GuiTexture> guis;
    public static ShaderProgram shader;
    public static AirPlain a1 ;
    public static IFlyRemote stub = null ;
    
    public static void main(String[] args) 
    {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
            UIManager.put( "Table.showHorizontalLines", true );
            UIManager.put( "Table.showVerticalLines", true );
            UIManager.put( "Table.showTrailingVerticalLine", true );
            
        } catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
        
        
        wf = new WaitingFrame();
        
        
        MF = new MainFrame();
        wf.toFront();
        mfMap = new GUIMap.MainFrame();
        
        DisplayManager.createDisplay();
        AvionGestionFiles.init();
        for (int i = 0 ; i < AvionGestionFiles.stuct.size() ; i++)
        {
            MF.up.p3.comboLanguage.addItem(AvionGestionFiles.getNameIn(i));
            MF.up.p1.qap.cp.addItem(AvionGestionFiles.getNameIn(i));
        }
        
        MF.up.p3.repaint();
        MF.up.p1.qap.repaint();
        
        
        SkyBoxRenderer sky = new SkyBoxRenderer();
        shader = new ShaderProgram("./res/shaders/vertexShader.txt","./res/shaders/fragmentShader.txt" );
        
        Matrix4f projection = MathUtils.createProjectionMatrix() ;
        Camera c = new Camera();
        Light l1 = new Light(new Vector3f(-50,10,0), new Vector3f(1f,1f,1f));
        Light l2 = new Light(new Vector3f(50,10,0), new Vector3f(1f,1f,1f));
        Light l3 = new Light(new Vector3f(0,10,10), new Vector3f(1f,1f,1f));
        // init shader
        shader.start();
        
        shader.loadInt("stexture1", 0);
        shader.loadInt("enviroMap", 1);
        
        shader.loadMatrix("projectionMatrix", projection);
        
        shader.loadVector("lightPosition[0]", l1.getPosition());
        shader.loadVector("lightColour[0]", l1.getColour());
        shader.loadVector("lightPosition[1]", l2.getPosition());
        shader.loadVector("lightColour[1]", l2.getColour());
        shader.loadVector("lightPosition[2]",l3.getPosition());
        shader.loadVector("lightColour[2]", l3.getColour());
        shader.loadVector("lightPosition[3]",new Vector3f(0, 0, 0));
        shader.loadVector("lightColour[3]", new Vector3f(0, 0, 0));
        shader.stop();
        
        
        RendererCore.initRenderer();
        
        
        guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(0,false,Texture.loadTexture_("image"), new Vector2f(-0.7f, 0.2f), new Vector2f(0.25f, 0.5f));
        GuiTexture gui2 = new GuiTexture(1,true,Texture.loadTexture_("menu-bar"), new Vector2f(-0.9f, 0.9f), new Vector2f(0.04f,1.77f* 0.04f));
        BufferedImage id = TextGenerator.genText("ID ", 120) ;
        GuiTexture gui3 = new GuiTexture(2,false,Texture.loadTexture(id), new Vector2f(-0.85f,0.6f), new Vector2f(0.025f*(id.getWidth()/id.getHeight()), 0.026f));
        BufferedImage model = TextGenerator.genText("Model ", 120) ;
        GuiTexture gui4 = new GuiTexture(3,false,Texture.loadTexture(model), new Vector2f(-0.85f,0.4f), new Vector2f(0.025f*(model.getWidth()/model.getHeight()), 0.029f));
        BufferedImage speed = TextGenerator.genText("Speed ", 50) ;
        GuiTexture gui5 = new GuiTexture(4,false,Texture.loadTexture(speed), new Vector2f(-0.85f,0.2f), new Vector2f(0.025f*(speed.getWidth()/speed.getHeight()), 0.026f));
        BufferedImage res = TextGenerator.genText("Esnc ", 120) ;
        GuiTexture gui6 = new GuiTexture(5,false,Texture.loadTexture(res), new Vector2f(-0.85f,0.0f), new Vector2f(0.024f*(res.getWidth()/res.getHeight()), 0.028f));
        GuiTexture gui7 = new GuiTexture(6,false,Texture.loadTexture_("add"), new Vector2f(-0.5f, -0.2f), new Vector2f(0.04f,1.77f* 0.04f));
        GuiTexture gui8 =new GuiTexture(7,true,Texture.loadTexture_("annuler"), new Vector2f(0.9f, 0.9f), new Vector2f(0.04f,1.77f* 0.04f));
        
        guis.add(gui);
        guis.add(gui2);
        guis.add(gui3);
        guis.add(gui4);
        guis.add(gui5);
        guis.add(gui6);
        guis.add(gui7);
        guis.add(gui8);
        
        
        GuiRenderer guiRenderer = new GuiRenderer();
        
        MF.setEnabled(true);

        wf.dispatchEvent(new WindowEvent(wf, WindowEvent.WINDOW_CLOSING));
        
        
        new Thread()
        {
            public void run()
            {
                while(true)
                {
                    CheckPanels.checkAvionPanel();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        
        while(!Display.isCloseRequested () )
        {	
            if(MainPanel.active){
                RendererCore.prepareRenderer();
                c.move();

                shader.start();

                shader.loadMatrix("viewMatrix", MathUtils.createViewMatrix(c));

                if (a1 != null)
                {
                    a1.getT().Activate(0);
                    a1.getT().bind();

                    GL13.glActiveTexture(GL13.GL_TEXTURE1);
                    GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP,sky.getTextureID());

                    RendererCore.Render(a1.getMesh(),shader.getNbAttribs());
                }

                shader.stop();

                sky.render(c);
                guiRenderer.render(guis);
                

                if (MainPanel.choice != -1)
                {
                    a1 = new AirPlain((AvionGestionFiles.stuct.get(MainPanel.choice-1).getNom()),
                            AvionGestionFiles.stuct.get(MainPanel.choice-1).getNom(),
                            AvionGestionFiles.stuct.get(MainPanel.choice-1).getScale());
                    a1.affectshader(shader);
                    MainPanel.choice = -1;
                }
                
                GUIUtils.doSomthAboutHover(guis);
                GUIUtils.doSomthAboutClick(guis);
            }
            DisplayManager.updateDisplay();
            
        }
        DisplayManager.closeDisplay();
        
        
        
    }

    public static void ScreenShot(Canvas c) throws Exception {

        Robot robot = new Robot();
        int xC = (int) c.getLocationOnScreen().getX();
        //getLocationOnScreen donne la position du canvas
        //(l'intérieur de la fenêtre) sur l'écran
        int yC = (int) c.getLocationOnScreen().getY();
        int widthC = c.getWidth();
        int heightC = c.getHeight();

        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(xC, yC, widthC, heightC));

        ImageIO.write(screenShot, "JPG", new File("ScreenShot"+0+".jpg"));
        System.out.println("Photo prise");
        }
    
    public static void saveImg(BufferedImage img , String name)
    {
        File outputfile = new File(name+".png");
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(RenderEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
