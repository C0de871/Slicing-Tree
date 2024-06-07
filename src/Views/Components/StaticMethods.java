package Views.Components;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import Controller.ColorController;
import Controller.FontController;
import PanelRound.PanelRound;
import button.Button;
import javaswingdev.Notification;
import javaswingdev.Notification.Type;
import textfield.TextField;

public class StaticMethods {
    static boolean curState= false;

    static int width =0;
    static int height =0;

     // show massage:
     public static void showMassage(String msg,JFrame frame, Type type) {
        Notification panel = new Notification(frame, type, Notification.Location.TOP_LEFT, msg);
        panel.showNotification();
    }

    //create one Button only:
    public static Button decorateButton(){

        //create new button:
        Button button = new Button();

        // set Color:
        button.setBackground(ColorController.selectButtonColor);
        button.setForeground(ColorController.selectButtonForgroundColor);
        button.setRippleColor(new java.awt.Color(255, 255, 255));
        button.setShadowColor(new java.awt.Color(29, 162, 253));

        //set Font:
        button.setFont(FontController.okButton);

        //set Round:
        button.setRound(50);

        //set Focusuable:
        button.setFocusable(false);
        return button;

    }

    //decorate a buttons:
    public static void createButtons(Button converter1,Button converter2,Button backButton,Button resetButton,String button1Text, String button2Text, Container panal) {

        // setBounds:
        converter1.setBounds(580, 680, 150, 70);
        converter2.setBounds(770, 680, 150, 70);
        backButton.setBounds(25, 680, 100, 70);
        resetButton.setBounds(1375,680,100,70);

        // set Color:
        converter1.setBackground(ColorController.selectButtonColor);
        converter1.setForeground(ColorController.selectButtonForgroundColor);
        converter1.setRippleColor(new java.awt.Color(255, 255, 255));
        converter1.setShadowColor(new java.awt.Color(29, 162, 253));
        converter2.setBackground(ColorController.selectButtonColor);
        converter2.setForeground(ColorController.selectButtonForgroundColor);
        converter2.setRippleColor(new java.awt.Color(255, 255, 255));
        converter2.setShadowColor(new java.awt.Color(29, 162, 253));
        backButton.setBackground(ColorController.selectButtonColor);
        backButton.setForeground(ColorController.selectButtonForgroundColor);
        backButton.setRippleColor(new java.awt.Color(255, 255, 255));
        backButton.setShadowColor(new java.awt.Color(29, 162, 253));
        resetButton.setBackground(ColorController.resetButtonBackground);
        resetButton.setForeground(ColorController.selectButtonForgroundColor);
        resetButton.setRippleColor(new java.awt.Color(255, 255, 255));
        resetButton.setShadowColor(new java.awt.Color(29, 162, 253));

        // set Font:
        converter1.setFont(FontController.instructionLabelFont);
        converter2.setFont(FontController.instructionLabelFont);
        backButton.setFont(FontController.instructionLabelFont);
        resetButton.setFont(FontController.instructionLabelFont);

        // button decoration:
        converter1.setText(button1Text);
        converter1.setRound(30);
        converter1.setFocusable(false);
        converter2.setText(button2Text);
        converter2.setRound(30);
        converter2.setFocusable(false);
        backButton.setText("Back");
        backButton.setRound(50);
        backButton.setFocusable(false);
        resetButton.setText("Reset!");
        resetButton.setRound(50);
        resetButton.setFocusable(false);

        // add:
        panal.add(converter1);
        panal.add(converter2);
        panal.add(backButton);
        panal.add(resetButton);
    }

    //reset the container
    public static void resetPanel(Container c) {
        for (Component comp : c.getComponents()) {
            if(!(comp instanceof Button)){
                c.remove(comp);
            }
        }
        c.revalidate();
        c.repaint();
    }

     // Method to restrict text field to two digits only
     private static void restrictToTwoDigits(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.insert(offset, text);

                if (isNumeric(sb.toString()) && sb.length() <= 2) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.replace(offset, offset + length, text);

                if (isNumeric(sb.toString()) && sb.length() <= 2) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    // Utility method to check if a string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
