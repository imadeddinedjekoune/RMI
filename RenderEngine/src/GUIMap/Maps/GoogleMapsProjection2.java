/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIMap.Maps;

/**
 *
 * @author Alif computer
 */
public final class GoogleMapsProjection2 
{
    private final int TILE_SIZE = 256;
    private PointF _pixelOrigin;
    private double _pixelsPerLonDegree;
    private double _pixelsPerLonRadian;

    public GoogleMapsProjection2()
    {
        this._pixelOrigin = new PointF(TILE_SIZE / 2.0,TILE_SIZE / 2.0);
        this._pixelsPerLonDegree = TILE_SIZE / 360.0;
        this._pixelsPerLonRadian = TILE_SIZE / (2 * Math.PI);
    }

    double bound(double val, double valMin, double valMax)
    {
        double res;
        res = Math.max(val, valMin);
        res = Math.min(res, valMax);
        return res;
    }

    double degreesToRadians(double deg) 
    {
        return deg * (Math.PI / 180);
    }

    double radiansToDegrees(double rad) 
    {
        return rad / (Math.PI / 180);
    }

    PointF fromLatLngToPoint(double lat, double lng, int zoom)
    {
        PointF point = new PointF(0, 0);

        point.x = _pixelOrigin.x + lng * _pixelsPerLonDegree;       

        // Truncating to 0.9999 effectively limits latitude to 89.189. This is
        // about a third of a tile past the edge of the world tile.
        double siny = bound(Math.sin(degreesToRadians(lat)), -0.9999,0.9999);
        point.y = _pixelOrigin.y + 0.5 * Math.log((1 + siny) / (1 - siny)) *- _pixelsPerLonRadian;

        int numTiles = 1 << zoom;
        point.x = point.x * numTiles;
        point.y = point.y * numTiles;
        return point;
     }

    PointF fromPointToLatLng(PointF point, int zoom)
    {
        int numTiles = 1 << zoom;
        point.x = point.x / numTiles;
        point.y = point.y / numTiles;       

        double lng = (point.x - _pixelOrigin.x) / _pixelsPerLonDegree;
        double latRadians = (point.y - _pixelOrigin.y) / - _pixelsPerLonRadian;
        double lat = radiansToDegrees(2 * Math.atan(Math.exp(latRadians)) - Math.PI / 2);
        return new PointF(lat, lng);
    }
    
    public static int[] getXYFromLatLonInMap(double lat , double lon,int zoom)
    {
        GoogleMapsProjection2 gmap2 = new GoogleMapsProjection2();

        PointF point_ref = gmap2.fromLatLngToPoint(45.089035564831,-10.01953125, 11);
        
        PointF point_calc = gmap2.fromLatLngToPoint(lat,lon, zoom);
        
        
        
        int posX = (int) Math.round(point_calc.x-point_ref.x);
        int posY = (int) Math.round(point_calc.y-point_ref.y) ;
        
        int xTile = posX / 1024 ;
        int yTile = posY / 1024 ;
        
        posX = posX %1024;
        posY = posY %1024;
        
        int toReturn [] = {xTile,yTile,posX,posY};
        
        return toReturn;
    }
    
    public static int[] getXYFromLatLonInMapZ8(double lat , double lon,int zoom)
    {
        GoogleMapsProjection2 gmap2 = new GoogleMapsProjection2();

        PointF point_ref = gmap2.fromLatLngToPoint(45.089035564831,-11.25, zoom);
        
        PointF point_calc = gmap2.fromLatLngToPoint(lat,lon, zoom);
        
        
        
        int posX = (int) Math.round(point_calc.x-point_ref.x);
        int posY = (int) Math.round(point_calc.y-point_ref.y) ;
       
        
        int toReturn [] = {posX,posY};
        
        return toReturn;
    }
    
    public static double[] getLatLonFromXYInMap(int x , int y , int tileX , int tileY)
    {
        GoogleMapsProjection2 gmap2 = new GoogleMapsProjection2();
        PointF point_ref = gmap2.fromLatLngToPoint(45.089035564831,-10.01953125, 11);
        PointF point2 = gmap2.fromPointToLatLng(new PointF(x+tileX*1024+point_ref.x, y+tileY*1024+point_ref.y),11);
        double toReturn [] = {point2.x,point2.y};
        
        return toReturn;
    }
    
    public static double[] getLatLonFromXYInMapZ8(int x , int y )
    {
        GoogleMapsProjection2 gmap2 = new GoogleMapsProjection2();
        PointF point_ref = gmap2.fromLatLngToPoint(45.089035564831,-11.25, 8);
        PointF point2 = gmap2.fromPointToLatLng(new PointF(x+point_ref.x,y+point_ref.y),8);
        double toReturn [] = {point2.x,point2.y};
        
        return toReturn;
    }

}

final class PointF 
{
    public double x;
    public double y;

    public PointF(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}