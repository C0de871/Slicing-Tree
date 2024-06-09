package Views.pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Controller.ColorController;
import Controller.FontController;
import Model.Node;
import Views.Components.StaticMethods;
import Views.Components.TextFieldNode;
import button.Button;

public class RectangleView extends JPanel {
    // components:
    Button rectangleToTreeButton;
    Button rectangleToTextButton;
    Button backButton;
    Button resetButton;
    Button rotateButton;

    public RectangleView() {
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        rectangleToTreeButton = new Button();
        rectangleToTextButton = new Button();
        backButton = new Button();
        resetButton = new Button();
        rotateButton = new Button();

        //rotate Button decoration:
        rotateButton.setBounds(1120, 680, 100, 70);
        rotateButton.setBackground(ColorController.resetButtonBackground);
        rotateButton.setForeground(ColorController.selectButtonForgroundColor);
        rotateButton.setRippleColor(new java.awt.Color(255, 255, 255));
        rotateButton.setShadowColor(new java.awt.Color(29, 162, 253));
        rotateButton.setFont(FontController.instructionLabelFont);
        rotateButton.setText("Rotate");
        rotateButton.setRound(50);
        rotateButton.setFocusable(false);
        
        // Buttons Decoration:
        StaticMethods.createButtons(rectangleToTreeButton, rectangleToTextButton, backButton, resetButton,
        "Rec To Tree",
        "Rec to Text", this);

        //add:
        this.add(rotateButton);
    }

    // add rectangle:
    public void addRectangles(ArrayList<Node> pieces) {
        Border border = BorderFactory.createLineBorder(new Color(0x01B1717), 2);
        for (Node p : pieces) {
            JLabel l = new JLabel("", SwingConstants.CENTER);
            l.setText(String.valueOf(p.getValue()));
            l.setBounds(300 + p.getX() * 3, 200 + p.getY() * 3, p.getWidth() * 3, p.getHeight() * 3);
            l.setBackground(Color.white);
            l.setOpaque(true);
            l.setForeground(new Color(0x01B1717));
            l.setFont(new Font("Serif", Font.BOLD, 16));
            l.setBorder(border);
            add(l);
        }
    }

    // add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // add action listener to the reset button:
    public void addResetButtonActionListener(ActionListener actionListener) {
        resetButton.addActionListener(actionListener);
    }

    // add action listener to the reset button:
    public void addRotateButtonActionListener(ActionListener actionListener) {
        rotateButton.addActionListener(actionListener);
    }

    // add action listener to the converter Buttons:
    public void addConvertersActionListener(ActionListener rectangleToTreeListener,
            ActionListener rectangleToTextListener) {
        rectangleToTreeButton.addActionListener(rectangleToTreeListener);
        rectangleToTextButton.addActionListener(rectangleToTextListener);
    }
}
