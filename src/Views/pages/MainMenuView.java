package Views.pages;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.ColorController;
import Controller.FontController;
import Views.Components.AnimatedStringPanel;
import Views.Components.AnimationMethods;
import Views.Components.ChoosePanel;
import Views.Components.RectanglePanel;
import button.Button;

public class MainMenuView extends JPanel {

    // panels of the Main Menu Panel:
    ChoosePanel textToTreeChoicePanel;
    ChoosePanel enterAsTreeChoicePanel;
    AnimatedStringPanel animatedStringPanel;
    RectanglePanel parentNode;
    RectanglePanel leftNode;
    RectanglePanel rightNode;
    AnimationMethods animatedEdgePanel;
    Button checkButton;

    public MainMenuView() {

        
        
        this.setLayout(null);
        this.setBackground(ColorController.MainMenuColor);
        
        // initialize the two main panel of the main frame:
        textToTreeChoicePanel = new ChoosePanel("Enter the tree as a text here ", "the instructions:",
        "• use - or | to seperate two group of rectangles.", "• use () to group rectangles together",
        "• use this form to create a leaf node: A[hieght, width]", "");
        enterAsTreeChoicePanel = new ChoosePanel("Enter the tree directly here ", "the insturctions:",
        "1. Click on the node.", "2. Enter | or - to make two children of this node.",
        "3. Enter character A-Z and the node will be a leaf", "");
        animatedStringPanel = new AnimatedStringPanel();
        
        // set bounds of the components:
        textToTreeChoicePanel.setBounds(125, 75, 500, 500);
        enterAsTreeChoicePanel.setBounds(850, 75, 500, 500);
        int enterAsTreePanelMidX = enterAsTreeChoicePanel.getX() + (enterAsTreeChoicePanel.getWidth() / 2);
        int enterAsTreePanelMidY = enterAsTreeChoicePanel.getY() + enterAsTreeChoicePanel.getHeight() + 40;
        checkButton = new Button();
        parentNode = new RectanglePanel(enterAsTreePanelMidX, enterAsTreePanelMidY, 50, 29, 'A');
        leftNode = new RectanglePanel(enterAsTreePanelMidX - 50, enterAsTreePanelMidY + 55, 50, 29, 'B');
        rightNode = new RectanglePanel(enterAsTreePanelMidX + 50, enterAsTreePanelMidY + 55, 50, 29, 'C');
        checkButton.setBounds(1360, 690, 160, 50);
        animatedEdgePanel = new AnimationMethods(parentNode, leftNode, rightNode, this);
        Point middle = getMid(textToTreeChoicePanel, animatedStringPanel.getPreferredSize().width);
        int x = middle.x;
        int y = middle.y;
        animatedStringPanel.setBounds(x, y, animatedStringPanel.getPreferredSize().width,
        animatedStringPanel.getPreferredSize().height);
        
        //set Font:
        checkButton.setFont(FontController.instructionLabelFont);
        checkButton.setHorizontalAlignment(SwingConstants.LEFT);;
        
        //set Colors:
        checkButton.setBackground(ColorController.selectButtonColor);
        checkButton.setForeground(ColorController.selectButtonForgroundColor);
        checkButton.setRippleColor(new java.awt.Color(255, 255, 255));
        checkButton.setShadowColor(new java.awt.Color(29, 162, 253));
        
        // start animation
        animatedEdgePanel.startAnimation();

        // button decoration:
        checkButton.setText("  Checker");
        checkButton.setRound(-1);
        checkButton.setFocusable(false);
        
        // addes:
        this.add(textToTreeChoicePanel);
        this.add(enterAsTreeChoicePanel);
        this.add(animatedStringPanel);
        this.add(parentNode);
        this.add(leftNode);
        this.add(rightNode);
        this.add(checkButton);
        
    }
    
    // get the middle point:
    public Point getMid(JPanel p1, int width) {
        
        // Calculate the x coordinate to center animatedStringPanel under
        // textToTreePanel
        int x = p1.getX() + ((p1.getWidth() / 2) - (width / 2));
        int y = p1.getY() + p1.getHeight() + 45; // 60 pixels below textToTreePanel
        return new Point(x, y);
    }

    // add action listener to buttons:
    public void addButtonListener(ActionListener selectEnterAsTreeListener, ActionListener selectStringToTreeListener,ActionListener checkButtonActionListener) {
        enterAsTreeChoicePanel.addButtonListener(selectEnterAsTreeListener);
        textToTreeChoicePanel.addButtonListener(selectStringToTreeListener);
        checkButton.addActionListener(checkButtonActionListener);
    }
}
