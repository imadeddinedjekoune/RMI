/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Utils.FileStructure;
import java.util.ArrayList;

/**
 *
 * @author Alif computer
 */
public class AvionGestionFiles 
{
    public static ArrayList<FileStructure> stuct = new ArrayList<>();
    
    public static void init()
    {
        stuct.add(new FileStructure("pa28",0.45f));
        stuct.add(new FileStructure("q400",0.2f));
        stuct.add(new FileStructure("boe789",0.1f));
        
    }
    
    public static String getNameIn(int i)
    {
        return stuct.get(i).getNom();
    }
    
}


