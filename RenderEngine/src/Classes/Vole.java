/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author Alif computer
 */
public class Vole {
    private static int id = 0 ;
    private String place_destination ;
    private String place_depart ;
    private Time temp_depart ;
    private ArrayList<Aeroport> aeroports ;

    public Vole(String place_destination, String place_depart, Time temp_depart, ArrayList<Aeroport> aeroports) {
        this.place_destination = place_destination;
        this.place_depart = place_depart;
        this.temp_depart = temp_depart;
        this.aeroports = aeroports;
    }
    public Vole( Time temp_depart, ArrayList<Aeroport> aeroports) 
    {
        this.temp_depart = temp_depart;
        this.aeroports = aeroports;
    }
    
    public String getType()
    {
        if (this.aeroports.size() > 2)
        {
            return "Escale";
        }
        return "Direct";
    }

    public static int getId() {
        return id;
    }

    public String getPlace_destination() {
        return place_destination;
    }

    public String getPlace_depart() {
        return place_depart;
    }

    public Time getTemp_depart() {
        return temp_depart;
    }

    public ArrayList<Aeroport> getAeroports() {
        return aeroports;
    }
    
    

    @Override
    public String toString() {
        return "Vole{" + "place_destination=" + place_destination + ", place_depart=" + place_depart + ", temp_depart=" + temp_depart + ", aeroports=" + aeroports + '}';
    }
    
}
