package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectanglePacker {

   // private static String structuredString = "";

    public static boolean canFormRectangle(List<Paper> papers) {
        List<List<Paper>> permutations = new ArrayList<>();
        generatePermutations(papers, 0, permutations);

        for (List<Paper> perm : permutations) {
           // structuredString = ""; // Reset the structured string for each permutation
            if (checkIfRectangle(perm)) {
              //  structuredString = buildStructuredString(perm); // Build the structured string based on the permutation
                return true;
            }
        }
        return false;
    }

    private static void generatePermutations(List<Paper> papers, int index, List<List<Paper>> permutations) {
        if (index == papers.size() - 1) {
            permutations.add(new ArrayList<>(papers));
            return;
        }
        for (int i = index; i < papers.size(); i++) {
            Collections.swap(papers, i, index);
            generatePermutations(papers, index + 1, permutations);
            Collections.swap(papers, i, index);
        }
    }

    private static boolean checkIfRectangle(List<Paper> papers) {
        int totalArea = 0;
        int maxLength = 0;
        int maxWidth = 0;

        for (Paper paper : papers) {
            totalArea += paper.length * paper.width;
            maxLength = Math.max(maxLength, paper.length);
            maxWidth = Math.max(maxWidth, paper.width);
        }

        int side1 = maxLength;
        int side2 = totalArea / side1;

        if (side1 * side2 != totalArea) {
            return false;
        }

        int currentLength = 0;
        int currentWidth = 0;

        for (Paper paper : papers) {
            if (currentWidth + paper.width <= side2) {
                currentWidth += paper.width;
            } else {
                currentLength += paper.length;
                currentWidth = paper.width;
            }

            if (currentLength > side1 || currentWidth > side2) {
                return false;
            }
        }

        return true;
    }

   /* private static String buildStructuredString(List<Paper> papers) {
        if (papers.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        buildStructuredStringRecursive(papers, 0, papers.size() - 1, result, true);
        return result.toString();
    }

    private static void buildStructuredStringRecursive(List<Paper> papers, int start, int end, StringBuilder result, boolean isVertical) {
        if (start == end) {
            Paper paper = papers.get(start);
            result.append(paper.name).append("[").append(paper.length).append(",").append(paper.width).append("]");
            return;
        }

        result.append("(");
        int mid = (start + end) / 2;
        buildStructuredStringRecursive(papers, start, mid, result, !isVertical);
        result.append(isVertical ? '-' : '|');
        buildStructuredStringRecursive(papers, mid + 1, end, result, !isVertical);
        result.append(")");
    }*/


   /* public static void main(String[] args) {
        List<Paper> papers = new ArrayList<>();
        papers.add(new Paper("A", 20, 10));
        papers.add(new Paper("B", 20, 10));
        papers.add(new Paper("C", 30, 10));
        papers.add(new Paper("D", 30, 50));
        papers.add(new Paper("E", 40, 30));
        papers.add(new Paper("F", 40, 20));
        if (canFormRectangle(papers)) {
            System.out.println("The papers can form a rectangle.");
           // System.out.println("Structured String: " + structuredString);
        } else {
            System.out.println("The papers cannot form a rectangle.");
        }
    }*/
}
