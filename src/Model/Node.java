package Model;

import java.util.Objects;

public class Node {
    private char value;
    private Integer width, height,x,y;
    private Node left, right,Parent;

    public Node getParent() {
        return Parent;
    }

    public void setParent(Node parent) {
        Parent = parent;
    }

    private boolean isHorizontal;

    public Node(char value) {
        this(value, 0, 0);
    }

    Node(char value, Integer width, Integer height) {
        this.value = value;
        this.height = height;
        this.width = width;
        this.x=0;
        this.y=0;
        left = right = null;
        isHorizontal=false;
    }

    Node(char value, int width, int height, boolean horizontal) {
        this.value = value;
        this.width = width;
        this.height = height;
        this.isHorizontal = horizontal;
        this.left = null;
        this.right = null;
    }
    public Node(char value, boolean isHorizontal, int width, int height) {
        this.value = value;
        this.isHorizontal = isHorizontal;
        this.width = width;
        this.height = height;
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

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isHorizontal == node.isHorizontal &&
                Objects.equals(value, node.value) &&
                Objects.equals(width, node.width) &&
                Objects.equals(height, node.height) &&
                Objects.equals(left, node.left) &&
                Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, width, height, left, right, isHorizontal);
    }
}
