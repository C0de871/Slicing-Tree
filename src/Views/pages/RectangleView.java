package Views.pages;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Controller.ColorController;
import Controller.FontController;
import Views.Components.StaticMethods;
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
