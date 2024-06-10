package Model;

import java.util.Objects;
import java.util.Stack;

public class RectangleOperations {

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

    public char[][] drawing(Node root) {
        calculateDimensions(root);
        int width = root.getWidth()+1;
        int height = root.getHeight()+1;
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
}
