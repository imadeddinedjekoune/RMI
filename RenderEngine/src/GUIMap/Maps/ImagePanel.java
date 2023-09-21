/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap.Maps;

import GUIMap.ImageUtils;
import GUIMap.Maps.MapLoader;
import static GUIMap.Maps.MapLoader.getByIndex;
import static GUIMap.Maps.MapLoader.grid;
import static GUIMap.Maps.MapLoader.x;
import static GUIMap.Maps.MapLoader.y;
import GUIMap.Maps.MapPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import static GUIMap.Maps.GoogleMapsProjection2.getLatLonFromXYInMap;
import static GUIMap.Maps.GoogleMapsProjection2.getXYFromLatLonInMap;
import java.util.ArrayList;


/**
 *
 * @author Alif computer
 */
public class ImagePanel extends JPanel{
    
    public static float tx = -1024+800/2, ty = -1024+800/2 ;
    Point mousePt;
    BufferedImage image ;
    BufferedImage copie;
    public static ArrayList<Object[]> avions = new ArrayList<>();

    public ImagePanel() {
        
       image = MapLoader.grid[1][1];
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }
        });
        
        this.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) 
                {
                    int x = (-(int) ty+e.getY()*800/640) ;
                    int y = (-(int) tx +e.getX()*800/640 ) ;
                    
                    System.out.println(x+" "+y);
                    
                    x = x / 1024 ;
                    y = y / 1024 ;
                    
                    int xpos = (-(int) tx +e.getX()*800/640 )%1024 ;
                    int ypos = (-(int) ty +e.getY()*800/640 )%1024 ;
                    
                    double mercator[] = getLatLonFromXYInMap(xpos,ypos,MapLoader.y+y,MapLoader.x+x);
                    System.out.println(mercator[0]+" "+mercator[1]);
                    
                    //int posZ8[] = GoogleMapsProjection2.getXYFromLatLonInMapZ8(mercator[0], mercator[1], 8);
                    renderengine.RenderEngine.mfMap.mp.switchToZ8();
                    
                }
           }

           @Override
           public void mousePressed(MouseEvent e) {
           }

           @Override
           public void mouseReleased(MouseEvent e) {
           }

           @Override
           public void mouseEntered(MouseEvent e) {
           }

           @Override
           public void mouseExited(MouseEvent e) {
           }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() 
        {
            
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                
                tx = tx+dx;
                ty = ty+dy;
                
                
                mousePt = e.getPoint();
                repaint();
            }
        });
        
       
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
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
   
        if(tx > 0)
        {
            image = MapLoader.grid[1][0];
            MapLoader.y = MapLoader.y-1;
            
            MapLoader.grid[1][2] = MapLoader.grid[1][1] ;
            MapLoader.grid[1][1] = MapLoader.grid[1][0];
            
            new Thread()
            {
                public void run() {
                    grid[1][0] = getByIndex(x, y-1);
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
         
            
            tx = -1024;
        }
                
        
        if(tx < -1248 )
        {
            image = MapLoader.grid[1][2];
            MapLoader.y = MapLoader.y+1;
            
            
            MapLoader.grid[1][0] = MapLoader.grid[1][1] ;
            MapLoader.grid[1][1] = MapLoader.grid[1][2];
            
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
         
            
            tx = -1024+800;
        }
                
        if(ty > 0)
        {
            image = MapLoader.grid[0][1];
            MapLoader.x -= 1 ;
            
            
            MapLoader.grid[2][1] = MapLoader.grid[1][1];
            MapLoader.grid[1][1] = MapLoader.grid[0][1] ;
            
            new Thread()
            {
                public void run() {
                    grid[0][1] = getByIndex(x-1, y);
                }
            }.start();
            
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
            
            
            ty = -1024 ;
        }
        
        if(ty < -1248 )
        {
            image = MapLoader.grid[2][1];
            
            MapLoader.x += 1 ;
            
            
            MapLoader.grid[0][1] = MapLoader.grid[1][1];
            MapLoader.grid[1][1] = MapLoader.grid[2][1] ;
            
            
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
                    grid[2][1] = getByIndex(x+1, y);
                }
            }.start();
            
            ty = -1024+800; 
            
        }
        
        
        int x = 711 ;
        int y = 826 ;
        
        int x_screen = (x + (int)tx);
        int y_screen = (y + (int)ty);
        
        
        
        copie = ImageUtils.copyImage(image);
        BufferedImage croped = ImageUtils.cropImage(copie, new Rectangle(-(int) tx,-(int) ty, 800, 800));
        
        if (MapPanel.selectedPanel == 1 ){
            for(int j = 0 ; j < avions.size() ; j++)
            {
                Object infos[] = avions.get(j);
                double latlon[] = (double[]) infos[0];
                int zoom = (int) infos[1];
                drawData(latlon[0] , latlon[1],zoom);
                //System.out.println(latlon[0] +" , "+ latlon[1]);
            }
        }
        
        
        
        
        g2draw.drawImage(croped, 0,0 ,null);  
        g2draw.dispose();
    }
    
    public void drawData(double lat,double lon,int zoom)
    {
        int ret[] = getXYFromLatLonInMap(lat,lon,zoom);
        
        
        int y = ret[3];
        int x = ret[2];
        
        // etape de verification
        
        int stepx = 0 , stepy = 0 ;
        boolean draw = false ;
        
        if (ret[1] == MapLoader.x && ret[0] == MapLoader.y)
        {
            draw = true;
        }
        
        if(ret[1] == MapLoader.x && ret[0]-1 == MapLoader.y)
        {
            stepx = 1 ;
            draw = true;
        }
        if(ret[1]-1 == MapLoader.x && ret[0] == MapLoader.y)
        {
            stepy = 1 ;
            draw = true;
        }
        if(ret[1]-1 == MapLoader.x && ret[0]-1 == MapLoader.y)
        {
            stepx = 1 ;
            draw = true;
            stepy = 1 ;
        }
        
        
        if(draw)
        {
            ImageUtils.drawPixel(copie, (x+stepx*1024) ,(y+stepy*1024));
        }
        
    }
    
    public void rep()
    {
        removeAll();
        repaint();
        revalidate();
    }
    
 
    
}
