package Views.Components;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.ColorController;
import Controller.FontController;

public class AnimatedStringPanel extends JPanel implements ActionListener{

    //atttirbutes:
    String bluePrintText= "(A[20,40] - B[30,50])";
    String text="";
    int curInd= 0 ;
    Timer timer ;

    //constructor:
    public AnimatedStringPanel(){
        setOpaque(false);
        timer = new Timer(150, this);
        timer.start();
    }

    //Preferred Size depend on the string:
    @Override
    public Dimension getPreferredSize() {
        // Calculate the preferred size based on the string width and font height
        FontMetrics fm = getFontMetrics(FontController.animatedStringFont);
        int textWidth = fm.stringWidth(bluePrintText);
        int textHeight = fm.getHeight();
        // Add some padding around the text
        return new Dimension(textWidth + 20, textHeight + 10);
    }

    //override paint to animate the String:
    @Override
    public void paint(Graphics g){

        //optimize graphic resolution:
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(rh);

        // Set the font and color
        g2d.setFont(FontController.animatedStringFont);
        g2d.setColor(ColorController.OrderLabelColor);

        // Measure the text
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight(); // Total height of the text, including ascent and descent

        // Calculate the coordinates to center the text
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2 + fm.getAscent(); // Center the text vertically

        // Draw the text
        g2d.drawString(text, x, y);
    }


    //this method is to animate the Text:
    @Override
    public void actionPerformed(ActionEvent e) {
        if(curInd<bluePrintText.length()){
            text+= bluePrintText.charAt(curInd);
            curInd++;
            repaint();
        }else{
            curInd=0;
            text="";
            timer.setDelay(150);
            repaint();
        }
        if(curInd==bluePrintText.length()-1){
            timer.setDelay(1000);
        }
    }
}
