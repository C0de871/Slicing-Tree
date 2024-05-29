package Controller;

import Model.BinaryTreeModel;
import Model.Node;
import View.binaryTreeView;

import java.io.IOException;


public class binaryTreeController {
    private final BinaryTreeModel model;
    private final binaryTreeView view;

    String state = "home";


    public binaryTreeController(BinaryTreeModel model, binaryTreeView view, String state) {
        this.model = model;
        this.view = view;
        this.state = state;
    }


    public void displayInorder() {
        StringBuilder result = new StringBuilder();
        model.inorder(result);
        view.displayInorder(result.toString());
    }


    public void exportTree() {
        String formula = view.exploring();
        if (model.canFormRectangle(formula)) {
            model.export(formula);
            view.displayTree(model);
        } else {
            view.handleErrors("Invalid formula");
        }
    }

    public void insertNode() {
        view.printM("Enter root value");
        Node root = new Node(view.getChar());
        model.setRoot(root);
        model.insertNode();
        view.printM("All leaves are letters or invalid characters and all '-' or '|' nodes have two children. Insertion complete.");
    }

    public void handleUserInput() {
        int choice = view.displayOptions();
        switch (choice) {
            case 1:
                state = "insert node";
                autoUI();
                break;
            case 2:
                state = "tree to string";
                autoUI();
                break;
            case 3:
                state = "export tree";
                autoUI();
                break;
            case 4:
                state = "drawing rectangle";
                autoUI();
                break;
            case 5:
                state = "drawing rectangle On File";
                autoUI();
            case 6:
                state = "Rotate a rectangle";
                autoUI();
            case 7:
                state = "covert from rectangle to tree";
                autoUI();
            default:
                System.out.println("Invalid option. Please try again.");
                autoUI();
                break;
        }
    }

    public void autoUI() {
        switch (state) {
            case "home":
                handleUserInput();
            case "insert node":
                insertNode();
                break;
            case "tree to string":
                displayInorder();
                break;
            case "export tree":
                exportTree();
                break;
            case "drawing rectangle":
                char[][] canvas = model.drawing(this.model.getRoot());
                view.printCanvas(canvas);
                break;
            case "drawing rectangle On File":
                char[][] canvasFile = model.drawing(this.model.getRoot());
                view.print2DArrayToFile(canvasFile, "output.txt");
                break;
            case "Rotate a rectangle":
                char[][] canvasRotate = model.drawing(this.model.getRoot());
                view.printCanvas(model.transposeMatrix(canvasRotate));
                break;
            case "covert from rectangle to tree":
                String filePath = "D:\\Algorithm2 Project/output.txt";
                try {
                    char[][] canvasTree = model.read2DArrayFromFile(filePath);
                   //model.setRoot(model.reconstructTree(canvasTree));
                    displayInorder();
                    //convert from tree to string
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        handleUserInput();
    }
}

