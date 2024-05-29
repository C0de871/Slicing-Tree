package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class BinaryTreeModel {
    private Node root;

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
        if (Character.isLetter(node.getValue())) {
            return true;
        } else {
            if (node.getLeft() == null) {
                return false;
            }
            if (node.getRight() == null) {
                return false;
            }
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

    // function to draw a rectangle
    public char[][] drawing(Node root) {
        calculateDimensions(root);
        System.out.println(root.getWidth() + " " + root.getHeight());
        int width = root.getWidth() + 2;
        int height = root.getHeight() + 2;

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
        drawNode(root, canvas, 1, 1, root.getWidth(), root.getHeight());
        return canvas;
    }

    private void drawNode(Node node, char[][] canvas, int startX, int startY, int width, int height) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            int nameStartX = startX + width / 2;
            int nameStartY = startY + height / 2;
            if (nameStartX < canvas[0].length && nameStartY < canvas.length) {
                canvas[nameStartY][nameStartX] = node.getValue();
            }
        } else {
            if (node.isHorizontal()) {
                int mid = startX + width / 2;
                for (int i = startY; i < startY + height; i++) {
                    if (i < canvas.length && mid < canvas[0].length) {
                        canvas[i][mid] = '|';
                    }
                }
                drawNode(node.getLeft(), canvas, startX, startY, mid - startX, height);
                drawNode(node.getRight(), canvas, mid + 1, startY, startX + width - mid - 1, height);
            } else {
                int mid = startY + height / 2;
                for (int i = startX; i < startX + width; i++) {
                    if (mid < canvas.length && i < canvas[0].length) {
                        canvas[mid][i] = '-';
                    }
                }
                drawNode(node.getLeft(), canvas, startX, startY, width, mid - startY);
                drawNode(node.getRight(), canvas, startX, mid + 1, width, startY + height - mid - 1);
            }
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
            node.setHorizontal(true);
            node.setWidth((node.getLeft() != null ? node.getLeft().getWidth() : 0)
                    + (node.getRight() != null ? node.getRight().getWidth() : 0) + 1);
            node.setHeight(Math.max(node.getLeft() != null ? node.getLeft().getHeight() : 0,
                    node.getRight() != null ? node.getRight().getHeight() : 0));
        } else if (node.getValue() == '-') {
            node.setHorizontal(false);
            node.setWidth(Math.max(node.getLeft() != null ? node.getLeft().getWidth() : 0,
                    node.getRight() != null ? node.getRight().getWidth() : 0));
            node.setHeight((node.getLeft() != null ? node.getLeft().getHeight() : 0)
                    + (node.getRight() != null ? node.getRight().getHeight() : 0) + 1);
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

    // function to get the height of the tree
    public int treeHeight(Node root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = treeHeight(root.getLeft());
            int rightHeight = treeHeight(root.getRight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
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

}
