/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Panels;

import Classes.Avion;
import Classes.FlyState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alif computer
 */
public class PlaneInfo extends JPanel
{
    public Avion aID ;
    JLabel matricule ;
    public JLabel speed ;
    JLabel state ;
    public JLabel res ;
    JButton btnRemove = new JButton("Effacer");
    JPanel inner = new JPanel();
    
    public String [] saved = new String[5];
    
    static int idCreation = 0 ;
    int id ;
    
    public BufferedImage img ;

    public PlaneInfo(String path , String mat , String s , String et , String resv )
    {
        id = idCreation++ ;
        aID = new Avion(mat, path.split("/")[3].split(".png")[0], FlyState.New, Float.parseFloat(s), Float.parseFloat(resv));
        
        saved[0] = path;
        saved[1] = mat;
        saved[2] = s;
        saved[3] = et;
        saved[4] = resv;
        
        
        matricule = new JLabel("Identifiant  :  "+mat);
        speed = new JLabel("Speed  :  "+s+" km/h ");
        state = new JLabel("Etat  :  "+et);
        res = new JLabel("Reservoire  :  "+resv+" L");
        
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
        }
        
        
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                
                renderengine.RenderEngine.MF.up.p1.ti.removeInfo(id);
                
                try {
                    renderengine.RenderEngine.stub.effacerAvion(new Avion(mat));
                } catch (Exception ee) {
                }
                
                renderengine.RenderEngine.MF.up.p1.rep();
                renderengine.RenderEngine.MF.up.repaint();
                repaint();
            }
        });
        
        
        setLayout(null);
        
        
        
        matricule.setBounds(220, 10, 200, 30);
        speed.setBounds(220, 50, 200, 30);
        state.setBounds(220, 90, 200, 30);
        res.setBounds(220, 130, 200, 30);
        btnRemove.setBounds(1100, 120, 80, 30);
        
        inner.setBounds(200, 10, 1000, 150);
        inner.setBorder(BorderFactory.createEtchedBorder());
        
        add(matricule);
        add(speed);
        add(state);
        add(res);
        add(btnRemove);
        add(inner);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(img != null){
            g.drawImage(img, 10, 10, 180,150 , this);
        }
    }
    
    
    public int getId()
    {
        return id;
    }
    
}
