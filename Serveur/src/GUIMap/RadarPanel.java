/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Alif computer
 */
public class RadarPanel extends JPanel{
    
    float rotation = 0;
    
    public RadarPanel()
    {
        setBackground(new Color(25,43,21));
        new Thread(){
            public void run()
            {
                while(true)
                {
                    rotation = rotation +1f ;
                    repaint();
                    try{
                        sleep(10);
                    }catch(Exception e)
                    {
                        
                    }
                }
            }
        }.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        
        
        for(int i = 0 ; i < 15 ; i++ )
        {
            g.setColor(new Color(111,152,91));
            g.drawLine(0, i*45, 650, i*45);
        }
        for(int i = 0 ; i < 15 ; i++ )
        {
            g.setColor(new Color(111,152,91,128));
            g.drawLine(i*45, 0, i*45, 650);
        }
        
        g.setColor(new Color(147,239,79));
        g.drawLine(7*45, 0, 7*45, 650);
        g.setColor(new Color(147,239,79));
        g.drawLine(0, 7*45, 650, 7*45);
        
        
        drawCircle(500,2,g);
        drawCircle(400,2,g);
        drawCircle(300,2,g);
        drawCircle(200,2,g);
        drawCircle(100,2,g);
        
        
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int s = this.getSize().width / 2 -5 ;
        //g2d.rotate(1);
        g2d.rotate(Math.toRadians(rotation), s, s);
        g2d.drawLine(100, 100, s, s);
        
        
        
    }
    
    
    private void drawCircle(int size , int thickness , Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int innerSize = size - (2 * thickness);

        Shape outer = new Ellipse2D.Double(0, 0, size, size);
        Shape inner = new Ellipse2D.Double(thickness, thickness, innerSize, innerSize);

        Area circle = new Area( outer );
        circle.subtract( new Area(inner) );

        int x = (this.getSize().width - size) / 2-5;
        int y = (this.getSize().height - size) / 2-5;
        g2d.translate(x, y);

        g2d.setColor(new Color(147,239,79));
        g2d.fill(circle);
        g2d.dispose();
    }
    
    
}
