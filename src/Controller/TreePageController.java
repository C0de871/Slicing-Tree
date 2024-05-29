package Controller;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Model.Node;
import Views.Components.TextFieldNode;
import Views.pages.TreeView;

public class TreePageController {

    // declare the components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    private TreeView treeToRectangleView;
    JFrame frame;

    // constructor:
    public TreePageController(BinaryTreeModel model, TreeView treeToRectangleView, JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.treeToRectangleView = treeToRectangleView;
        treeToRectangleView.addBackButtonActionListener(e -> backToMainMenu());
        treeToRectangleView.addConvertButtonActionLIstener(e -> convertToNewForm());
    }

    private void convertToNewForm() {
        if (TreeView.textRoot.getText().equals("null")) {
            System.out.println("There is no root");
            return;
        }
        model.setRoot(new Node(TreeView.textRoot.getText().charAt(0)));
        treeBuilder(model.getRoot(), TreeView.textRoot);
        ArrayList<Node> response = model.convertToPaper();
        if (response == null) {
            System.out.println("isn't completed");
        } else if (response.isEmpty()) {
            System.out.println("can't form full rectangle!");
        } else {
            System.out.println("thx kareem");
            StringBuilder response1 = new StringBuilder();
            model.inorderRec(model.getRoot(), response1, true);
            System.out.println(response1);
        }

    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }

    private void treeBuilder(Node node, TextFieldNode textFieldNode) {
        if (textFieldNode.leftNode == null)
            return;
        String leftValue = textFieldNode.leftNode.getText();
        if (!(leftValue.equals("null"))) {
            Node leftNode = new Node(leftValue.charAt(0));
            node.setLeft(leftNode);
            treeBuilder(leftNode, textFieldNode.leftNode);
        }
        String rightValue = textFieldNode.rightNode.getText();
        if (!(rightValue.equals("null"))) {
            Node righNode = new Node(rightValue.charAt(0));
            node.setRight(righNode);
            treeBuilder(righNode, textFieldNode.rightNode);
        }
        return;

    }

}
