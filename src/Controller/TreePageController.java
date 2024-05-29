package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.pages.TreeToRectangleView;

public class TreePageController {

    //declare the components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    private TreeToRectangleView treeToRectangleView;
    JFrame frame;

    //constructor:
    public TreePageController(BinaryTreeModel model,TreeToRectangleView treeToRectangleView, JFrame frame){
        this.frame=frame;
        this.model= model;
        this.treeToRectangleView = treeToRectangleView;
        treeToRectangleView.addBackButtonActionListener(e -> backToMainMenu()); 
        treeToRectangleView.addConvertButtonActionLIstener(e-> converToNewForm());
    }

    private void converToNewForm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToNewForm'");
    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
    }


}
