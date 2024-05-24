package View;

import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class binaryTreeView {
    private final Scanner scanner;

    public binaryTreeView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayTree(binaryTree tree) {
        StringBuilder result = new StringBuilder();
        tree.inorder(result);
        System.out.println("Tree (inorder): " + result.toString());
    }

    public void displayInorder(String inorder) {
        System.out.println("Inorder traversal: " + inorder);
    }

    public void displayPaperPieces(ArrayList<Node> pieces) {
        System.out.println("Paper pieces:");
        for (Node p : pieces) {
            System.out.println(p.getValue() + " " + p.getWidth() + " " + p.getHeight() + " " + p.getX() + " " + p.getY());
        }
    }

    public void displayCanFormRectangle(boolean canForm) {
        System.out.println("Can form rectangle: " + canForm);
    }


    public String promptPath() {
        System.out.print("Enter path: ");
        return scanner.nextLine().trim();
    }

    public char promptValue() {
        System.out.print("Enter value: ");
        return scanner.nextLine().trim().charAt(0);
    }


    public Integer promptX() {
        System.out.print("Enter width (or press Enter to skip): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() || input.equals("skip") ? null : Integer.parseInt(input);
    }

    public Integer promptY() {
        System.out.print("Enter height (or press Enter to skip): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() || input.equals("skip") ? null : Integer.parseInt(input);
    }

    public String RectangleCheckString() {
        System.out.print("Enter tree string to check: ");
        return scanner.nextLine().trim();
    }

    public String exploring() {
        System.out.print("Enter tree string: ");
        return scanner.nextLine().trim();
    }
    public int displayOptions() {
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
        return choice;
    }
}
