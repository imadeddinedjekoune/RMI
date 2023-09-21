/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Alif computer
 */
public class Time extends Date
{
    public static final long HOUR = 3600*1000; 
    public Time(int year, int month, int date,int hrs, int min,int sec){
        super(year, month, date, hrs, min, sec);
    }
    
    public Time(long time){
        super(time);
    }
    
    

    
    public Date ajouterHeure(double heure)
    {
        return new Date(this.getTime() + (long)(heure * HOUR));
    }
    public Date ajouterTemp(double time)
    {
        return new Date(this.getTime() + (long)(time));
    }
    
    
}
