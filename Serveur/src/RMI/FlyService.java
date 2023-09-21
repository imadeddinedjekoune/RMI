/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import Classes.Aeroport;
import Classes.Avion;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Alif computer
 */
public class FlyService extends UnicastRemoteObject implements IFlyRemote
{
    public static ArrayList<Avion> avions = new ArrayList<>();
    public static ArrayList<Aeroport> aeroports = new ArrayList<>();
    
    
    protected FlyService() throws RemoteException{
        super();
    }
    
    @Override
    public void ajouterAvion(Avion a) throws RemoteException {
        avions.add(a);
    }

    @Override
    public int testerConnexion(int a) throws RemoteException {
        return  0;
    }

    @Override
    public void effacerAvion(Avion a) throws RemoteException {
        avions.remove(a);
    }

    @Override
    public ArrayList<Avion> getAllAvions() throws RemoteException 
    {
        return avions;
    }
    
}
