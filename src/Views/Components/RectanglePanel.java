package Views.Components;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import Controller.ColorController;

public class RectanglePanel extends JPanel implements ViewNode{

    //attributes:
    final int x;
    final int y;
    @SuppressWarnings("unused")
    private final int width;
    @SuppressWarnings("unused")
    private final int height;
    private final char character;

    // constructer:
    public RectanglePanel(int x, int y, int width, int height, char character) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.character = character;
        setBounds(x - width / 2, y - height / 2, width, height);
        setOpaque(false);
    }

    // paint the component with a letter inside it:
    @Override
    protected void paintComponent(Graphics g) {

        //optimize graphic resolution:
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

        //draw the rectangle:
        g2d.setColor(ColorController.OrderLabelColor);
        g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 10, 10); // Adjust for stroke width and round corners

        // Draw the character in the middle
        FontMetrics fm = g2d.getFontMetrics();
        int charWidth = fm.charWidth(character);
        int charHeight = fm.getAscent();
        int charX = (getWidth() - charWidth) / 2;
        int charY = (getHeight() + charHeight) / 2 - fm.getDescent();
        g2d.drawString(Character.toString(character), charX, charY);
    }

    @Override
    public int getMidX() {
        return x;
    }

    @Override
    public int getMidY() {
        return y;
    }

}
