package Model;

import java.io.IOException;

import javax.swing.SwingUtilities;

import Controller.MainController;
import Views.pages.LeavesCheckView;
import Views.pages.MainMenuView;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;

public class Main {
    public static void main(String[] args) throws IOException {

  SwingUtilities.invokeLater(() -> {
            BinaryTreeModel model = new BinaryTreeModel();
            MainMenuView mainMenuPanel = new MainMenuView();
            TreeView treeToRectangleView = new TreeView();
            TextView textToTreeView = new TextView();
            RectangleView rectangleView = new RectangleView();
            LeavesCheckView leavesCheckView = new LeavesCheckView();
            new MainController(model, mainMenuPanel, treeToRectangleView, textToTreeView, leavesCheckView, rectangleView);
        });
    }
}
//((A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20])))
//(A[10,20]|B[10,20])
