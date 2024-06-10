package Views.Components;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import Controller.ColorController;
import Controller.FontController;
import PanelRound.PanelRound;
import button.Button;


public class ChoosePanel extends PanelRound{

    //componenet of Choose Panel:
    JLabel orderLabel1;
    JLabel orderLabel2;
    JLabel condition1Label;
    JLabel condition2Label;
    JLabel condition3Label;
    JLabel condition4Label;
    Button selectButton;
    
    //initialize the components:
    public ChoosePanel (String orderText1,String orderText2,  String condition1Text , String condition2Text, String condition3Text,String condition4Text){

        //set layout to null to arrange manually:
        this.setLayout(null);
        this.setBackground(ColorController.ChoosePanelColor);
        setRoundBottomLeft(50);
        setRoundBottomRight(50);
        setRoundTopLeft(50);
        setRoundTopRight(50);

        //initialize:
        orderLabel1= new JLabel(orderText1);
        orderLabel2= new JLabel(orderText2);
        condition1Label = new JLabel(condition1Text);
        condition2Label = new JLabel(condition2Text);
        condition3Label = new JLabel(condition3Text);
        condition4Label = new JLabel(condition4Text);
        selectButton = new Button();

        //button decoration:
        selectButton.setText("Select");
        selectButton.setRound(37);
        selectButton.setFocusable(false);

        //setFonts:
        orderLabel1.setFont(FontController.orderLabelFont);
        orderLabel2.setFont(FontController.orderLabelFont);
        condition1Label.setFont(FontController.instructionLabelFont);
        condition2Label.setFont(FontController.instructionLabelFont);
        condition3Label.setFont(FontController.instructionLabelFont);
        condition4Label.setFont(FontController.instructionLabelFont);
        selectButton.setFont(FontController.instructionLabelFont);

        //setColors:
        orderLabel1.setForeground(ColorController.OrderLabelColor);
        orderLabel2.setForeground(ColorController.OrderLabelColor);
        condition1Label.setForeground(ColorController.instructionsLabelColor);
        condition2Label.setForeground(ColorController.instructionsLabelColor);
        condition3Label.setForeground(ColorController.instructionsLabelColor);
        condition4Label.setForeground(ColorController.instructionsLabelColor);
        selectButton.setBackground(ColorController.selectButtonColor);
        selectButton.setForeground(ColorController.selectButtonForgroundColor);
        selectButton.setRippleColor(new java.awt.Color(255, 255, 255));
        selectButton.setShadowColor(new java.awt.Color(29, 162, 253));

        //set Bounds:
        orderLabel1.setBounds(60, 40, 480, 40);
        orderLabel2.setBounds(60, 70, 480, 40);
        condition1Label.setBounds(60, 130, 480, 40);
        condition2Label.setBounds(60, 190, 480, 40);
        condition3Label.setBounds(60, 250, 480, 40);
        condition4Label.setBounds(60, 310, 480, 40);
        selectButton.setBounds(300, 420, 160, 50);

        //add component to the choose panel:
        add(orderLabel1);
        add(orderLabel2);
        add(condition1Label);
        add(condition2Label);
        add(condition3Label);
        add(condition4Label);
        add(selectButton);
    }

    //add action listener to the select button:
    public void addButtonListener(ActionListener listener){
        selectButton.addActionListener(listener);
    }
    
}
