package Classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import Classes.Time;

/**
 *
 * @author Alif computer
 */
public class Trajectoire 
{
    private double x[] ; 
    private double y[] ;
    private Time t_start ;

    public Trajectoire(double[] x, double[] y, Time t_start) {
        this.x = x;
        this.y = y;
        this.t_start = t_start;
    }
    
    public int getTaille()
    {
        return x.length;
    }
    
    public void getAtInstent(Time t){
        
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public Time getT_start() {
        return t_start;
    }
    
    
    
}
