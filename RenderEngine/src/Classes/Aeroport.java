/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author Alif computer
 */
public class Aeroport 
{
    private String name ;
    private GeoPoint p ;
    private String place ;
    private float capaciteDeReservoire ;
    private float reservoireActuel ;
    private int capaciteDePlace ;
    private int placePrise ; 

    public Aeroport(String name) {
        this.name = name;
    }
    
    public Aeroport(String name, GeoPoint p, String place , int capaciteDePlace , float capaciteDeReservoire ) {
        this.name = name;
        this.p = p;
        this.place = place;
        this.capaciteDePlace = capaciteDePlace ;
        this.capaciteDeReservoire = capaciteDeReservoire ;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPoint getP() {
        return p;
    }

    public void setP(GeoPoint p) {
        this.p = p;
    }
    
    public void addAvoin()
    {
        if (this.placePrise < this.capaciteDePlace )
        {
            this.placePrise ++;
        }else{
            System.out.println("aeoroport plien");
        }
    }
    
    public void removeAvion()
    {
        if (this.placePrise > 0 )
        {
            this.placePrise --;
        }
    }
    
    public void ajouterReservoire(float amount)
    {
        if (this.reservoireActuel + amount > this.capaciteDeReservoire)
        {
            // send to Control 
            this.reservoireActuel = this.capaciteDeReservoire ;
        }else{
            this.reservoireActuel += amount ;
        }
    }
    
    
    public void debiterReservoire(float amount,Avion a)
    {
        if(this.reservoireActuel - amount < 0 )
        {
            // send To Control
            a.setReservoir(this.reservoireActuel);
            this.reservoireActuel = 0 ;
        }else
        {
            a.setReservoir(amount);
            this.reservoireActuel -= amount;
        }
    }

    public float getCapaciteDeReservoire() {
        return capaciteDeReservoire;
    }

    public float getReservoireActuel() {
        return reservoireActuel;
    }

    public int getCapaciteDePlace() {
        return capaciteDePlace;
    }

    public int getPlacePrise() {
        return placePrise;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeroport other = (Aeroport) obj;
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return "Aeroport{" + "name=" + name + ", p=" + p + ", place=" + place + ", capaciteDeReservoire=" + capaciteDeReservoire + ", reservoireActuel=" + reservoireActuel + ", capaciteDePlace=" + capaciteDePlace + ", placePrise=" + placePrise + '}';
    }
    
    
}
