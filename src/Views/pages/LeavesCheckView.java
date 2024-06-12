package Views.pages;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controller.ColorController;
import Controller.FontController;
import button.Button;

public class LeavesCheckView extends JPanel {

    // declare components:
    Button backButton;
    Button enterLeavesButton;
    Button goLeftButton;
    Button goRighButton;

    // constructor:
    public LeavesCheckView() {

        // decorate the page:
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        backButton = new Button();
        enterLeavesButton = new Button();
        goLeftButton = new Button();
        goRighButton = new Button();

        // button decoration:
        backButton.setText("Back");
        backButton.setRound(50);
        backButton.setFocusable(false);
        enterLeavesButton.setText("Enter papers");
        enterLeavesButton.setRound(50);
        enterLeavesButton.setFocusable(false);
        goLeftButton.setText("<");
        goLeftButton.setRound(50);
        goLeftButton.setFocusable(false);
        goRighButton.setText(">");
        goRighButton.setRound(50);
        goRighButton.setFocusable(false);

        // setBounds:
        backButton.setBounds(25, 665, 100, 70);
        enterLeavesButton.setBounds(150, 665, 150, 70);
        goLeftButton.setBounds(450, 400, 70, 70);
        goRighButton.setBounds(980, 400, 70, 70);

        // set Color:
        backButton.setBackground(ColorController.selectButtonColor);
        backButton.setForeground(ColorController.selectButtonForgroundColor);
        backButton.setRippleColor(new java.awt.Color(255, 255, 255));
        backButton.setShadowColor(new java.awt.Color(29, 162, 253));
        enterLeavesButton.setBackground(ColorController.selectButtonColor);
        enterLeavesButton.setForeground(ColorController.selectButtonForgroundColor);
        enterLeavesButton.setRippleColor(new java.awt.Color(255, 255, 255));
        enterLeavesButton.setShadowColor(new java.awt.Color(29, 162, 253));
        goLeftButton.setBackground(ColorController.selectButtonColor);
        goLeftButton.setForeground(ColorController.selectButtonForgroundColor);
        goLeftButton.setRippleColor(new java.awt.Color(255, 255, 255));
        goLeftButton.setShadowColor(new java.awt.Color(29, 162, 253));
        goRighButton.setBackground(ColorController.selectButtonColor);
        goRighButton.setForeground(ColorController.selectButtonForgroundColor);
        goRighButton.setRippleColor(new java.awt.Color(255, 255, 255));
        goRighButton.setShadowColor(new java.awt.Color(29, 162, 253));

        // setFont:
        backButton.setFont(FontController.instructionLabelFont);
        enterLeavesButton.setFont(FontController.instructionLabelFont);
        goLeftButton.setFont(FontController.instructionLabelFont);
        goRighButton.setFont(FontController.instructionLabelFont);

        //set Visible:
        goLeftButton.setVisible(false);
        goRighButton.setVisible(false);

        // add:
        add(backButton);
        add(enterLeavesButton);
        add(goLeftButton);
        add(goRighButton);
    }

    // add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // add action listener to the Enter Leaves Button
    public void addEnterLeavesButtonListener(ActionListener listener) {
        enterLeavesButton.addActionListener(listener);
    }

    // add action listener to the goLeft button:
    public void addGoLeftActionListener(ActionListener actionListener) {
        goLeftButton.addActionListener(actionListener);
    }

    // add action listener to the goRight button:
    public void addGoRightButtonActionListener(ActionListener actionListener) {
        goRighButton.addActionListener(actionListener);
    }

    // pop up a Enter Information dialog
    public JDialog createEnterInfoDialog(ActionListener addListener, ActionListener doneListener) {
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog enterInfoDialog = new JDialog(mainFrame, "Enter Information", true);
        enterInfoDialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Enter name:");
        JTextField nameField = new JTextField();

        JLabel widthLabel = new JLabel("Enter width:");
        JTextField widthField = new JTextField();

        JLabel heightLabel = new JLabel("Enter height:");
        JTextField heightField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton doneButton = new JButton("Done");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addListener.actionPerformed(
                        new ActionEvent(
                                new Object[] { nameField.getText(), widthField.getText(), heightField.getText() },
                                ActionEvent.ACTION_PERFORMED, null));
                nameField.setText("");
                widthField.setText("");
                heightField.setText("");
            }
        });

        doneButton.addActionListener(e -> {
            doneListener.actionPerformed(e);
            enterInfoDialog.dispose();
        });

        enterInfoDialog.add(nameLabel);
        enterInfoDialog.add(nameField);
        enterInfoDialog.add(widthLabel);
        enterInfoDialog.add(widthField);
        enterInfoDialog.add(heightLabel);
        enterInfoDialog.add(heightField);
        enterInfoDialog.add(addButton);
        enterInfoDialog.add(doneButton);

        enterInfoDialog.pack();
        enterInfoDialog.setLocationRelativeTo(mainFrame);

        return enterInfoDialog;
    }

    //set visiblity for go right and left buttons:
    public void setGoButtonsVisiblity(boolean visibleState){
        goLeftButton.setVisible(visibleState);
        goRighButton.setVisible(visibleState);
    }

    //set bounds for go right button:
    public void setBoundsGoRight(int x,int y){
        goRighButton.setBounds(x, y, goRighButton.getWidth(), goRighButton.getHeight());
    }

    //set bounds for go left button:
    public void setBoundsGoLeft(int x,int y){
        goLeftButton.setBounds(x, y, goLeftButton.getWidth(), goLeftButton.getHeight());
    }
}
