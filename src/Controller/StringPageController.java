package Controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Model.BinaryTreeModel;
import Model.FileOperations;
import Model.Node;
import Model.RectangleOperations;
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
        textView.addResetButtonActionListener(e -> reset());
    }

    private void textToRectangle() {
        StaticMethods.resetPanel(rectangleView);
        String text = textView.getText();
        model.export(text);
        if (model.getRoot() == null) {
            StaticMethods.showMassage("Enter some thing Valid My NIGGA -<", frame, Type.INFO);
            return;
        }
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

    // convert the String to tree:
    private void textToTree(ActionEvent e) {
        StaticMethods.resetPanel(treeView);
        String text = textView.getText();
        model.export(text);
        if (model.getRoot() == null) {
            StaticMethods.showMassage("Enter some thing Valid My NIGGA -<", frame, Type.INFO);
        } else {
            StaticMethods.showMassage("Tree planted succefuly:)", frame, Type.SUCCESS);
            model.calcMaxLevel(1, model.getRoot());
            StaticMethods.resetPanel(treeView);
            TreeView.resetRoot();
            treeView.add(TreeView.textRoot);
            TreeView.textRoot.setBranchValue((int) (Math.pow(2, Math.max(0, model.getMaxLevel() - 3)) * 50));
            TreeView.textRoot.setText(Character.toString(model.getRoot().getValue()));
            ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Tree");
            StaticMethods.treeDrawer(TreeView.textRoot, model.getRoot(),treeView);
        }
    }

    // back to the main menu:
    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }

    // reset the panel:
    private void reset() {
        textView.setText("");
    }
}
