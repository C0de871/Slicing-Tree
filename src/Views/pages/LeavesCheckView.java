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
import textfield.TextField;

public class LeavesCheckView extends JPanel {

    // declare components:
    TextField textField;
    Button backButton;
    Button enterLeavesButton;

    // constructor:
    public LeavesCheckView() {

        // decorate the page:
        this.setBackground(ColorController.MainMenuColor);
        this.setLayout(null);

        // init components:
        textField = new TextField(750, 700, 800, 70);
        backButton = new Button();
        enterLeavesButton = new Button();

        // button decoration:
        backButton.setText("Back");
        backButton.setRound(50);
        backButton.setFocusable(false);
        enterLeavesButton.setText("Enter papers");
        enterLeavesButton.setRound(50);
        enterLeavesButton.setFocusable(false);

        // setBounds:
        backButton.setBounds(25, 665, 100, 70);
        enterLeavesButton.setBounds(150, 665, 150, 70);

        // setRound:
        textField.setRound(50);

        // set Color:
        backButton.setBackground(ColorController.selectButtonColor);
        backButton.setForeground(ColorController.selectButtonForgroundColor);
        backButton.setRippleColor(new java.awt.Color(255, 255, 255));
        backButton.setShadowColor(new java.awt.Color(29, 162, 253));
        enterLeavesButton.setBackground(ColorController.selectButtonColor);
        enterLeavesButton.setForeground(ColorController.selectButtonForgroundColor);
        enterLeavesButton.setRippleColor(new java.awt.Color(255, 255, 255));
        enterLeavesButton.setShadowColor(new java.awt.Color(29, 162, 253));

        // setFont:
        textField.setFont(FontController.userTextFieldInput);
        backButton.setFont(FontController.instructionLabelFont);
        enterLeavesButton.setFont(FontController.instructionLabelFont);

        // set aligment:
        textField.setHorizontalAlignment(JTextField.CENTER);

        // add:
        add(textField);
        add(backButton);
        add(enterLeavesButton);
    }

    // add action listener to the back button:
    public void addBackButtonActionListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    // add action listener to the convertButton:
    public void addtextFieldActionLIstener(ActionListener actionListener) {
        textField.addActionListener(actionListener);
    }

    // add action listener to the Enter Leaves Button
    public void addEnterLeavesButtonListener(ActionListener listener) {
        enterLeavesButton.addActionListener(listener);
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
                        new ActionEvent(new Object[] { nameField.getText(), widthField.getText(), heightField.getText() },
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
}
