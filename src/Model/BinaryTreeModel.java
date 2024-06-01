package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BinaryTreeModel {
    private Node root;
    private int maxLevel = 0;

    public int getMaxLevel() {
        return maxLevel;
    }

    public BinaryTreeModel() {
        root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    // function to check if the user can input a more nodes or not
    public boolean isComplete(Node node) {
        if (node == null) return false;
        if (Character.isLetter(node.getValue())) {
            return true;
        }
        return (isComplete(node.getLeft()) && (isComplete(node.getRight())));

    }

    public void inorderRec(Node node, StringBuilder result, boolean isRoot) {
        if (node != null) {
            boolean needParentheses = node.getLeft() != null || node.getRight() != null;
            if (needParentheses && isRoot) {
                result.append("(");
            }
            if (needParentheses && !isRoot) {
                result.append("(");
            }
            inorderRec(node.getLeft(), result, false);
            result.append(node.getValue());
            if (node.getWidth() != null && node.getHeight() != null && node.getValue() != '-'
                    && node.getValue() != '|') {
                result.append("[").append(node.getWidth()).append(",").append(node.getHeight()).append("]");
            }
            inorderRec(node.getRight(), result, false);

            if (needParentheses && !isRoot) {
                result.append(")");
            }

            if (needParentheses && isRoot) {
                result.append(")");
            }
        }
    }

    // function to convert from string to a tree
    public void export(String s) {
        s = s.trim();
        s = s.replace(" ", "");
        Stack<Character> operators = new Stack<>();
        Stack<Node> nodes = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                continue;
            } else if (c == ')') {
                Node right = nodes.pop();
                Node left = nodes.pop();
                char operator = operators.pop();
                Node operatorNode = new Node(operator);
                operatorNode.setLeft(left);
                operatorNode.setRight(right);
                nodes.push(operatorNode);
            } else if (c == '|' || c == '-') {
                operators.push(c);
            } else if (Character.isUpperCase(c)) {
                i++;
                StringBuilder sub = new StringBuilder();
                while (s.charAt(++i) != ',') {
                    sub.append(s.charAt(i));
                }
                int width = Integer.parseInt(sub.toString());
                sub.setLength(0);
                while (s.charAt(++i) != ']') {
                    sub.append(s.charAt(i));
                }
                int height = Integer.parseInt(sub.toString());
                nodes.push(new Node(c, width, height));
            }
        }
        if (!nodes.isEmpty()) {
            setRoot(nodes.pop());
        }
    }

    // function to check if the Entered string can form a rectangle or not
    public boolean canFormRectangle(String input) {
        Stack<Node> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                continue;
            } else if (c == ')') {
                Node right = stack.pop();
                Node operator = stack.pop();
                Node left = stack.pop();

                if (operator.getValue() == '|') {
                    if (Objects.equals(left.getHeight(), right.getHeight())) {
                        Node newNode = new Node(operator.getValue(), left.getWidth() + right.getWidth(),
                                left.getHeight());
                        stack.push(newNode);
                    } else {
                        return false;
                    }
                } else if (operator.getValue() == '-') {
                    if (Objects.equals(left.getWidth(), right.getWidth())) {
                        Node newNode = new Node(operator.getValue(), left.getWidth(),
                                left.getHeight() + right.getHeight());
                        stack.push(newNode);
                    } else {
                        return false;
                    }
                }
            } else if (Character.isLetter(c)) {
                int start = input.indexOf('[', input.indexOf(c)) + 1;
                int end = input.indexOf(',', start);
                int width = Integer.parseInt(input.substring(start, end));
                start = end + 1;
                end = input.indexOf(']', start);
                int height = Integer.parseInt(input.substring(start, end));
                Node newNode = new Node(c, width, height);
                stack.push(newNode);
            } else if (c == '|' || c == '-') {
                Node operator = new Node(c, 0, 0);
                stack.push(operator);
            }
        }

        return stack.size() == 1;
    }

    // function to draw a rectangle from a tree

    public char[][] drawing(Node root) {
        calculateDimensions(root);
        int width = root.getWidth();
        int height = root.getHeight();
        char[][] canvas = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = ' ';
            }
        }
        for (int i = 0; i < width; i++) {
            canvas[0][i] = '-';
            canvas[height - 1][i] = '-';
        }
        for (int i = 0; i < height; i++) {
            canvas[i][0] = '|';
            canvas[i][width - 1] = '|';
        }
        convertToPaper(root, canvas);
        return canvas;
    }


    private void convertToPaper(Node root, char[][] paper) {
        if (root.getLeft() == null) {
            return;
        }

        root.getLeft().setX(root.getX());
        root.getLeft().setY(root.getY());
        root.getRight().setX(root.getX());
        root.getRight().setY(root.getY());

        if (root.getLeft().getValue() != '|' && root.getLeft().getValue() != '-') {
            fillPaper(root.getLeft(), paper);
        } else {
            convertToPaper(root.getLeft(), paper);
        }

        if (root.getValue() == '|') {
            root.getRight().setX(root.getRight().getX() + root.getLeft().getWidth());
        } else {
            root.getRight().setY(root.getRight().getY() + root.getLeft().getHeight());
        }

        if (root.getRight().getValue() != '|' && root.getRight().getValue() != '-') {
            fillPaper(root.getRight(), paper);
        } else {
            convertToPaper(root.getRight(), paper);
        }
    }

    private void fillPaper(Node node, char[][] paper) {
        int startX = node.getX();
        int startY = node.getY();
        int endX = startX + node.getWidth() - 1;
        int endY = startY + node.getHeight() - 1;

        for (int i = startY; i <= endY; i++) {
                paper[i][startX] = '|';
        }
        // Draw top and bottom borders
        for (int j = startX; j <= endX; j++) {
                paper[startY][j] = '-';
        }

        // Place the node value in the center
        if (node.getHeight() > 2 && node.getWidth() > 2) {
            int centerX = startX + (node.getWidth() / 2);
            int centerY = startY + (node.getHeight() / 2);
            paper[centerY][centerX] = node.getValue();
        } else if (node.getHeight() > 1 && node.getWidth() > 1) {
            paper[startY + 1][startX + 1] = node.getValue();
        }
    }



    // function to get the height and the width of the whole rectangle and store
    // them in the root
    public void calculateDimensions(Node node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null) {
            calculateDimensions(node.getLeft());
        }

        if (node.getRight() != null) {
            calculateDimensions(node.getRight());
        }
        if (node.getValue() == '|') {
            node.setHorizontal(false);
            node.setWidth((node.getLeft() != null ? node.getLeft().getWidth() : 0)
                    + (node.getRight() != null ? node.getRight().getWidth() : 0));
            node.setHeight(Math.max(node.getLeft() != null ? node.getLeft().getHeight() : 0,
                    node.getRight() != null ? node.getRight().getHeight() : 0));
            System.out.println(node.getValue() + " [" + node.getWidth() + ',' + node.getHeight() + ']');
        } else if (node.getValue() == '-') {
            node.setHorizontal(true);
            node.setWidth(Math.max(node.getLeft() != null ? node.getLeft().getWidth() : 0,
                    node.getRight() != null ? node.getRight().getWidth() : 0));
            node.setHeight((node.getLeft() != null ? node.getLeft().getHeight() : 0)
                    + (node.getRight() != null ? node.getRight().getHeight() : 0));
            System.out.println(node.getValue() + " [" + node.getWidth() + ',' + node.getHeight() + ']');
        }
    }

    // function to Rotate the rectangle
    public char[][] transposeMatrix(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] rotated = new char[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (matrix[r][c] == '-') {
                    rotated[c][rows - 1 - r] = '|';
                } else if (matrix[r][c] == '|') {
                    rotated[c][rows - 1 - r] = '-';
                } else {
                    rotated[c][rows - 1 - r] = matrix[r][c];
                }
            }
        }
        return rotated;
    }

    // function to Read a rectangle from a file
    public char[][] read2DArrayFromFile(String filePath) throws IOException {
        List<char[]> lines = new ArrayList<>();
        int maxWidth = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                maxWidth = Math.max(maxWidth, line.length());
                lines.add(line.toCharArray());
            }
        }
        int height = lines.size();
        char[][] canvas = new char[height][maxWidth];
        for (int i = 0; i < height; i++) {
            char[] lineChars = lines.get(i);
            System.arraycopy(lineChars, 0, canvas[i], 0, lineChars.length);
            for (int j = lineChars.length; j < maxWidth; j++) {
                canvas[i][j] = ' ';
            }
        }
        return canvas;
    }

    public ArrayList<Node> convertToPaper() {
        ArrayList<Node> pieces = new ArrayList<>();
        if (!isComplete(this.root))
            return null;
        StringBuilder result = new StringBuilder();
        inorderRec(this.root, result, true);
        if (canFormRectangle(result.toString())) {
            convertToPaper(root, pieces);
        }
        return pieces;
    }

    private void convertToPaper(Node root, ArrayList<Node> pieces) {
        if (root.getLeft() == null) {
            return;
        }
        root.getLeft().setX(root.getX());
        root.getLeft().setY(root.getY());
        root.getRight().setX(root.getX());
        root.getRight().setY(root.getY());
        if (root.getLeft().getValue() != '|' && root.getLeft().getValue() != '-') {
            pieces.add(root.getLeft());
        } else {
            convertToPaper(root.getLeft(), pieces);// 12
        }
        if (root.getValue() == '|') {
            root.getRight().setX(root.getRight().getX() + root.getLeft().getWidth());// if (root.data.equals("|"))
            // {root.right.x+=root.left.width;
        } else {
            root.getRight().setY(root.getRight().getY() + root.getLeft().getHeight());
        }
        if (root.getRight().getValue() != '|' && root.getRight().getValue() != '-') {
            pieces.add(root.getRight());
        } else {
            convertToPaper(root.getRight(), pieces);// 3
        }
        if (root.getValue() == '|') {
            root.setWidth(root.getLeft().getWidth() + root.getRight().getWidth());
            root.setHeight(root.getLeft().getHeight());
        } else {
            root.setHeight(root.getLeft().getHeight() + root.getRight().getHeight());
            root.setWidth(root.getLeft().getWidth());
        }
    }

    //calculate the maximum level in the tree:
    public void calcMaxLevel(int curLevel, Node curNode) {
        maxLevel = Math.max(curLevel, maxLevel);
        if (curNode.getValue() == '|' || curNode.getValue() == '-') {
            calcMaxLevel(curLevel + 1, curNode.getLeft());
            calcMaxLevel(curLevel + 1, curNode.getRight());
        }
    }

    //function that I give it the array and the filePath which I want to write the array on it
    public void print2DArrayToFile(char[][] array, String filePath) {
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
    public Node buildTree(char[][] rectangle, int startX, int startY, int endX, int endY) {
        if (startX > endX || startY > endY) return null;

        // Search for '-' to split horizontally or '|' to split vertically
        int EX = findRowDivider(rectangle, startX, startY, endX, endY);
        if (EX != -1) {
            Node node = new Node('-');
            node.setLeft(buildTree(rectangle, startX, startY, EX - 1, endY));
            node.setRight(buildTree(rectangle, EX + 1, startY, endX, endY));
            return node;
        }
        int EY = findColDivider(rectangle, startX, startY, endX, endY);
        if (EY != -1) {
            Node node = new Node('|');
            node.setLeft(buildTree(rectangle, startX, startY, endX, EY - 1));
            node.setRight(buildTree(rectangle, startX, EY + 1, endX, endY));
            return node;
        }
        for (int r = startX; r <= endX; r++) {
            for (int c = startY; c <= endY; c++) {
                if (Character.isLetter(rectangle[r][c])) {
                    int width = endY - startY + 2;
                    int height = endX - startX + 2;
                    return new Node(rectangle[r][c], width, height);
                }
            }
        }
        return new Node(rectangle[startX][startY]);
    }

    public int findRowDivider(char[][] rectangle, int startX, int startY, int endX, int endY) {
        for (int row = startX; row <= endX; row++) {
            boolean hasDivider = true;
            for (int col = startY; col <= endY; col++) {
                if (rectangle[row][col] != '-'&&rectangle[row][col]!='|') {
                    hasDivider = false;
                    break;
                }
            }
            if (hasDivider) {
                return row;
            }
        }
        return -1;
    }

    public int findColDivider(char[][] rectangle, int startX, int startY, int endX, int endY) {
        for (int col = startY; col <= endY; col++) {
            boolean hasDivider = true;
            for (int row = startX; row <= endX; row++) {
                if (rectangle[row][col] != '-'&&rectangle[row][col]!='|') {
                    hasDivider = false;
                    break;
                }
            }
            if (hasDivider) {
                return col;
            }
        }
        return -1;
    }

}
