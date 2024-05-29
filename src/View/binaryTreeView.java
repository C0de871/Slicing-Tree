package View;

import Model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class binaryTreeView {
    private static Scanner scanner;

    public binaryTreeView() {
        scanner = new Scanner(System.in);
    }

    public void displayTree(BinaryTreeModel tree) {
        StringBuilder result = new StringBuilder();
        tree.inorder(result);
        System.out.println("Tree (inorder): " + result.toString());
    }

    public void displayInorder(String inorder) {
        System.out.println("Inorder traversal: " + inorder);
    }


    public void displayCanFormRectangle(boolean canForm) {
        System.out.println("Can form rectangle: " + canForm);
    }


    public static String promptPath() {
        System.out.print("Enter path: ");
        return scanner.nextLine().trim();
    }

    public static char promptValue() {
        System.out.print("Enter value: ");
        return scanner.nextLine().trim().charAt(0);
    }


    public static Integer promptX() {
        System.out.print("Enter width (or press Enter to skip): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() || input.equals("skip") ? null : Integer.parseInt(input);
    }

    public static Integer promptY() {
        System.out.print("Enter height (or press Enter to skip): ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() || input.equals("skip") ? null : Integer.parseInt(input);
    }

    public String exploring() {
        System.out.print("Enter tree string: ");
        return scanner.nextLine().trim();
    }
//function to displayOptions to the user
    public int displayOptions() {
        System.out.println("Select an option:");
        System.out.println("1. Insert tree");
        System.out.println("2. Inorder traversal");
        System.out.println("3. Export tree from string");
        System.out.println("4. draw");
        System.out.println("5. drawing rectangle On File");
        System.out.println("6. draw a rectangle Rotated");
        System.out.println("7. covert from rectangle to tree");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
//function to print 2D array on the console
    public void printCanvas(char[][] canvas) {
        for (char[] canva : canvas) {
            for (char c : canva) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public void handleErrors(String error) {
        System.out.println("Error :" + error);
    }

    public static void printM(String m) {
        System.out.println(m);
    }

    //function to get a character from user
    public char getChar() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().trim().charAt(0);
    }
    //function that I give it the array and the filePath which I want to write the array on it
    public  void print2DArrayToFile(char[][] array, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (char[] row : array) {
                for (char ch : row) {
                    writer.write(ch);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
