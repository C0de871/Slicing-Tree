package Views.Components;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class AnimationMethods implements ActionListener {

    // Components:
    StretchingLinePanel downFromParent, toTheLeft, toTheRight, downToRighChild, downToLeftChild;
    Container frame;
    boolean animationDone = true; // Flag to track animation status
    ViewNode parentNode;
    ViewNode leftChild;
    ViewNode rightChild;
    
    // constructor:
    public AnimationMethods(ViewNode parentNode, ViewNode leftChild, ViewNode rightChild,
            Container frame) {

        // init components:
        this.parentNode = parentNode;
        this.rightChild = rightChild;
        this.leftChild = leftChild;

        // Vertical edge from parnet node:
        downFromParent = new StretchingLinePanel(
                new Point(parentNode.getMidX(), parentNode.getY() + parentNode.getHeight() - 2),
                new Point(parentNode.getMidX(),
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                2); // of 3

        // Horizantel edge to the left:
        toTheLeft = new StretchingLinePanel(
                new Point(parentNode.getMidX(),
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                new Point(leftChild.getMidX() - 1,
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                2);

        // Horizantel edge to the right:
        toTheRight = new StretchingLinePanel(
                new Point(parentNode.getMidX(),
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                new Point(rightChild.getMidX() + 1,
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                2); // of 3

        // Vertical edge to the right node:
        downToRighChild = new StretchingLinePanel(
                new Point(rightChild.getMidX(),
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                new Point(rightChild.getMidX(), rightChild.getY() + 2), 2);

        // Vertical edge to the left node:
        downToLeftChild = new StretchingLinePanel(
                new Point(leftChild.getMidX(),
                        (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2)),
                new Point(leftChild.getMidX(), leftChild.getY() + 2), 2); // of 3

        // add panel to the container:
        this.frame = frame;
        frame.add(downFromParent);
        frame.add(toTheLeft);
        frame.add(toTheRight);
        frame.add(downToRighChild);
        frame.add(downToLeftChild);
    }

    // Start animation to infinit:
    public void startAnimation() {
        // Start the timer to check and start the animation
        Timer timer = new Timer(10, this);
        timer.setRepeats(true);
        timer.start();
    }

    // animate the edges:
    public void edgeAnimated(AnimationCompleteListener listener) {

        // to check if animation done:
        animationDone = false;

        // first animation:
        Animator animator1 = downFromParent.startConnectAnimation();
        

        // dedict the end of the animation:
        animator1.addTarget(new TimingTargetAdapter() {
            @Override
            public void end() {

                // start the second and third animatin:
                toTheLeft.startConnectAnimation();
                Animator animator2 = toTheRight.startConnectAnimation();

                // dedict the end of the sec and third animations:
                animator2.addTarget(new TimingTargetAdapter() {
                    @Override
                    public void end() {

                        // start the fourth and fifth animations:
                        downToRighChild.startConnectAnimation();
                        Animator animator3 = downToLeftChild.startConnectAnimation();
                        animator3.addTarget(new TimingTargetAdapter() {
                            @Override
                            public void end() {
                                parentNode.getParent().add((Component) leftChild);
                                parentNode.getParent().add((Component) rightChild);
                                parentNode.getParent().repaint();
                                listener.onAnimationComplete();
                                
                                // puase animation for half second:
                                Timer removalTimer = new Timer(500, new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // animatinon is done:
                                        animationDone = true;
                                    }
                                });

                                // timer settings:
                                removalTimer.setRepeats(false); // Ensure the timer only runs once
                                removalTimer.start();
                            }
                        });
                    }
                });
            }
        });
    }

    // reset animation to there first state:
    void resetPanel() {
        downFromParent.setBounds(parentNode.getMidX(), parentNode.getY() + parentNode.getHeight() - 2, 0, 0);
        toTheLeft.setBounds(parentNode.getMidX(),
                (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2), 0, 0);
        toTheRight.setBounds(parentNode.getMidX(),
                (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2), 0, 0);
        downToRighChild.setBounds(rightChild.getMidX(),
                (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2), 0, 0);
        downToLeftChild.setBounds(leftChild.getMidX(),
                (parentNode.getMidY() + (rightChild.getMidY() - parentNode.getMidY()) / 2), 0, 0);
    }

    // action performed for timer:
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the previous animation cycle is complete before starting a new one
        if (animationDone) {
            // reset bound and size of edges to zero:
            resetPanel();
            edgeAnimated(new AnimationCompleteListener() {
                @Override
                public void onAnimationComplete() {
                }
            });
    
        }
    }

    public StretchingLinePanel getDownFromParent() {
        return downFromParent;
    }

    public StretchingLinePanel getToTheLeft() {
        return toTheLeft;
    }

    public StretchingLinePanel getToTheRight() {
        return toTheRight;
    }

    public StretchingLinePanel getDownToRighChild() {
        return downToRighChild;
    }

    public StretchingLinePanel getDownToLeftChild() {
        return downToLeftChild;
    }

    public Container getFrame() {
        return frame;
    }

    public boolean isAnimationDone() {
        return animationDone;
    }

    public ViewNode getParentNode() {
        return parentNode;
    }

    public ViewNode getLeftChild() {
        return leftChild;
    }

    public ViewNode getRightChild() {
        return rightChild;
    }
}

interface AnimationCompleteListener {
    void onAnimationComplete();
}
