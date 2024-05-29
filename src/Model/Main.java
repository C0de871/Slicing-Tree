package Model;

import javax.swing.SwingUtilities;

import Controller.MainController;
import Views.pages.LeavesCheckView;
import Views.pages.MainMenuView;
import Views.pages.TextToTreeView;
import Views.pages.TreeView;

public class Main {
    public static void main(String[] args) {

      /*  SwingUtilities.invokeLater(()-> {
            BinaryTreeModel model = new BinaryTreeModel();
            MainMenuView mainMenuPanel =new MainMenuView();
            TreeView treeToRectangleView = new TreeView();
            TextToTreeView textToTreeView =new TextToTreeView();
            LeavesCheckView leavesCheckView = new LeavesCheckView();
            new MainController(model, mainMenuPanel, treeToRectangleView, textToTreeView,leavesCheckView);
        });*/
        BinaryTreeModel tree=new BinaryTreeModel();
        tree.export("((((A[60,40]-B[60,20])|C[20,60])-((D[50,30]-((E[25,20]-F[25,20])|G[25,40]))|((H[20,40]|I[10,40])-(J[30,15]-K[30,15]))))|(L[20,65]-M[20,65]))");
        Paper p = new Paper(tree.convertToPaper());

    }
}
//((((A[60,40]-B[60,20])|C[20,60])-((D[50,30]-((E[25,20]-F[25,20])|G[25,40]))|((H[20,40]|I[10,40])-(J[30,15]-K[30,15]))))|(L[20,65]-M[20,65]))
//