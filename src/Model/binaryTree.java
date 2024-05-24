package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class binaryTree {
    private Node root;

    public binaryTree() {
        root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean insert(String path, char value) {
        return insert(path, value, null, null);
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
        if (Character.isLetter(current.value) && current.value != '-' && current.value != '|') {
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
            if (current.left == null) {
                current.left = new Node('-');
            }
            return insertRec(current.left, path, index + 1, value, x, y);
        } else if (path.charAt(index) == 'R') {
            if (current.right == null) {
                current.right = new Node('-');
            }
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


    public void buildSpecificTree() {
        Node root = new Node('-');
        setRoot(root);

        insert("L", '|');
        insert("R", '|');

        insert("LL", 'A', 20, 10);
        insert("LR", '|');

        insert("LRL", 'B', 20, 10);
        insert("LRR", 'C', 30, 10);

        insert("RL", 'D', 30, 50);
        insert("RR", '-');

        insert("RRL", 'E', 40, 30);
        insert("RRR", 'F', 40, 20);
    }

    public ArrayList<Node> convertToPaper() {
        ArrayList<Node> pieces = new ArrayList<>();
        convertToPaper(root, pieces);
        for (Node p : pieces) {
            System.out.println(p.value + " " + p.width + " " + p.height + " " + p.x + " " + p.y);
        }
        return pieces;
    }

    public void export(String s) {
        Stack<Character> brackets = new Stack<>();
        Stack<Node> nodes = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            } else if (s.charAt(i) == '(') {
                brackets.add('(');
            } else if (s.charAt(i) == ')') {
                brackets.pop();
                Node right = nodes.pop();
                Node root = nodes.pop();
                root.left = nodes.pop();
                root.right = right;
                nodes.add(root);

            } else if (s.charAt(i) == '|' || s.charAt(i) == '–') {
                nodes.push(new Node(s.charAt(i)));
            } else {
                char data = s.charAt(i);
                StringBuilder sub = new StringBuilder();
                i += 2;
                while (s.charAt(i) != ',') {
                    sub.append(s.charAt(i++));
                }
                i++;
                int width = Integer.parseInt(sub.toString());
                sub = new StringBuilder();
                while (s.charAt(i) != ']') {
                    sub.append(s.charAt(i++));
                }
                int height = Integer.parseInt(sub.toString());
                nodes.add(new Node(data, width, height));
            }
        }
        Node right = nodes.pop();
        Node root = nodes.pop();
        root.left = nodes.pop();
        root.right = right;
        setRoot(root);
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
                        // Assign width and height to the new node
                        Node newNode = new Node(operator.value, left.width + right.width, left.height);
                        stack.push(newNode);
                    } else {
                        return false;
                    }
                } else if (operator.value == '-') {
                    // Check condition 2 for '-'
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


    private void convertToPaper(Node root, ArrayList<Node> pieces) {
        if (root.left == null) {
            return;
        }
        root.left.x = root.x;
        root.left.y = root.y;
        root.right.x = root.x;
        root.right.y = root.y;
        if (root.left.value != '|' && root.left.value != '-') {
            pieces.add(root.left);
        } else {
            convertToPaper(root.left, pieces);//12
        }
        if (root.value == '|') {
            root.right.x += root.left.width;
        } else {
            root.right.y += root.left.height;
        }
        if (root.right.value != '|' && root.right.value != '-') {
            pieces.add(root.right);
        } else {
            convertToPaper(root.right, pieces);//3
        }
        if (root.value == '|') {
            root.width = root.left.width + root.right.width;
            root.height = root.left.height;
        } else {
            root.height = root.left.height + root.right.height;
            root.width = root.left.width;
        }
    }
}