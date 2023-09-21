/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Alif computer
 */
public class MathUtils 
{
    public static double[] getSegment(double x1,double y1,double x2 ,double y2)
    {
        // get the slope and the intercept  // y = ax + b 
        double a = (y2-y1) / (x2-x1);
        double b = y1 - a * x1 ;
        double toReturn [] = {a,b};
        return toReturn;
    }
    
    public static double mapping(double a,double b,double c,double d,double t)
            // will map the interval [a,b] onto the interval [c,d] in that order (meaning f(a)=c and f(b)=d)
    {
        return c + ((d-c)/(b-a)) * (t-a);
    }
    
    public static double[] generateIntervale (int duartion_in_s , double x1 , double x2)
    {
        int i ;
        double intervale [] = new double[duartion_in_s+1] ;
        for ( i = 0 ; i <= duartion_in_s ; i++ )
        {
            intervale[i] = mapping(0,duartion_in_s,x1,x2,i) ; 
        }
        return intervale;
    }
    
    public static double[] getY(double line[] , double intervale[])
    {
        double y[] = new double[intervale.length];
        int i ;
        for (i = 0 ; i < y.length ; i++)
        {
            y[i] = line[0] * intervale[i] + line[1];
        }
        return y ;
    }
    
}
