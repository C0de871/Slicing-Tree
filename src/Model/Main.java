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
