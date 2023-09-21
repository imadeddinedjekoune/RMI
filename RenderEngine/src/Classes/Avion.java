/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import GUIMap.Maps.ImagePanelZ8;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Alif computer
 */
public class Avion implements Serializable
{
    public static final int DELAY = 100;
    private String Matricule ;
    private String Model ;
    private FlyState etat ;
    private ArrayList<Vole> vols ;
    private GeoPoint actualPosition ;
    private float speed ;
    private float reservoir ;
    private ArrayList<Trajectoire> trajectoire ;
    private Thread actuelVol = null ;

    public Avion(String Matricule, String Model, FlyState etat, ArrayList<Vole> vols) 
    {
        this.Matricule = Matricule;
        this.Model = Model;
        this.etat = etat;
        this.vols = vols;
    }

    public Avion(String Matricule) {
        this.Matricule = Matricule;
    }
    
    
    
    public Avion(String Matricule, String Model, FlyState etat , float speed , float reservoir ) 
    {
        this.Matricule = Matricule;
        this.Model = Model;
        this.etat = etat;
        this.vols =  new ArrayList<>();
        this.speed = speed;
        this.reservoir = reservoir ;
        this.trajectoire = new ArrayList<>();
    }

    public GeoPoint getActualPosition() {
        return actualPosition;
    }

    public void setActualPosition(GeoPoint actualPosition) {
        this.actualPosition = actualPosition;
    }

    public ArrayList<Trajectoire> getTrajectoire() {
        return trajectoire;
    }
    
    
    
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getMatricule() {
        return Matricule;
    }

    public float getReservoir() {
        return reservoir;
    }

    public void setReservoir(float reservoir) {
        this.reservoir = reservoir;
    }
    
    

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public FlyState getEtat() {
        return etat;
    }

    public void setEtat(FlyState etat) {
        this.etat = etat;
    }

    public ArrayList<Vole> getVols() {
        return vols;
    }

    public void setVols(ArrayList<Vole> vols) {
        this.vols = vols;
    }
    
    
    public void ajouterVol(Vole v)
    {
        this.vols.add(v);
    }
    
    public void calculerLaTrajectoire(int index)
    {
        Vole v = this.getVols().get(index);
        ArrayList<Aeroport> ar = v.getAeroports();
        
        Time t_depart = v.getTemp_depart();
        for (int i = 0 ; i < ar.size()-1 ; i++ )
        {
            GeoPoint start = ar.get(i).getP() ; 
            GeoPoint end = ar.get(i+1).getP() ; 
            
            // calculer la distance 
            double dist = GeoPoint.distance(start, end, "K");
            
            // calculer le temp darriver --> v = d / t --> t(h) = d(km) / v(km/h)
            double t_delta = dist / this.speed;
            Time t_arriver = new Time(t_depart.ajouterHeure(t_delta).getTime());
            
            // rapport en seconde 
            int delta_second = (int)(t_arriver.getTime()-t_depart.getTime())/1000;
            
            // calculer la tajectoire 
            double x[] = MathUtils.generateIntervale(delta_second,start.getX(), end.getX());
            double y[] = MathUtils.getY(MathUtils.getSegment(start.getX(), start.getY(), end.getX(), end.getY()),x);
            this.trajectoire.add(new Trajectoire(x, y, t_depart));
            
            t_depart = t_arriver ; 
        }
    }
    
    public void commancerLeVol(Vole v)
    {
        int indexVole = vols.indexOf(v);
        calculerLaTrajectoire(indexVole);
        Vole actuel = vols.remove(indexVole);
        
        actuelVol = new Thread(){
            public void run(){
                int i , j ;
                for (i = 0 ; i < trajectoire.size() ; i++)
                {
                    for (j = 0 ; j < trajectoire.get(i).getX().length ; j++)
                    {
                        double coord [] = {trajectoire.get(i).getX()[j],trajectoire.get(i).getY()[j]};
                        Time t = new Time(trajectoire.get(i).getT_start().ajouterTemp(j*1000).getTime());
                        System.out.println("Avion : "+Matricule+" , t : "+t+" , pos : ("+coord[0]+" , "+coord[1]+")");
                    }
                }
            }
        };
        actuelVol.start();
    }
    
    public void commancerLeVol(Vole v , ImagePanelZ8 ip8)
    {
        int indexVole = vols.indexOf(v);
        calculerLaTrajectoire(indexVole);
        Vole actuel = vols.remove(indexVole);
        
        ip8.avions.add(new Object[]{new double[]{0 , 0} , 8});
        
        actuelVol = new Thread(){
            public void run(){
                int i , j ;
                for (i = 0 ; i < trajectoire.size() ; i++)
                {
                    for (j = 0 ; j < trajectoire.get(i).getX().length ; j++)
                    {
                        double coord [] = {trajectoire.get(i).getX()[j],trajectoire.get(i).getY()[j]};
                        Time t = new Time(trajectoire.get(i).getT_start().ajouterTemp(j*1000).getTime());
                        System.out.println("Avion : "+Matricule+" , t : "+t+" , pos : ("+coord[0]+" , "+coord[1]+")");
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                        ip8.avions.set(0,new Object[]{new double[]{coord[0] , coord[1]} , 8});
                        ip8.repaint();
                    }
                }
            }
        };
        actuelVol.start();
    }
    
    public void pauseVol()
    {
        try {
            actuelVol.stop();
        } catch (Exception ex) {
            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void debugTrajectoire()
    {
        int i , j ;
        for (i = 0 ; i < trajectoire.size() ; i++)
        {
            System.out.println("Trajectoire "+i+" : ");
            
            for (j = 0 ; j < trajectoire.get(i).getX().length ; j++)
            {
                System.out.println(fmt(trajectoire.get(i).getX()[j])+" , "+fmt(trajectoire.get(i).getY()[j]));
            }
        }
    }
    
    private String fmt(double a)
    {
        return String.format("%.5f", a);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Avion other = (Avion) obj;
        return Objects.equals(this.Matricule, other.Matricule);
    }

    @Override
    public String toString() {
        return "Avion{" + "Matricule=" + Matricule + ", Model=" + Model + ", etat=" + etat + ", vols=" + vols + ", actualPosition=" + actualPosition + ", speed=" + speed + ", reservoir=" + reservoir + ", trajectoire=" + trajectoire + ", actuelVol=" + actuelVol + '}';
    }

    
}
