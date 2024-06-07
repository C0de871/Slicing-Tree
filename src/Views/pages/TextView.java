package Views.pages;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ColorController;
import Controller.FontController;
import Views.Components.StaticMethods;
import button.Button;
import textfield.TextField;

public class TextView extends JPanel {

    // declare components:
    TextField textField;
    Button textToRectangleButton;
    Button textToTreeButton;
    Button backButton;
    Button resetButton;

    // constructor:
    public TextView() {

        // decorate the page:
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        textField = new TextField(750, 365, 800, 70);
        textToRectangleButton = new Button();
        resetButton = new Button();
        textToTreeButton = new Button();
        backButton = new Button();

        // button decoration:
        StaticMethods.createButtons(textToRectangleButton, textToTreeButton, backButton,resetButton, "Text To Rec", "Text to Tree",
                this);

        // setRound:
        textField.setRound(50);

        // setFont:
        textField.setFont(FontController.userTextFieldInput);

        // set aligment:
        textField.setHorizontalAlignment(JTextField.CENTER);

        // add:
        add(textField);
        add(backButton);
    }

    // add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // add action listener to the converters:
    public void convertersActionListener(ActionListener textToTreeActionListener,
            ActionListener textToRectangleActionListener) {
        textToTreeButton.addActionListener(textToTreeActionListener);
        textToRectangleButton.addActionListener(textToRectangleActionListener);
    }

    // add action listener to the reset button:
    public void addResetButtonActionListener(ActionListener actionListener) {
        resetButton.addActionListener(actionListener);
    }

    public void setText(String Text) {
        textField.setText(Text);
    }

    public String getText() {
        return textField.getText();
    }
}
