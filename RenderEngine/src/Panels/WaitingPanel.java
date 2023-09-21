/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import GUI.TextGenerator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class WaitingPanel extends JPanel{
    BufferedImage image;
    public String Text = "- Loading Maps Files - ";
    int i = 0 ;
    public WaitingPanel()
    {
        new Thread()
        {
            public void run()
            {
                while (true)
                {
                    readImage(i++);
                    repaint();

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                    if (i == 249)
                    {
                        i = 0 ;
                    }
                }
            }
        }.start();
    }
    
    void readImage(int index)
    {
        try {
            image = ImageIO.read(new File("./res/GUI/waiting/frame_"+String.format("%03d", index) +"_delay-0.04s.png"));
        } catch (IOException ex) {
            Logger.getLogger(WaitingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2draw = (Graphics2D) g;

        g2draw.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2draw.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2draw.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2draw.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2draw.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2draw.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2draw.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2draw.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2draw.setTransform(AffineTransform.getScaleInstance(1, 1));
        g2draw.drawImage(image, 0, 0, 800 , 600, null);  
        g2draw.drawImage(TextGenerator.genText(Text, 32,new Color(223, 253, 255)), 200, 0,null);  
        
        
    }
    
}
