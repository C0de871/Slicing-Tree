package Views.Components;

import java.awt.Container;

public interface ViewNode {
    int getMidX();
    int getMidY();
    int getX();
    int getY();
    int getHeight();
    Container getParent();
}
