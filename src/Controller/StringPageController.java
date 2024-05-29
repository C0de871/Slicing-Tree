package Controller;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.pages.TextToTreeView;
import java.awt.CardLayout;


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
        stringToTreeView.addtextFieldActionLIstener(e -> convertToNewForm()); 

    }

    private void convertToNewForm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToNewForm'");
    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
    }
}
