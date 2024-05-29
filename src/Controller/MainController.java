package Controller;

import java.awt.CardLayout;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.pages.LeavesCheckView;
import Views.pages.MainMenuView;
import Views.pages.TextToTreeView;
import Views.pages.TreeToRectangleView;

public class MainController {
    //declare the components:
    private JFrame frame ;
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

    //constructor:
    public MainController(BinaryTreeModel model, MainMenuView mainMenuPanel, TreeToRectangleView treeToRectangleView, TextToTreeView stringToTreeView, LeavesCheckView leavesCheckView) {

        //init components:
        this.frame = new JFrame("Slicing Tree");
        this.model = model;
        this.mainMenuPanel = mainMenuPanel;
        this.mainMenuPanel = mainMenuPanel;
        this.leavesCheckView= leavesCheckView;
        this.treePageController = new TreePageController(model, treeToRectangleView, frame);
        this.stringPageController=new StringPageController(model, stringToTreeView,frame);
        this.leavesCheckController=new LeavesCheckController(model,leavesCheckView ,frame);

        //setUp frame:
        frame.setTitle("Slicing Floor Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new CardLayout());
        
        //add panel to frame:
        frame.getContentPane().add(mainMenuPanel,"MainMenu");
        frame.getContentPane().add(treeToRectangleView,"Tree");
        frame.getContentPane().add(stringToTreeView,"Text");
        frame.getContentPane().add(leavesCheckView,"Check");
        
        //make frame visible:
        frame.setVisible(true);

        //add action listeners:       
        mainMenuPanel.addButtonListener(e -> showTreeToRecView(), e ->showTextToTreeView(), e ->showCheckLeavesView()); 
    }

    //change the panel to Text to tree view:
    private void showTextToTreeView(){
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"Text");
    }

    //change the panel to enter as tree view:
    private void showTreeToRecView(){
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"Tree");
    }
    
    private void showCheckLeavesView(){
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"Check");
    }
    
}


