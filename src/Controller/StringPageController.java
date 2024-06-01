package Controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Model.BinaryTreeModel;
import Model.Node;
import Views.Components.AnimationCompleteListener;
import Views.Components.AnimationMethods;
import Views.Components.StaticMethods;
import Views.Components.TextFieldNode;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;
import button.Button;
import javaswingdev.Notification.Type;
import textfield.TextField;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StringPageController {

    // declare the components:
    private BinaryTreeModel model;
    private TreeView treeView;
    private RectangleView rectangleView;
    private TextView textView;
    JFrame frame;

    // constructor:
    public StringPageController(BinaryTreeModel model, TreeView treeView, RectangleView rectangleView,
                                TextView textView, JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.treeView = treeView;
        this.textView = textView;
        this.rectangleView = rectangleView;
        textView.addBackButtonActionListener(e -> backToMainMenu());
        textView.convertersActionListener(e -> textToTree(e), e -> textToRectangle());
    }

    private void textToRectangle() {
        StaticMethods.resetPanel(rectangleView);
        String text = textView.getText();
        model.export(text);
        if (model.getRoot() == null) {
            StaticMethods.showMassage("Enter some thing Valid My NIGGA -<", frame, Type.INFO);
        } else {
            ArrayList<Node> response = model.convertToPaper();
            if (response == null) {
                StaticMethods.showMassage("The tree isn't fully grown yet ", frame, Type.INFO);
            } else if (response.isEmpty()) {
                StaticMethods.showMassage("Can't form rectangle", frame, Type.WARNING);
            } else {
                StaticMethods.showMassage("Successfuly convert the tree to rectangle", frame, Type.SUCCESS);
                char[][] rec = model.drawing(model.getRoot());
                model.print2DArrayToFile(rec, "D:\\Algorithm2 Project/output.txt");
                rectangleView.addRectangles(response);
                ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Rectangle");
            }
        }
    }

    // convert the String to tree:
    private void textToTree(ActionEvent e) {
        StaticMethods.resetPanel(treeView);
        String text = textView.getText();
        model.export(text);
        if (model.getRoot() == null) {
            StaticMethods.showMassage("Enter some thing Valid My NIGGA -<", frame, Type.INFO);
        } else {
            StaticMethods.showMassage("Tree was planted :)", frame, Type.SUCCESS);
            model.calcMaxLevel(1, model.getRoot());
            System.out.println(model.getMaxLevel());
            StaticMethods.resetPanel(treeView);
            TreeView.resetRoot();
            treeView.add(TreeView.textRoot);
            TreeView.textRoot.setBranchValue((int) (Math.pow(2, Math.max(0, model.getMaxLevel() - 3)) * 50));
            TreeView.textRoot.setText(Character.toString(model.getRoot().getValue()));
            ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Tree");
            treeDrawer(TreeView.textRoot, model.getRoot());
        }
    }

    // Draw the tree:
    private void treeDrawer(TextFieldNode textNode, Node root) {
        textNode.setText(Character.toString(root.getValue()));
        String text = Character.toString(root.getValue());
        if (text.equals("-") || text.equals("|")) {
            AnimationMethods newChildren = textNode.addNewChildren(textNode, treeView);
            newChildren.edgeAnimated(new AnimationCompleteListener() {
                @Override
                public void onAnimationComplete() {
                    treeDrawer(textNode.leftNode, root.getLeft());
                    treeDrawer(textNode.rightNode, root.getRight());
                }
            });
        } else if (text.matches("[A-Z]")) {
            textNode.recHeight = root.getHeight();
            textNode.recWidth = root.getWidth();
        } else {
        }
    }

    // back to the main menu:
    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
        textView.setText("");
    }
}
