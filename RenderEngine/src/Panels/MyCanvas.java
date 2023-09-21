/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class MyCanvas extends Canvas {
    public BufferedImage img;
    
    public MyCanvas()
    {
        setSize(1280, 720);
        setVisible(true);
        setFocusable(false);
    }
    
    public void exportImage() 
    {
        BufferedImage image = new  BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        paintAll(graphics);
        graphics.dispose();
        img = image;
    }
    
}
