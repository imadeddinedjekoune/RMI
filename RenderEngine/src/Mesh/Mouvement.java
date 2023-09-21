/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Alif computer
 */
public class Mouvement 
{
    public static float[] rotatePoint(float x , float y , float cx , float cy , float angle)
    {
        Point2D result = new Point2D.Double();
        AffineTransform rotation = new AffineTransform();
        double angleInRadians = (angle * Math.PI / 180);
        rotation.rotate(angleInRadians, cx, cy);
        rotation.transform(new Point2D.Double(x,y), result);
        return new float[]{(float)result.getX(),(float)result.getY()};
    }
    
    public static ArrayList <Float[]> genCircle(float x , float y , float cx , float cy , float angle)
    {
        ArrayList <Float[]> arr = new ArrayList<Float[]>();
        
        float x_s = x ;
        float y_s = y ;
        
        
        for(int i = 0 ; i < 360*2 ; i++)
        {
            float res[] = rotatePoint(x_s,y_s,cx,cy,angle);
            arr.add(new Float[]{res[0],res[1]});
            x_s = res[0];
            y_s = res[1];
        }
        return arr ;
    }
    
}
