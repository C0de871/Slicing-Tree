package Controller;

import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Views.pages.LeavesCheckView;
import java.awt.CardLayout;


public class LeavesCheckController {

    //declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    LeavesCheckView leavesCheckView;
    JFrame frame;

    //constructor:
    public LeavesCheckController(BinaryTreeModel model,LeavesCheckView leavesCheckView, JFrame frame){
        this.frame = frame;
        this.model = model;
        this.leavesCheckView =leavesCheckView;
        leavesCheckView.addBackButtonActionListener(e -> backToMainMenu()); 
        leavesCheckView.addtextFieldActionLIstener(e -> convertToNewForm()); 
        
    }

    private void convertToNewForm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToNewForm'");
    }

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(),"MainMenu");
    }
}
