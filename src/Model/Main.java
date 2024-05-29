package Model;

import Controller.binaryTreeController;
import View.binaryTreeView;

public class Main {
    public static void main(String[] args) {
        BinaryTreeModel tree = new BinaryTreeModel();
        binaryTreeView view = new binaryTreeView();
        binaryTreeController controller = new binaryTreeController(tree, view,"home");
        controller.autoUI();
    }
}
//((((A[60,40]-B[60,20])|C[20,60])-((D[50,30]-((E[25,20]-F[25,20])|G[25,40]))|((H[20,40]|I[10,40])-(J[30,15]-K[30,15]))))|(L[20,65]-M[20,65]))
//((A[20,10]|(B[20,10]|C[30,10]))-(D[30,50]|(E[40,30]-F[40,20])))