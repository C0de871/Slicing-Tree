package Model;

import javax.swing.SwingUtilities;

import Controller.MainController;
import Views.pages.LeavesCheckView;
import Views.pages.MainMenuView;
import Views.pages.RectangleView;
import Views.pages.TextView;
import Views.pages.TreeView;

import java.io.IOException;

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
     /*   BinaryTreeModel model = new BinaryTreeModel();
        FileOperations f = new FileOperations();
        char[][] rec = f.read2DArrayFromFile("D:\\Algorithm2 Project/output.txt");
        Node root = model.buildTree(rec, 1, 1, rec.length - 2, rec[0].length - 2);
        model.setRoot(root);
        //model.rotateTree();
        RectangleOperations r = new RectangleOperations();
       //char[][] rotated = r.drawing(model.getRoot());
       // f.print2DArrayToFile(rotated, "D:\\Algorithm2 Project/rotate.txt");
        StringBuilder s = new StringBuilder();
        model.inorderRec(root, s, true);
        System.out.println(s);*/
    }
}
//((((A[60,40]-B[60,20])|C[20,60])-((D[50,30]-((E[25,20]-F[25,20])|G[25,40]))|((H[20,40]|I[10,40])-(J[30,15]-K[30,15]))))|(L[20,65]-M[20,65]))
//((A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20])))