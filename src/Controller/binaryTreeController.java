package Controller;

import Model.binaryTree;
import Model.Node;
import View.binaryTreeView;

import java.util.ArrayList;
import java.util.Scanner;

public class binaryTreeController {
    private final binaryTree model;
    private final binaryTreeView view;


    public binaryTreeController(binaryTree model, binaryTreeView view) {
        this.model = model;
        this.view = view;
    }

    public void buildSpecificTree() {
        model.buildSpecificTree();
        view.displayTree(model);
    }

    public void displayInorder() {
        StringBuilder result = new StringBuilder();
        model.inorder(result);
        view.displayInorder(result.toString());
    }

    public void convertToPaper() {
        ArrayList<Node> pieces = model.convertToPaper();
        view.displayPaperPieces(pieces);
    }

    public void exportTree() {
        model.export(view.exploring());
        view.displayTree(model);
    }

    public void canFormRectangle() {
        String s = view.RectangleCheckString();
        boolean result = model.canFormRectangle(s);
        view.displayCanFormRectangle(result);
    }

    public void insertNode() {
        System.out.print("Enter root value: ");
        Scanner scanner = new Scanner(System.in);
        char rootValue = scanner.next().charAt(0);
        Node root = new Node(rootValue);
        model.setRoot(root);
        do {
            String path = view.promptPath();
            char value = view.promptValue();
            Integer width = view.promptX();
            Integer height = view.promptY();

            if (model.insert(path, value, width, height)) {
                System.out.println("Node inserted successfully.");
            } else {
                System.out.println("Failed to insert node.");
            }
        } while (view.promptContinue());
    }


}
