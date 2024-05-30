package Views.pages;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Controller.ColorController;
import Controller.FontController;
import Views.Components.StaticMethods;
import Views.Components.TextFieldNode;
import button.Button;

public class TreeView extends JPanel {

    // components:
    Button treeToRectangleButton;
    Button treeToTextButton;
    Button backButton;
    public static TextFieldNode textRoot;

    // constructor:
    public TreeView() {
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        treeToRectangleButton = new Button();
        treeToTextButton = new Button();
        backButton = new Button();
        textRoot = new TextFieldNode(750, 70, 50, 50, 50);
        
        //Buttons Decoration:
        StaticMethods.createButtons(treeToRectangleButton,treeToTextButton,backButton,"Tree to Rec", "Tree to String", this);

        // add:
        add(textRoot);

    }

    // add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // add action listener to the converter Buttons:
    public void convertersActionListener(ActionListener treeToRectangListener, ActionListener treeToTextListener) {
        treeToRectangleButton.addActionListener(treeToRectangListener);
        treeToTextButton.addActionListener(treeToTextListener);
    }

    public static void resetRoot(){
        textRoot = new TextFieldNode(750, 70, 50, 50, 50);
    }
}
