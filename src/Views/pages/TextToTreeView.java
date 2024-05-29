package Views.pages;

import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ColorController;
import Controller.FontController;
import button.Button;
import textfield.TextField;

public class TextToTreeView extends JPanel{

    //declare components:
    TextField textField;
    Button backButton;

    //constructor:
    public TextToTreeView(){

        //decorate the page:
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);
        
        //init components:
        textField= new TextField(750, 700, 800, 70);
        backButton= new Button();

        //button decoration:
        backButton.setText("Back");
        backButton.setRound(50);
        backButton.setFocusable(false);
        
        //setBounds:
        backButton.setBounds(25, 665, 100, 70);

        //setRound:
        textField.setRound(50);
        
        //set Color:
        backButton.setBackground(ColorController.selectButtonColor);
        backButton.setForeground(ColorController.selectButtonForgroundColor);
        backButton.setRippleColor(new java.awt.Color(255, 255, 255));
        backButton.setShadowColor(new java.awt.Color(29, 162, 253));
        
        //setFont:
        textField.setFont(FontController.userTextFieldInput);
        backButton.setFont(FontController.instructionLabelFont);
        
        //set aligment:
        textField.setHorizontalAlignment(JTextField.CENTER);
        
        //add:
        add(textField);
        add(backButton);
    }

    //add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    //add action listener to the convertButton:
    public void addtextFieldActionLIstener(ActionListener actionListener){
        textField.addActionListener(actionListener);
    }
}
