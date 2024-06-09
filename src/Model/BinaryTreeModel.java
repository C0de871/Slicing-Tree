package Model;


import java.io.IOException;
import java.util.*;

public class BinaryTreeModel {
    private Node root;
    private int maxLevel = 0;
    private String path;

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
        if (node == null)
            return false;
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
    private StringBuilder fromRecToText(String path)  {
        drawTreeFromRec();
        StringBuilder Text=new StringBuilder();
        inorderRec(this.root,Text,true);
        return Text;
    }

    public void rotateTree() {
        if (root != null) {
            transposeNode(root);
        }
    }

    private void transposeNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.getValue() == '|') {
            node.setValue('-');
        } else if (node.getValue() == '-') {
            node.setValue('|');
        }
        int temp = node.getWidth();
        node.setWidth(node.getHeight());
        node.setHeight(temp);

        transposeNode(node.getLeft());
        transposeNode(node.getRight());
    }

    // function to Rotate the rectangle
    /*
     * public char[][] transposeMatrix(char[][] matrix) {
     * int rows = matrix.length;
     * int cols = matrix[0].length;
     * char[][] rotated = new char[cols][rows];
     *
     * for (int r = 0; r < rows; r++) {
     * for (int c = 0; c < cols; c++) {
     *
     * if (matrix[r][c] == '-') {
     * rotated[c][rows - 1 - r] = '|';
     * } else if (matrix[r][c] == '|') {
     * rotated[c][rows - 1 - r] = '-';
     * } else {
     * rotated[c][rows - 1 - r] = matrix[r][c];
     * }
     * }
     * }
     * return rotated;
     * }
     */

    public ArrayList<Node> convertToPaper(String path) {
        ArrayList<Node> pieces = new ArrayList<>();
        if (!isComplete(this.root))
            return null;
        StringBuilder result = new StringBuilder();
        inorderRec(this.root, result, true);
        RectangleOperations R = new RectangleOperations();
        if (R.canFormRectangle(result.toString())) {
            convertToPaper(root, pieces);
        }
        char[][] rec = R.drawing(getRoot());
        FileOperations F = new FileOperations();
        F.print2DArrayToFile(rec, path);
        this.path = path;
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

    // calculate the maximum level in the tree:
    public void calcMaxLevel(int curLevel, Node curNode) {
        maxLevel = Math.max(curLevel, maxLevel);
        if (curNode.getValue() == '|' || curNode.getValue() == '-') {
            calcMaxLevel(curLevel + 1, curNode.getLeft());
            calcMaxLevel(curLevel + 1, curNode.getRight());
        }
    }

    public Node drawTreeFromRec() {
        FileOperations file = new FileOperations();
        char[][] rec;
        try {
            rec = file.read2DArrayFromFile(path);
            return buildTree(rec, 1, 1, rec.length - 2, rec[0].length - 2);
        } catch (IOException e) {
            return null;
        }
    }

    public Node buildTree(char[][] rectangle, int startX, int startY, int endX, int endY) {
        if (startX > endX || startY > endY)
            return null;
        int EX = findDivider(rectangle, startX, startY, endX, endY, true);
        if (EX != -1) {
            Node node = new Node('-');
            node.setLeft(buildTree(rectangle, startX, startY, EX - 1, endY));
            node.setRight(buildTree(rectangle, EX + 1, startY, endX, endY));
            return node;
        }
        int EY = findDivider(rectangle, startX, startY, endX, endY, false);
        if (EY != -1) {
            Node node = new Node('|');
            node.setLeft(buildTree(rectangle, startX, startY, endX, EY - 1));
            node.setRight(buildTree(rectangle, startX, EY + 1, endX, endY));
            return node;
        }
        for (int r = startX; r <= endX; r++) {
            for (int c = startY; c <= endY; c++) {
                if (Character.isLetter(rectangle[r][c])) {
                    int width = endY - startY + 1;
                    int height = endX - startX + 1;
                    return new Node(rectangle[r][c], width, height);
                }
            }
        }
        return new Node(rectangle[startX][startY]);
    }

    private int findDivider(char[][] rectangle, int startX, int startY, int endX, int endY, boolean isRow) {
        for (int i = (isRow ? startX : startY); i <= (isRow ? endX : endY); i++) {
            boolean hasDivider = true;
            for (int j = (isRow ? startY : startX); j <= (isRow ? endY : endX); j++) {
                char current = isRow ? rectangle[i][j] : rectangle[j][i];
                if (current != '-' && current != '|') {
                    hasDivider = false;
                    break;
                }
            }
            if (hasDivider) {
                return i;
            }
        }
        return -1;
    }
}