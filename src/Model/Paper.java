package Model;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Paper extends JFrame {

        private String name;
        private int height;
        private int width;

        public Paper(String name, int length, int width) {
            this.name = name;
            this.height = length;
            this.width = width;
        }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int length) {
        this.height = length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Paper(int length, int width) {
        this.height = length;
        this.width = width;
    }
    public static Paper parsePaper(String paperStr) {
        String[] parts = paperStr.split("[\\[\\],]");
        @SuppressWarnings("unused")
        String name = parts[0];
        int width = Integer.parseInt(parts[1].trim());
        int height = Integer.parseInt(parts[2].trim());
        return new Paper(width, height);
    }

    public Paper(ArrayList<Node> pieces){
        setTitle("Paper");
        Border border=BorderFactory.createLineBorder(new Color(0x01B1717),2);
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
