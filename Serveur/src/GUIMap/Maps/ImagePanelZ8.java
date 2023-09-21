/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap.Maps;

import GUIMap.ImageUtils;
import static GUIMap.Maps.GoogleMapsProjection2.getLatLonFromXYInMap;
import static GUIMap.Maps.GoogleMapsProjection2.getXYFromLatLonInMap;
import static GUIMap.Maps.ImagePanel.avions;
import GUIMap.Maps.MapLoader;
import static GUIMap.Maps.MapLoader.getByIndex;
import static GUIMap.Maps.MapLoader.grid;
import GUIMap.Maps.MapPanel;
import RMI.FlyService;
import java.awt.Color;
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
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class ImagePanelZ8 extends JPanel
{
    static BufferedImage img = null ;
    public ArrayList<Object[]> avions = new ArrayList<>();
    public static float rotate = 0  ;
    
    public static float tx = -1024*2-200, ty = -1024*2+400 ;
    Point mousePt;
    
    public static void setTXY(double lat,double lon)
    {
        int t[] = GoogleMapsProjection2.getXYFromLatLonInMapZ8(lat,lon, 8);
        tx = -t[0] + 400;
        ty = -t[1] + 400;
    }
    
    public ImagePanelZ8()
    {
        try {
            Instant start = Instant.now();
            img = ImageIO.read(new File("MapZoom8.png"));
            
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        this.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) 
                {
                    //System.out.println(e.getX()+" , "+e.getY());
                    double dd[] = GoogleMapsProjection2.getLatLonFromXYInMapZ8(-(int)tx+e.getX(), -(int)ty+e.getY());
                    int t [] = GoogleMapsProjection2.getXYFromLatLonInMap(dd[0], dd[1], 11);
                    

                    MapLoader.x = t[1] ;
                    MapLoader.y = t[0] ;
                    
                    System.out.println(t[1]+" "+t[0]);
                    
                    
                    //System.out.println("x : "+MapLoader.x+" , y : "+MapLoader.y);
                   
                    MapLoader.loadMapGrid();
                    RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip = new ImagePanel();
                    

                    RMI.ServeurRMI.MF.cv.aep.addPanel.mp.ip.setBounds(0, 0,640, 640);

                    RMI.ServeurRMI.MF.cv.aep.addPanel.mp.switchToZ11();
                }else{
                    if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3)
                    {
                        double dd[] = GoogleMapsProjection2.getLatLonFromXYInMapZ8(-(int)tx+e.getX()*800/640, -(int)ty+e.getY()*800/640);
                        MapPanel.aePos = new Object[]{new double[]{dd[0], dd[1]},8};
                        RMI.ServeurRMI.MF.cv.aep.addPanel.t2.setText(""+dd[0]+" , "+dd[1]);
                        repaint();
                    }
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
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
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
    BufferedImage copie ;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
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
        
        
        BufferedImage croped = ImageUtils.cropImage(img, new Rectangle(-(int) tx,-(int) ty, 800, 800));
        copie = ImageUtils.copyImage(croped);
        
        if (MapPanel.selectedPanel == 0)
        {
            for(int j = 0 ; j < avions.size() ; j++)
            {
                Object infos[] = avions.get(j);
                double latlon[] = (double[]) infos[0];
                int zoom = 8;
                drawData(latlon[0] , latlon[1],zoom);
            }
            
            if (MapPanel.showAr)
            {
                for(int j = 0 ; j < FlyService.aeroports.size() ; j++)
                {
                    double latlon[] = new double[]{
                        FlyService.aeroports.get(j).getP().getX(),
                        FlyService.aeroports.get(j).getP().getY()
                    };
                    int zoom = 8;
                    drawAr(latlon[0] , latlon[1],zoom);
                }
            }
            
            
            if (MapPanel.aePos != null)
            {
                Object infos[] = MapPanel.aePos;
                double latlon[] = (double[]) infos[0];
                int zoom = 8;
                drawLocation(latlon[0] , latlon[1],zoom);
            }
            
            
        }
        
        g2draw.drawImage(copie, 0, 0, this);
    }
    
    
    public void drawData(double lat,double lon,int zoom)
    {
        int ret[] = GoogleMapsProjection2.getXYFromLatLonInMapZ8(lat,lon,zoom);
        
        int y = ret[1];
        int x = ret[0];
        
        ImageUtils.drawPixel(copie,(int) tx+x ,((int)ty+y-50));
    }
    
    public void drawLocation(double lat,double lon,int zoom)
    {
        int ret[] = GoogleMapsProjection2.getXYFromLatLonInMapZ8(lat,lon,zoom);
        
        int y = ret[1];
        int x = ret[0];
        
        ImageUtils.drawLocation(copie,(int) tx+x ,((int)ty+y));
    }
    
    public void drawAr(double lat,double lon,int zoom)
    {
        int ret[] = GoogleMapsProjection2.getXYFromLatLonInMapZ8(lat,lon,zoom);
        
        int y = ret[1];
        int x = ret[0];
        
        ImageUtils.drawAr(copie,(int) tx+x ,((int)ty+y));
    }
    
    
}
