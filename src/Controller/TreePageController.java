package Controller;

import java.awt.CardLayout;
import java.awt.ScrollPane;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.BinaryTreeModel;
import Model.FileOperations;
import Model.Node;
import Model.RectangleOperations;
import Views.Components.StaticMethods;
import Views.Components.TextFieldNode;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;
import javaswingdev.Notification.Type;

public class TreePageController {

    // declare the components:
    private BinaryTreeModel model;
    private TreeView treeView;
    private RectangleView rectangleView;
    private TextView textView;
    JFrame frame;

    // constructor:
    public TreePageController(BinaryTreeModel model, TreeView treeView, RectangleView rectangleView, TextView textView,
            JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.treeView = treeView;
        this.textView = textView;
        this.rectangleView = rectangleView;
        treeView.addBackButtonActionListener(e -> backToMainMenu());
        treeView.convertersActionListener(e -> treeToRectangle(), e -> treeToText());
        treeView.addResetButtonActionListener(e -> reset());
    }

    private void treeToText() {
        textView.setText("");
        StringBuilder response = new StringBuilder();
        initRoot();
        model.inorderRec(model.getRoot(), response, true);
        textView.setText(response.toString());
        StaticMethods.showMassage("Successfuly convert the tree to String", frame, Type.SUCCESS);
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Text");
    }

    // convert to paper and String:
    private void treeToRectangle() {
        StaticMethods.resetPanel(rectangleView);
        if (TreeView.textRoot.getText().equals("null")) {
            StaticMethods.showMassage("There is no Root yet", frame, Type.INFO);
            return;
        }
        initRoot();
        String filePath = StaticMethods.chooseFile();
        if (filePath == null) {
            StaticMethods.showMassage("why you don't choose a vaild file bro!", frame, Type.WARNING);
            return;
        }
        model.setPath(filePath);
        ArrayList<Node> response = model.convertToPaper();
        if (response == null) {
            StaticMethods.showMassage("The tree isn't fully grown yet ", frame, Type.INFO);
        } else if (response.isEmpty()) {
            StaticMethods.showMassage("Can't form rectangle", frame, Type.WARNING);
        } else {
            StaticMethods.showMassage("Successfuly convert the tree to rectangle", frame, Type.SUCCESS);
            rectangleView.addRectangles(response);
            ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Rectangle");
        }

    }

    private void initRoot() {
        model.setRoot(new Node(TreeView.textRoot.getText().charAt(0)));
        model.getRoot().setWidth(TreeView.textRoot.recWidth);
        model.getRoot().setHeight(TreeView.textRoot.recHeight);
        treeBuilder(model.getRoot(), TreeView.textRoot);
    }

    // back to main menu:
    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }

    // reset the panel:
    private void reset() {
        StaticMethods.resetPanel(treeView);
        TreeView.resetRoot();
        treeView.add(TreeView.textRoot);
    }

    // convert Buttons Tree to Node tree:
    private void treeBuilder(Node node, TextFieldNode textFieldNode) {
        if (textFieldNode.leftNode == null) {
            return;
        }
        String leftValue = textFieldNode.leftNode.getText();
        if (!(leftValue.equals("null"))) {
            Node leftNode = new Node(leftValue.charAt(0));
            leftNode.setHeight(textFieldNode.leftNode.recHeight);
            leftNode.setWidth(textFieldNode.leftNode.recWidth);
            node.setLeft(leftNode);
            treeBuilder(leftNode, textFieldNode.leftNode);
        }
        String rightValue = textFieldNode.rightNode.getText();
        if (!(rightValue.equals("null"))) {
            Node rightNode = new Node(rightValue.charAt(0));
            rightNode.setHeight(textFieldNode.rightNode.recHeight);
            rightNode.setWidth(textFieldNode.rightNode.recWidth);
            node.setRight(rightNode);
            treeBuilder(rightNode, textFieldNode.rightNode);
        }
        return;

    }

}
