package Views.Components;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.ColorController;

// Panel to represent the stretching line
public class StretchingLinePanel extends JPanel {

    // attributes:
    private final Point p1, p2;
    private final int lineThickness;
    private int startX, startY, endX, endY;

    // constructor that recieve start and end point:
    public StretchingLinePanel(Point p1, Point p2, int lineThickness) {
        this.p1 = p1;
        this.p2 = p2;
        this.lineThickness = lineThickness;
        startX = p1.x;
        startY = p1.y;
        endX = p2.x;
        endY = p2.y;
        setOpaque(false);
    }

    // animation to connect nodes:
    public Animator startConnectAnimation() {
        Animator animator = new Animator(300);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                endX = startX + (int) ((p2.x - p1.x) * fraction);
                endY = startY + (int) ((p2.y - p1.y) * fraction);
                int wid = Math.abs(endX - startX);
                int len = Math.abs(endY - startY);
                int xx = Math.min(startX, endX);
                int yy = Math.min(startY, endY);
                if (wid == 0) {
                    wid = 6;
                    xx -= 3;
                } else if (len == 0) {
                    len = 6;
                    yy -= 3;
                }
                setBounds(xx, yy, wid, len);
                repaint();
            }
        });
        animator.start();
        return animator;
    }

    // animation to adjust nodes:
    public Animator startStretchingAnimation(int distance, boolean stretchFromLeft) {
        Animator animator = new Animator(300);
        int initialStartX = stretchFromLeft ? Math.min(startX,endX) : Math.max(endX,startX);
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (stretchFromLeft&&startX<endX) {
                    startX = initialStartX - (int) (distance * fraction);
                }else if(stretchFromLeft&&startX>=endX){
                    endX = initialStartX - (int) (distance * fraction);
                } else if(endX>startX){
                    endX = initialStartX + (int) (distance * fraction);
                }else{
                    startX = initialStartX + (int) (distance * fraction);
                }
                int wid = Math.abs(endX - startX);
                int len = Math.abs(endY - startY);
                int xx = Math.min(startX, endX);
                int yy = Math.min(startY, endY);
                if (wid == 0) {
                    wid = 6;
                    xx -= 3;
                } else if (len == 0) {
                    len = 6;
                    yy -= 3;
                }
                setBounds(xx, yy, wid, len);
                repaint();
            }
        });
        animator.start();
        return animator;
    }

    // animation to translate edge:
    public Animator startTranslatingAnimation(int distance) {
        Animator animator = new Animator(300);
        int oldStartX = startX;
        int oldEndX = endX;
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                // Translate both start and end points of the line
                startX = oldStartX + (int) (distance * fraction);
                endX = oldEndX + (int) (distance * fraction);

                // Update bounds to accommodate new position
                int wid = Math.abs(endX - startX);
                int len = Math.abs(endY - startY);
                int xx = Math.min(startX, endX);
                int yy = Math.min(startY, endY);
                if (wid == 0) {
                    wid = 6;
                    xx -= 3;
                } else if (len == 0) {
                    len = 6;
                    yy -= 3;
                }
                setBounds(xx, yy, wid, len);

                // Repaint the component to reflect the new positions
                repaint();
            }
        });
        animator.start();
        return animator;
    }

    // paint the compoenent:
    @Override
    protected void paintComponent(Graphics g) {

        // optimize graphic resolution:
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // line property:
        g2d.setColor(ColorController.selectButtonColor);
        g2d.setStroke(new BasicStroke(lineThickness)); // Set the line thickness
        g2d.drawLine(startX - getX(), startY - getY(), endX - getX(), endY - getY());
    }
}