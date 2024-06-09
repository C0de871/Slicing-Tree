package Controller;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Model.Node;
import Views.Components.StaticMethods;
import Views.pages.LeavesCheckView;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;
import button.Button;
import javaswingdev.Notification.Type;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

public class RectanglePageController {

    // declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    RectangleView rectangleView;
    JFrame frame;
    TextView textView;
    TreeView treeView;

    // constructor:
    public RectanglePageController(BinaryTreeModel model, RectangleView rectangleView, TextView textView,TreeView treeView,
            JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.rectangleView = rectangleView;
        this.textView = textView;
        this.treeView = treeView;
        rectangleView.addBackButtonActionListener(e -> backToMainMenu());
        rectangleView.addConvertersActionListener(e -> rectangleToTree(), e -> rectangleToText());
        rectangleView.addResetButtonActionListener(e -> reset());
        rectangleView.addRotateButtonActionListener(e -> rotate());
    }

    private void rotate() {
        model.rotateRecangel();
        ArrayList<Node> paper = model.convertToPaper();
        this.reset();
        rectangleView.addRectangles(paper);
    }

    private void rectangleToText() {
        
        textView.setText("");
        String response = model.fromRecToText();
        textView.setText(response);
        StaticMethods.showMassage("Successfuly convert the Rectangle to Text", frame, Type.SUCCESS);
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Text");
    }

    private void rectangleToTree() {
        Node response = model.drawTreeFromRec();
        if (response == null) {
            StaticMethods.showMassage("Failed", frame, Type.WARNING);
            return;
        }
        StaticMethods.showMassage("Tree planted succefully :)", frame, Type.SUCCESS);
        model.setRoot(response);
        model.calcMaxLevel(1, response);
        StaticMethods.resetPanel(treeView);
        TreeView.resetRoot();
        treeView.add(TreeView.textRoot);
        TreeView.textRoot.setBranchValue((int) (Math.pow(2, Math.max(0, model.getMaxLevel() - 3)) * 50));
        TreeView.textRoot.setText(Character.toString(model.getRoot().getValue()));
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Tree");
        StaticMethods.treeDrawer(TreeView.textRoot, model.getRoot(),treeView);
    }

    private void backToMainMenu() {

        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }

    // reset the panel:
    private void reset() {
        StaticMethods.resetPanel(rectangleView);
    }
}
