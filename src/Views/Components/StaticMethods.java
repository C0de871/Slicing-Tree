package Views.Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import Controller.ColorController;
import Controller.FontController;
import Model.Node;
import Views.pages.TreeView;
import button.Button;
import javaswingdev.Notification;
import javaswingdev.Notification.Type;
import jnafilechooser.api.JnaFileChooser;

public class StaticMethods {
    static boolean curState = false;

    static int width = 0;
    static int height = 0;

    // show massage:
    public static void showMassage(String msg, JFrame frame, Type type) {
        Notification panel = new Notification(frame, type, Notification.Location.TOP_LEFT, msg);
        panel.showNotification();
    }

    // create one Button only:
    public static Button decorateButton() {

        // create new button:
        Button button = new Button();

        // set Color:
        button.setBackground(ColorController.selectButtonColor);
        button.setForeground(ColorController.selectButtonForgroundColor);
        button.setRippleColor(new java.awt.Color(255, 255, 255));
        button.setShadowColor(new java.awt.Color(29, 162, 253));

        // set Font:
        button.setFont(FontController.okButton);

        // set Round:
        button.setRound(50);

        // set Focusuable:
        button.setFocusable(false);
        return button;

    }

    // Draw the tree:
    public static void treeDrawer(TextFieldNode textNode, Node root,TreeView treeView) {
        textNode.setText(Character.toString(root.getValue()));
        String text = Character.toString(root.getValue());
        if (text.equals("-") || text.equals("|")) {
            AnimationMethods newChildren = textNode.addNewChildren(textNode, treeView);
            newChildren.edgeAnimated(new AnimationCompleteListener() {
                @Override
                public void onAnimationComplete() {
                    treeDrawer(textNode.leftNode, root.getLeft(),treeView);
                    treeDrawer(textNode.rightNode, root.getRight(),treeView);
                }
            });
        } else if (text.matches("[A-Z]")) {
            textNode.recHeight = root.getHeight();
            textNode.recWidth = root.getWidth();
        } else {
        }
    }

    // decorate a buttons:
    public static void createButtons(Button converter1, Button converter2, Button backButton, Button resetButton,
            String button1Text, String button2Text, Container panal) {

        // setBounds:
        converter1.setBounds(580, 680, 150, 70);
        converter2.setBounds(770, 680, 150, 70);
        backButton.setBounds(25, 680, 100, 70);
        resetButton.setBounds(1375, 680, 100, 70);

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

    // reset the container buttons:
    public static void resetPanelButtons(Container c) {
        for (Component comp : c.getComponents()) {
            if (!(comp instanceof Button)) {
                c.remove(comp);
            }
        }
        c.revalidate();
        c.repaint();
    }

    // reset the container Lable:
    public static void resetPanelLable(Container c) {
        for (Component comp : c.getComponents()) {
            if ((comp instanceof JLabel)) {
                c.remove(comp);
            }
        }
        c.revalidate();
        c.repaint();
    }

    // file chooser:
    public static String chooseFile() {
        // Create a file chooser
        JnaFileChooser fileChooser = new JnaFileChooser();

        // Limit the file chooser to .txt filesf
        fileChooser.addFilter("Text Files", "txt");

        boolean result = fileChooser.showOpenDialog(null); // Pass null as the parent component

        // If the user selects a file
        if (result) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            return null;
        }
    }

    // Method to restrict text field to two digits only
    @SuppressWarnings("unused")
    private static void restrictToTwoDigits(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
                sb.insert(offset, text);

                if (isNumeric(sb.toString()) && sb.length() <= 2) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
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

    // add rectangle:
    public static void addRectangles(ArrayList<Node> pieces,Node root,JPanel panel) {
        //1500,800
        StaticMethods.resetPanelLable(panel);
        Border border = BorderFactory.createLineBorder(new Color(0x01B1717), 2);
        int width = root.getWidth()*5;
        int height = root.getHeight()*5;    
        for (Node p : pieces) {
            JLabel l = new JLabel("", SwingConstants.CENTER);
            l.setText(String.valueOf(p.getValue()));
            System.out.println(p.getX());
            System.out.println(p.getY());
            l.setBounds(((1500/2)-(width/2)) + p.getX()*5, ((800/2)-(height/2))+ p.getY()*5, p.getWidth() * 5, p.getHeight() * 5);
            l.setBackground(Color.white);
            l.setOpaque(true);
            l.setForeground(new Color(0x01B1717));
            l.setFont(new Font("Serif", Font.BOLD, 16));
            l.setBorder(border);
            panel.add(l);
        }
    }
}
