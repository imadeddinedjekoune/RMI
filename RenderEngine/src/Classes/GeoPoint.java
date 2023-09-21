/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Alif computer
 */
public class GeoPoint {
    private float x , y ;

    public GeoPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
     public static double distance(GeoPoint p1 , GeoPoint p2, String unit) 
     {
        double lat1 = p1.x ,  lon1 = p1.y ,  lat2 = p2.x,  lon2 = p2.y;
        if ((lat1 == lat2) && (lon1 == lon2)) 
        {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + 
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                    dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                    dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    @Override
    public String toString() {
        return "GeoPoint{" + "x=" + x + ", y=" + y + '}';
    }
     
}
