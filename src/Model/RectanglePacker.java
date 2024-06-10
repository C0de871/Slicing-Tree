package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectanglePacker {

    /*  static String buildRectangleString(List<Paper> papers) {
          StringBuilder rectangleBuilder = new StringBuilder();
          buildRectangleStringHelper(papers, rectangleBuilder);
          return rectangleBuilder.toString();
      }

      private static void buildRectangleStringHelper(List<Paper> papers, StringBuilder rectangleBuilder) {
          if (papers.size() == 1) {
              Paper paper = papers.get(0);
              rectangleBuilder.append("(").append(paper.getName()).append("[").append(paper.getHeight()).append(",").append(paper.getWidth()).append("]");
          } else {
              rectangleBuilder.append("(");
              for (int i = 0; i < papers.size(); i++) {
                  Paper paper = papers.get(i);
                  if (i > 0) {
                      if ((i < papers.size() - 1 && papers.get(i).getWidth() == papers.get(i + 1).getWidth()) ||
                              (i < papers.size() - 1 && papers.get(i).getHeight() == papers.get(i + 1).getHeight())) {
                          rectangleBuilder.append("|");
                      } else {
                          rectangleBuilder.append("-");
                      }
                  }
                  rectangleBuilder.append(paper.getName()).append("[").append(paper.getHeight()).append(",").append(paper.getWidth()).append("]") ;
              }
              rectangleBuilder.append(")");
          }
      }  */
    public boolean canFormRectangle(List<Paper> papers) {
        List<List<Paper>> permutations = new ArrayList<>();
        generatePermutations(papers, 0, permutations);

        for (List<Paper> perm : permutations) {
            if (checkIfRectangle(perm)) {
                //System.out.println(buildRectangleString(perm)); // Print the rectangle representation
                return true;
            }
        }
        return false;
    }

    private void generatePermutations(List<Paper> papers, int index, List<List<Paper>> permutations) {
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

    private boolean checkIfRectangle(List<Paper> papers) {
        int totalArea = 0;
        int maxLength = 0;
        int maxWidth = 0;

        for (Paper paper : papers) {
            totalArea += paper.getHeight() * paper.getHeight();
            maxLength = Math.max(maxLength, paper.getHeight());
            maxWidth = Math.max(maxWidth, paper.getWidth());
        }

        int side1 = maxLength;
        int side2 = totalArea / side1;

        if (side1 * side2 != totalArea) {
            return false;
        }

        int currentLength = 0;
        int currentWidth = 0;

        for (Paper paper : papers) {
            if (currentWidth + paper.getWidth() <= side2) {
                currentWidth += paper.getWidth();
            } else {
                currentLength += paper.getHeight();
                currentWidth = paper.getWidth();
            }

            if (currentLength > side1 || currentWidth > side2) {
                return false;
            }
        }

        return true;
    }
/*    public   int maxRectangles(List<Paper> papers) {
        int maxCount = 0;
        int n = papers.size();
        for (int i = 1; i < (1 << n); i++) {
            List<Paper> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(papers.get(j));
                }
            }
            if (canFormRectangle(subset)) {
                maxCount = Math.max(maxCount, subset.size());
            }
        }
        return maxCount;
    }*/


}
