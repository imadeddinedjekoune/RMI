/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Vol;

import Classes.Aeroport;
import Classes.Avion;
import Classes.Time;
import Classes.Vole;
import RMI.FlyService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alif computer
 */
public class AddVolPanel extends JPanel{
    
    JLabel lVolId = new JLabel("Vol ID : ");
    JTextField t1 = new JTextField();
    JLabel lVolTime = new JLabel("Temp : ");
    JSpinner t2;
    
    JLabel type = new JLabel("Type : ");
    JPanel pTitle = new JPanel();
    JPanel pTitle2 = new JPanel();
    JPanel pTitle3 = new JPanel();
    
    JLabel lModel = new JLabel("Model : ");
    JTextField tModel = new JTextField();
    
    JLabel lEtat = new JLabel("Etat : ");
    JTextField tEtat = new JTextField();
    
    JLabel lSpeed = new JLabel("Speed : ");
    JTextField tSpeed = new JTextField();
    
    JLabel lRes = new JLabel("Reservoire : ");
    JTextField tRes = new JTextField();
    
    
    
    
    
    TabList tab = new TabList(new ArrayList<>());
    JComboBox<String> listeAr = new JComboBox<>();
    JButton addBtn = new JButton("Ajouter");
    JButton addReset = new JButton("Reset");
    
    public JComboBox<String> listeAv = new JComboBox<>();
    public JLabel time = new JLabel("", SwingConstants.CENTER);
    
    
    JButton finalBtn = new JButton("Ajouter Vole");
    
    public AddVolPanel()
    {
        int x1 = 200 , y1 = 80 ;
        lVolId.setBounds(20+x1, 30+y1, 100, 30);
        lVolId.setFont(new Font("Monospaced", Font.ITALIC, 16));
        t1.setBounds(120+x1, 30+y1, 230, 30);
        t1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        type.setBounds(30+x1, 110+y1, 100, 30);
        
        lVolTime.setBounds(20+x1, 70+y1, 100, 30);
        lVolTime.setFont(new Font("Monospaced", Font.ITALIC, 16));
        
        t2 = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(t2, "yyyy-MM-dd HH:mm:ss");
        t2.setEditor(timeEditor);
        t2.setValue(new Date()); // will only show the current time
        
        setLayout(null);
        t2.setBounds(120+x1, 70+y1, 230, 30);
        t2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        
        
        type.setFont(new Font("Monospaced", Font.ITALIC, 14));
        
        
        time.setBounds(900, 10, 300, 60);
        time.setFont(new Font("Arial", Font.BOLD, 15));
        time.setBorder(BorderFactory.createEtchedBorder());
        
        
        TitledBorder b = BorderFactory.createTitledBorder("   Vol Info :   ");
        b.setTitleFont(new Font("Monospaced", Font.ITALIC, 16));
        pTitle.setBorder(b);
        
        pTitle.setBounds(0+x1, 0+y1, 400, 160);
        
        TitledBorder b2 = BorderFactory.createTitledBorder("   Trajectoire Info :   ");
        b2.setTitleFont(new Font("Monospaced", Font.ITALIC, 16));
        pTitle2.setBorder(b2);
        
        pTitle2.setBounds(0+x1, 190+y1, 400, 300);
        
        TitledBorder b3 = BorderFactory.createTitledBorder("   Avion Info :   ");
        b3.setTitleFont(new Font("Monospaced", Font.ITALIC, 16));
        pTitle3.setBorder(b3);
        
        pTitle3.setBounds(450+x1, 0+y1, 400, 360);
        
        finalBtn.setBounds(600+x1, 390+y1, 100, 50);
        
        listeAv.setBounds(500+x1, 40+y1, 300, 30);
        lModel.setBounds(500+x1, 100+y1, 100, 30);
        tModel.setBounds(620+x1, 100+y1, 150, 30);
        tModel.setEnabled(false);
        lEtat.setBounds(500+x1, 160+y1, 100, 30);
        tEtat.setBounds(620+x1, 160+y1, 150, 30);
        tEtat.setEnabled(false);
        
        lSpeed.setBounds(500+x1, 220+y1, 100, 30);
        tSpeed.setBounds(620+x1, 220+y1, 150, 30);
        tSpeed.setEnabled(false);
        
        lRes.setBounds(500+x1, 280+y1, 100, 30);
        tRes.setBounds(620+x1, 280+y1, 150, 30);
        tRes.setEnabled(false);
        
        
        tab.setBounds(20+x1, 270+y1, 360, 160);
        // TrajectoireInfo t = new TrajectoireInfo();
        
        
        for (int i = 0 ; i < FlyService.aeroports.size() ; i++)
        {
            listeAr.addItem(FlyService.aeroports.get(i).getName());
        }
        
        listeAr.setBounds(50+x1, 50+180+y1, 220, 30);
        addBtn.setBounds(270+x1, 50+180+y1, 80, 30) ;
        addReset.setBounds(280+x1, 50+390+y1, 80, 30) ;
        
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab.addNewInfo(new TrajectoireInfo((String) listeAr.getSelectedItem()));
                repaint();
                tab.repaint();
            }
        });
        
        addReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab = new TabList(new ArrayList<>());
                rep2();
            }
        });
        
        listeAv.addActionListener(new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String s = (String) listeAv.getSelectedItem();//get the selected item
                    int index = FlyService.avions.indexOf(new Avion(s));
                    Avion a = FlyService.avions.get(index);

                    tModel.setText(a.getModel());
                    tEtat.setText(a.getEtat().toString());
                    tSpeed.setText(""+a.getSpeed());
                    tRes.setText(""+a.getReservoir());
                } catch (Exception ee) {
                }
                
            }
        });
        
        
        finalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = FlyService.avions.indexOf(new Avion((String)listeAv.getSelectedItem()));
                Avion a = FlyService.avions.get(index);
                
                ArrayList<Aeroport> ars = new ArrayList<>();
                
                for (int i = 0 ; i < tab.plans.size() ; i++ )
                {
                    String sel = tab.plans.get(i).NomAr.getText() ;
                    int ind = FlyService.aeroports.indexOf(new Aeroport(sel));
                    ars.add(FlyService.aeroports.get(ind));
                }
                
                Time t = new Time(((Date)(t2.getValue())).getTime());
                
                Vole v = new Vole(t, ars);
                a.ajouterVol(v);
                a.commancerLeVol(v,RMI.ServeurRMI.MF.cv.vp.mv.ip8);
            }
        });
        
        setLayout(null);
        
        add(time);
        add(finalBtn);
        add(lSpeed);
        add(tSpeed);
        add(lRes);
        add(tRes);
        add(lVolId);
        add(t1);
        add(tModel);
        add(lEtat);
        add(tEtat);
        add(lVolTime);
        add(t2);
        add(listeAv);
        add(lModel);
        add(type);
        add(tab);
        add(addBtn);
        add(addReset);
        add(listeAr);
        add(pTitle);
        add(pTitle2);
        add(pTitle3);
    }
    
    
    public void rep()
    {
        removeAll();
        listeAr =  new JComboBox<>();
        for (int i = 0 ; i < FlyService.aeroports.size() ; i++)
        {
            listeAr.addItem(FlyService.aeroports.get(i).getName());
        }
        int x1 = 200 , y1 = 80 ;
        listeAr.setBounds(50+x1, 50+180+y1, 220, 30);
        add(time);
        add(finalBtn);
        add(lSpeed);
        add(tSpeed);
        add(lRes);
        add(tRes);
        add(lVolId);
        add(lEtat);
        add(tEtat);
        add(tModel);
        add(listeAv);
        add(lModel);
        add(t1);
        add(lVolTime);
        add(t2);
        add(type);
        add(tab);
        add(addBtn);
        add(addReset);
        add(listeAr);
        add(pTitle);
        add(pTitle2);
        add(pTitle3);
        
        repaint();
        revalidate();
    }
    
    public void rep2()
    {
        removeAll();
        int x1 = 200 , y1 = 80 ;
        tab.setBounds(20+x1, 270+y1, 360, 160);
        add(time);
        add(finalBtn);
        add(lSpeed);
        add(tSpeed);
        add(lRes);
        add(tRes);
        add(lVolId);
        add(lEtat);
        add(tEtat);
        add(listeAv);
        add(tModel);
        add(lModel);
        add(t1);
        add(lVolTime);
        add(t2);
        add(type);
        add(tab);
        add(addBtn);
        add(addReset);
        add(listeAr);
        add(pTitle);
        add(pTitle2);
        add(pTitle3);
        repaint();
        revalidate();
    }
   

    public void MatchList(DefaultTableModel model) 
    {
        ArrayList<String> actuel = new ArrayList<>();
        ArrayList<String> M = new ArrayList<>();
        
        for (int i = 0 ; i < listeAv.getItemCount() ; i++)
        {
            actuel.add(listeAv.getItemAt(i));
        }
        
        for (int i = 0 ; i < model.getRowCount() ; i++)
        {
            M.add((String) model.getValueAt(i, 0));
        }
        
        
        for(int j = 0 ; j < model.getRowCount(); j++)
        {
            String idMod = (String) model.getValueAt(j, 0);
            if (actuel.contains(idMod))
            {
            }else{
                listeAv.addItem(idMod);
                System.out.println("added "+idMod);
                listeAv.repaint();
            }
        }
        
        for (int i = 0 ; i < actuel.size() ; i++)
        {
            if(!M.contains(actuel.get(i)))
            {
                listeAv.removeItem((String)(actuel.get(i)));
            }
        }
        
    }
    
}
