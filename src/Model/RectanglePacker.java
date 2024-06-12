package Model;

import java.util.*;

public class RectanglePacker {
    private List<Node> roots;

    public RectanglePacker() {
        roots = new ArrayList<>();
    }

    public List<Node> getRoots() {
        return roots;
    }

    public boolean formingRectangles(List<Node> papers) {
        if (papers.size() == 1) {
            Node root = papers.get(0);
            roots.add(root);
            return true;
        }
        Set<Node> seenPapers = new HashSet<>(papers);

        for (int i = 0; i < papers.size(); i++) {
            for (int j = i + 1; j < papers.size(); j++) {
                Node paper1 = papers.get(i);
                Node paper2 = papers.get(j);

                if (paper1.getHeight().equals(paper2.getHeight())) {
                    Node combined = new Node('|', paper1.getWidth() + paper2.getWidth(), paper1.getHeight());
                    combined.setLeft(paper1);
                    combined.setRight(paper2);
                    combined.setHorizontal(false);
                    tryFormingRectangle(papers, seenPapers, i, j, combined);

                }
                if (paper1.getWidth().equals(paper2.getWidth())) {
                    Node combined = new Node('-', paper1.getWidth(), paper1.getHeight() + paper2.getHeight());
                    combined.setLeft(paper1);
                    combined.setRight(paper2);
                    combined.setHorizontal(true);
                    if (tryFormingRectangle(papers, seenPapers, i, j, combined)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean tryFormingRectangle(List<Node> papers, Set<Node> seenPapers, int index1, int index2, Node combined) {
        List<Node> newPapersList = new ArrayList<>();
        newPapersList.add(combined);
        for (int k = 0; k < papers.size(); k++) {
            if (k != index1 && k != index2) {
                newPapersList.add(papers.get(k));
            }
        }
        if (!seenPapers.contains(combined)) {
            seenPapers.add(combined);
            return formingRectangles(newPapersList);
        }
        return false;
    }
    public Set<Node> getUniqueTrees() {
        return new HashSet<>(roots);
    }
}