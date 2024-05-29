package Model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Paper extends JFrame {
    public Paper(ArrayList<Node> pieces){
        setTitle("Paper");
        Border border=BorderFactory.createLineBorder(new Color(0x01B1717),2);
        //ImageIcon icon=new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\CinemaInterfaces\\recources\\Paying-icon.png");
        //setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        setSize(1500,1000);
        getContentPane().setBackground(new Color(0xBCFFF6));
        setLayout(null);
        setVisible(true);
        for(Node p:pieces){
            JLabel l=new JLabel("",SwingConstants.CENTER);
            l.setText(String.valueOf(p.getValue()));
            l.setBounds(300+p.getX()*3, 200+p.getY()*3, p.getWidth()*3, p.getHeight()*3);
            l.setBackground(Color.white);
            l.setOpaque(true);
            l.setForeground(new Color(0x01B1717));
            l.setFont(new Font("Serif", Font.BOLD, 16));
            l.setBorder(border);
            add(l);
        }
    }
}
/*
    0x0EEEBDD
    0X0CE1212
    0X0810000
    0x01B1717
     */
