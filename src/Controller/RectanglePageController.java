package Controller;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.Components.StaticMethods;
import Views.pages.LeavesCheckView;
import Views.pages.RectangleView;
import Views.pages.TextView;
import button.Button;

import java.awt.CardLayout;
import java.awt.Component;


public class RectanglePageController {

    //declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    RectangleView rectangleView;
    JFrame frame;
    TextView textView ;

    //constructor:
    public RectanglePageController(BinaryTreeModel model,RectangleView rectangleView,TextView textView, JFrame frame){
        this.frame = frame;
        this.model = model;
        this.rectangleView =rectangleView;
        this.textView = textView;
        rectangleView.addBackButtonActionListener(e -> backToMainMenu()); 
        rectangleView.addConvertersActionListener(e -> rectangleToTree(), e -> rectangleToString()); 
        
    }

    private void rectangleToString() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"Text");
        
    }

    private void rectangleToTree() {

        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"Tree");
    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
        StaticMethods.resetPanel(rectangleView);
    }
}
