package Model;

public class Node {
    char value;
    Integer x, y, width, height;
    Node left, right;

    public Node(char value) {
        this(value, null, null);
    }

    Node(char value, Integer width, Integer height) {
        this.value = value;
        this.height = height;
        this.width = width;
        left = right = null;
    }
    boolean isLeaf() {
        return left == null && right == null;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
