package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Model.BinaryTreeModel;
import Views.pages.LeavesCheckView;
import Views.pages.MainMenuView;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;

public class MainController {
    // declare the components:
    private JFrame frame;
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    @SuppressWarnings("unused")
    private MainMenuView mainMenuPanel;
    @SuppressWarnings("unused")
    private TreePageController treePageController;
    @SuppressWarnings("unused")
    private LeavesCheckController leavesCheckController;
    @SuppressWarnings("unused")
    private StringPageController stringPageController;
    @SuppressWarnings("unused")
    private LeavesCheckView leavesCheckView;
    @SuppressWarnings("unused")
    private RectangleView rectangleView;
    @SuppressWarnings("unused")
    private RectanglePageController rectanglePageController;
    private TreeView treeView;

    // constructor:
    public MainController(BinaryTreeModel model, MainMenuView mainMenuPanel, TreeView treeView, TextView textView,
            LeavesCheckView leavesCheckView, RectangleView rectangleView) {

        // init components:
        this.frame = new JFrame("Slicing Tree");
        this.model = model;
        this.mainMenuPanel = mainMenuPanel;
        this.treeView = treeView;
        this.leavesCheckView = leavesCheckView;
        this.rectangleView = rectangleView;
        this.treePageController = new TreePageController(model, treeView, rectangleView, textView, frame);
        this.stringPageController = new StringPageController(model, treeView, rectangleView, textView, frame);
        this.leavesCheckController = new LeavesCheckController(model, leavesCheckView, frame);
        this.rectanglePageController = new RectanglePageController(model, rectangleView,textView,treeView,frame);

        //add scrolling to tree view:
        JScrollPane scrollPane =  new JScrollPane(treeView);

        // setUp frame:
        frame.setTitle("Slicing Tree Floor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());

        // add panel to frame:
        frame.getContentPane().add(mainMenuPanel, "MainMenu");
        frame.getContentPane().add(scrollPane, "Tree");
        frame.getContentPane().add(textView, "Text");
        frame.getContentPane().add(rectangleView, "Rectangle");
        frame.getContentPane().add(leavesCheckView, "Check");

        // make frame visible:
        frame.setVisible(true);

        // add action listeners:
        mainMenuPanel.addButtonListener(e -> showTreeToRecView(), e -> showTextToTreeView(),
                e -> showCheckLeavesView());
    }

    // change the panel to Text to tree view:
    private void showTextToTreeView() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Text");
    }

    // change the panel to enter as tree view:
    private void showTreeToRecView() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Tree");
    }

    private void showCheckLeavesView() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Check");
    }

}
