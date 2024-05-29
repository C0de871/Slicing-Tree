package Views.pages;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Controller.ColorController;
import Controller.FontController;
import Views.Components.TextFieldNode;
import button.Button;

public class TreeView extends JPanel {

    // components:
    Button convertButton;
    Button backButton;
    public static TextFieldNode textRoot;

    // constructor:
    public TreeView() {
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        convertButton = new Button();
        textRoot = new TextFieldNode(750, 70, 50, 50, 50);
        backButton = new Button();

        // setBounds:
        convertButton.setBounds(700, 680, 100, 70);
        backButton.setBounds(25, 680, 100, 70);

        // set Color:
        convertButton.setBackground(ColorController.selectButtonColor);
        convertButton.setForeground(ColorController.selectButtonForgroundColor);
        convertButton.setRippleColor(new java.awt.Color(255, 255, 255));
        convertButton.setShadowColor(new java.awt.Color(29, 162, 253));
        backButton.setBackground(ColorController.selectButtonColor);
        backButton.setForeground(ColorController.selectButtonForgroundColor);
        backButton.setRippleColor(new java.awt.Color(255, 255, 255));
        backButton.setShadowColor(new java.awt.Color(29, 162, 253));

        // set Font:
        convertButton.setFont(FontController.instructionLabelFont);
        backButton.setFont(FontController.instructionLabelFont);

        // button decoration:
        convertButton.setText("Convert");
        convertButton.setRound(50);
        convertButton.setFocusable(false);
        backButton.setText("Back");
        backButton.setRound(50);
        backButton.setFocusable(false);

        // add:
        add(convertButton);
        add(textRoot);
        add(backButton);
    }

    //add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    //add action listener to the convertButton:
    public void addConvertButtonActionLIstener(ActionListener actionListener){
        convertButton.addActionListener(actionListener);
    }
}
