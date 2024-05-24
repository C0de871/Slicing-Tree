package Model;

import Controller.binaryTreeController;
import View.binaryTreeView;


public class Main {
    public static void main(String[] args) {
        binaryTree tree = new binaryTree();
        binaryTreeView view = new binaryTreeView();
        binaryTreeController controller = new binaryTreeController(tree, view);
        boolean exit = false;
        while (!exit) {
            int choice = view.displayOptions();
            switch (choice) {
                case 1:
                    controller.insertNode();
                    break;
                case 2:
                    controller.buildSpecificTree();
                    System.out.println("Specific tree built.");
                    break;
                case 3:
                    controller.displayInorder();
                    break;
                case 4:
                    controller.convertToPaper();
                    System.out.println("Converted to paper.");
                    break;
                case 5:
                    controller.exportTree();
                    System.out.println("Tree exported from string.");
                    break;
                case 6:
                    controller.canFormRectangle();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
