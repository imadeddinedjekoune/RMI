/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap.Maps;

import GUIMap.ImageUtils;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author Alif computer
 */
public class MapLoader {
    
    public static BufferedImage[][] grid = new BufferedImage[3][3];
    public static int x = 15 , y = 18 ;
    public static byte [][][]map = MapLoader.getBB();
    
    private static BufferedImage initGrayImage()
    {
        BufferedImage gi = new BufferedImage(1024*2,1024*2, BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0 ; i < 1024*2 ; i++)
        {
            for (int j = 0 ; j < 1024*2 ; j++)
            {
                gi.setRGB(i, j, new Color(128, 128, 128).getRGB());
            }
        }
        
        return gi;
    }
    
    
    public static void loadMapGrid() 
    {
        ImageUtils.init();
        grid[1][1] = getByIndex(x, y);
        
        new Thread()
        {
            public void run() {
                grid[1][0] = getByIndex(x, y-1);
            }
        }.start();
         
        new Thread()
        {
            public void run() {
                grid[1][2] = getByIndex(x, y+1);
            }
        }.start();
         
        new Thread()
        {
            public void run() {
                grid[0][1] = getByIndex(x-1, y);
            }
        }.start();
         
        new Thread()
        {
            public void run() {
                grid[2][1] = getByIndex(x+1, y);
            }
        }.start();
        
        
    }
    
    public static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            System.out.println("here");
            throw new RuntimeException(e);
        }
    }
    
    
    public static BufferedImage getByIndex(int x , int y)
    {
        BufferedImage img1 = MapLoader.createImageFromBytes(map[x][y]);
        BufferedImage img2 = MapLoader.createImageFromBytes(map[x][y+1]);
        BufferedImage img3 = MapLoader.createImageFromBytes(map[x+1][y]);
        BufferedImage img4 = MapLoader.createImageFromBytes(map[x+1][y+1]);
        
        BufferedImage imgT = new BufferedImage(1024*2, 1024*2, BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0 ; i < 1024 ; i++)
        {
            for (int j = 0 ; j < 1024 ; j++)
            {
                imgT.setRGB(i, j, img1.getRGB(i, j));
            }
        }
        
        for (int i = 0 ; i < 1024 ; i++)
        {
            for (int j = 0 ; j < 1024 ; j++)
            {
                imgT.setRGB(i+1024, j, img2.getRGB(i, j));
            }
        }
        
        for (int i = 0 ; i < 1024 ; i++)
        {
            for (int j = 0 ; j < 1024 ; j++)
            {
                imgT.setRGB(i, j+1024, img3.getRGB(i, j));
            }
        }
        for (int i = 0 ; i < 1024 ; i++)
        {
            for (int j = 0 ; j < 1024 ; j++)
            {
                imgT.setRGB(i+1024, j+1024, img4.getRGB(i, j));
            }
        }
        
        
        return imgT;
    }
    
    public static byte[][][] getBB()
    {
        byte [][] b_up_left = (byte[][]) ReadObjectFromFile("./maps/image_up_left");
        byte bb_up_left[][][] = new byte[22][15][1024];
        
        for (int i = 0 ; i < 22 ; i++)
        {
            for (int j = 0 ; j < 15 ; j++ )
            {
                bb_up_left[i][j] = b_up_left[i*15+j];
            }
        }
        
        byte [][] b_up_right = (byte[][]) ReadObjectFromFile("./maps/image_up_right");
        byte bb_up_right[][][] = new byte[22][15][1024];
        
        for (int i = 0 ; i < 22 ; i++)
        {
            for (int j = 0 ; j < 15 ; j++ )
            {
                bb_up_right[i][j] = b_up_right[i*15+j];
            }
        }
        
        
        byte [][] b_down_left = (byte[][]) ReadObjectFromFile("./maps/image_down_left");
        byte bb_down_left[][][] = new byte[21][15][1024];
        
        for (int i = 0 ; i < 21 ; i++)
        {
            for (int j = 0 ; j < 15 ; j++ )
            {
                bb_down_left[i][j] = b_down_left[i*15+j];
            }
        }
        
        byte [][] b_down_right = (byte[][]) ReadObjectFromFile("./maps/image_down_right");
        byte bb_down_right[][][] = new byte[21][15][1024];
        
        for (int i = 0 ; i < 21 ; i++)
        {
            for (int j = 0 ; j < 15 ; j++ )
            {
                bb_down_right[i][j] = b_down_right[i*15+j];
            }
        }
        
        
        
        byte bb_totale[][][] = new byte[21+21][15+15][1024];
        
        for (int i = 0 ; i < 22 ; i++ )
        {
            for (int j = 0 ; j < 15 ; j++)
            {
                bb_totale[i][j] = bb_up_left[i][j];
            }
        }
        
        for (int i = 0 ; i < 22 ; i++ )
        {
            for (int j = 15 ; j < 15+15 ; j++)
            {
                bb_totale[i][j] = bb_up_right[i][j-15];
            }
        }
        
        for (int i = 22 ; i < 21+21 ; i++ )
        {
            for (int j = 0 ; j < 15 ; j++)
            {
                bb_totale[i][j] = bb_down_left[i-22+1][j];
            }
        }
        
        for (int i = 22 ; i < 21+21 ; i++ )
        {
            for (int j = 15 ; j < 15+15 ; j++)
            {
                bb_totale[i][j] = bb_down_right[i-22+1][j-15];
            }
        }
        return bb_totale;
    }
    
    public static Object ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
