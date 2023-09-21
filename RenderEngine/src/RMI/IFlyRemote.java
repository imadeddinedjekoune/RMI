/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;

import Classes.Avion;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Alif computer
 */
public interface IFlyRemote extends Remote 
{
    public void ajouterAvion (Avion a) throws RemoteException;
    public int testerConnexion (int a) throws RemoteException;
    public void effacerAvion (Avion a) throws RemoteException;
    public ArrayList<Avion> getAllAvions () throws RemoteException;
}
