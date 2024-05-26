package Model;

import View.binaryTreeView;

import java.util.Objects;
import java.util.Stack;

import static View.binaryTreeView.*;

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

    public boolean insert(String path, char value, Integer x, Integer y) {
        if (root == null) {
            System.out.println("The tree is empty. Set a root node first.");
            return false;
        } else {
            return insertRec(root, path, 0, value, x, y);
        }
    }

    private boolean insertRec(Node current, String path, int index, char value, Integer x, Integer y) {
        if (index == path.length()) {
            return false;
        }
        if (Character.isLetter(current.value)  ) {
            System.out.println("Cannot add children to a leaf node with value: " + current.value);
            return false;
        }

        if (index == path.length() - 1) {
            if (path.charAt(index) == 'L') {
                if (current.left == null) {
                    current.left = new Node(value, x, y);
                    return true;
                } else {
                    System.out.println("Left child already exists at path: " + path);
                    return false;
                }
            } else if (path.charAt(index) == 'R') {
                if (current.right == null) {
                    current.right = new Node(value, x, y);
                    return true;
                } else {
                    System.out.println("Right child already exists at path: " + path);
                    return false;
                }
            }
        }

        if (path.charAt(index) == 'L') {
            return insertRec(current.left, path, index + 1, value, x, y);
        } else if (path.charAt(index) == 'R') {
            return insertRec(current.right, path, index + 1, value, x, y);
        }
        return false;
    }

    public boolean canEnterMoreNode(Node node) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            return !(Character.isLetter(node.value) && node.value != '-' && node.value != '|');
        }

        if (node.value == '-' || node.value == '|') {
            return node.left == null || node.right == null || canEnterMoreNode(node.left) || canEnterMoreNode(node.right);
        }

        return canEnterMoreNode(node.left) || canEnterMoreNode(node.right);
    }

    public void inorder(StringBuilder result) {
        inorderRec(root, result, true);
    }

    private void inorderRec(Node root, StringBuilder result, boolean isRoot) {
        if (root != null) {
            boolean needParentheses = root.left != null || root.right != null;
            if (needParentheses && isRoot) {
                result.append("(");
            }
            if (needParentheses && !isRoot) {
                result.append("(");
            }
            inorderRec(root.left, result, false);
            result.append(root.value);
            if (root.width != null && root.height != null) {
                result.append("[").append(root.width).append(",").append(root.height).append("]");
            }
            inorderRec(root.right, result, false);

            if (needParentheses && !isRoot) {
                result.append(")");
            }

            if (needParentheses && isRoot) {
                result.append(")");
            }
        }
    }

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
                operatorNode.left = left;
                operatorNode.right = right;
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
        } else {
            throw new IllegalStateException("Parsing error, no root node found.");
        }
    }
    public boolean canFormRectangle(String input) {
        Stack<Node> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                continue;
            } else if (c == ')') {
                Node right = stack.pop();
                Node operator = stack.pop();
                Node left = stack.pop();

                if (operator.value == '|') {
                    if (Objects.equals(left.height, right.height)) {
                        Node newNode = new Node(operator.value, left.width + right.width, left.height);
                        stack.push(newNode);
                    } else {
                        return false;
                    }
                } else if (operator.value == '-') {
                    if (Objects.equals(left.width, right.width)) {
                        Node newNode = new Node(operator.value, left.width, left.height + right.height);
                        stack.push(newNode);
                    } else {
                        return false;
                    }
                }
            } else if (Character.isLetter(c)) {
                // Start of a leaf node
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

    public char[][] drawing(Node root) {
        calculateDimensions(root);

        int width = root.width + 2;
        int height = root.height + 2;

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

        drawNode(root, canvas, 1, 1, root.width, root.height);

        return canvas;
    }

    private void drawNode(Node node, char[][] canvas, int startX, int startY, int width, int height) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            int nameStartX = startX + width / 2;
            int nameStartY = startY + height / 2;
            if (nameStartX < canvas[0].length && nameStartY < canvas.length) {
                canvas[nameStartY][nameStartX] = node.value;
            }
        } else {
            if (node.isHorizontal) {
                int mid = startX + width / 2;
                for (int i = startY; i < startY + height; i++) {
                    if (i < canvas.length && mid < canvas[0].length) {
                        canvas[i][mid] = '|';
                    }
                }
                drawNode(node.left, canvas, startX, startY, mid - startX, height);
                drawNode(node.right, canvas, mid + 1, startY, startX + width - mid - 1, height);
            } else {
                int mid = startY + height / 2;
                for (int i = startX; i < startX + width; i++) {
                    if (mid < canvas.length && i < canvas[0].length) {
                        canvas[mid][i] = '-';
                    }
                }
                drawNode(node.left, canvas, startX, startY, width, mid - startY);
                drawNode(node.right, canvas, startX, mid + 1, width, startY + height - mid - 1);
            }
        }
    }



    public void calculateDimensions(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            calculateDimensions(node.left);
        }

        if (node.right != null) {
            calculateDimensions(node.right);
        }

        if (node.value == '|') {
            node.isHorizontal = true;
            node.width = (node.left != null ? node.left.width : 0) + (node.right != null ? node.right.width : 0) + 1;
            node.height = Math.max(node.left != null ? node.left.height : 0, node.right != null ? node.right.height : 0);
        } else if (node.value == '-') {
            node.isHorizontal = false;
            node.width = Math.max(node.left != null ? node.left.width : 0, node.right != null ? node.right.width : 0);
            node.height = (node.left != null ? node.left.height : 0) + (node.right != null ? node.right.height : 0) + 1;
        }
    }
    public void insertNode(){
        while ( canEnterMoreNode(root)) {
            String path =  promptPath();
            char value = promptValue();
            Integer width = 0, height = 0;
            if (value != '|' && value != '-') {
                width = promptX();
                height =  promptY();
            }
            if ( insert(path, value, width, height)) {
                printM("Node inserted successfully.");
            } else {
                printM("Failed to insert node.");
            }
        }
    }
}