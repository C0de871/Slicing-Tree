package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Model.Node;
import Views.Components.TextFieldNode;
import Views.pages.TreeView;

public class TreePageController {

    //declare the components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    private TreeView treeToRectangleView;
    JFrame frame;

    //constructor:
    public TreePageController(BinaryTreeModel model,TreeView treeToRectangleView, JFrame frame){
        this.frame=frame;
        this.model= model;
        this.treeToRectangleView = treeToRectangleView;
        treeToRectangleView.addBackButtonActionListener(e -> backToMainMenu()); 
        treeToRectangleView.addConvertButtonActionLIstener(e-> convertToNewForm());
    }

    private void convertToNewForm() {
        Node node =null;
        if(TreeView.textRoot.getText().equals("null")){
            return ;
        }
        node= new Node(TreeView.textRoot.getText().charAt(0));
        treeBuilder(node, TreeView.textRoot);
        model.convertToPaper();

    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
    }

    private void treeBuilder(Node node,TextFieldNode textFieldNode){
        if(textFieldNode.leftNode==null)return ;
        String leftValue = textFieldNode.leftNode.getText();
        if(!(leftValue.equals("null"))){
            Node leftNode = new Node(leftValue.charAt(0));
            node.setLeft(leftNode);
            treeBuilder(leftNode, textFieldNode.leftNode);  
        }
        String rightValue = textFieldNode.rightNode.getText();
        if(!(rightValue.equals("null"))){
            Node righNode = new Node(rightValue.charAt(0));
            node.setRight(righNode);
            treeBuilder(righNode, textFieldNode.rightNode);
        }
        return;

    }


}
