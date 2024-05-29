package Controller;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.Components.TextFieldNode;
import Views.pages.TextToTreeView;
import textfield.TextField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;


public class StringPageController {

    //declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    TextToTreeView stringToTreeView;
    JFrame frame;

    //constructor:
    public StringPageController(BinaryTreeModel model,TextToTreeView stringToTreeView, JFrame frame){
        this.frame = frame;
        this.model = model;
        this.stringToTreeView =stringToTreeView;
        stringToTreeView.addBackButtonActionListener(e -> backToMainMenu()); 
        stringToTreeView.addtextFieldActionLIstener(e -> convertToNewForm(e)); 
    }

    private void convertToNewForm(ActionEvent e) {
        String text = ((TextField) e.getSource()).getText();
        model.export(text);
        if(model.getRoot()==null){
            System.out.println("Enter some thing my nigga :<");
        }else{
            System.out.println("Done");
        }
    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
    }
}
