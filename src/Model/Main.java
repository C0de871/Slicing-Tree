package Model;

import Controller.binaryTreeController;
import View.binaryTreeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        binaryTree tree = new binaryTree();
        binaryTreeView view = new binaryTreeView();
        binaryTreeController controller = new binaryTreeController(tree, view);
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Insert tree");
            System.out.println("2. Build specific tree");
            System.out.println("3. Inorder traversal");
            System.out.println("4. Convert to paper");
            System.out.println("5. Export tree from string");
            System.out.println("6. Check if can form rectangle");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

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

        scanner.close();
    }
}
