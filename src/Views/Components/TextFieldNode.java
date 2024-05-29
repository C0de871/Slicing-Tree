package Views.Components;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Controller.ColorController;
import Controller.FontController;
import Views.pages.TreeToRectangleView;
import textfield.TextField;

public class TextFieldNode extends TextField {

    // init components:
    TextFieldNode leftNode = null;
    TextFieldNode rightNode = null;
    AnimationMethods animatedEdgePanel;
    int branchValue;
    int startX;
    int endX;

    // constructor:
    public TextFieldNode(int x, int y, int width, int height, int branchValue) {

        //suer constructor:
        super(x, y, width, height);

        //text Decoration:
        this.setRound(20);
        this.setDisabledTextColor(ColorController.selectButtonColor);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setFont(FontController.orderLabelFont);
        this.setText("Null");

        //init important valuse:
        this.branchValue = branchValue;
        this.startX = getX();
        this.endX = getX()+getWidth();

        // Add an ActionListener to the JTextField
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textProcessing(e);
            }         
        });
    }

    //add new children:
    private void addNewChildren(TextFieldNode curTextFieldNode, Container curContainer) {
        leftNode = createLeftNode(curTextFieldNode.getX()+(curTextFieldNode.getWidth()/2), curTextFieldNode.getMidY(), curTextFieldNode.branchValue);
        rightNode = createRightNode(curTextFieldNode.getX()+(curTextFieldNode.getWidth()/2), curTextFieldNode.getMidY(), curTextFieldNode.branchValue);
        animatedEdgePanel = new AnimationMethods(curTextFieldNode, leftNode, rightNode, curContainer);
        animatedEdgePanel.edgeAnimated(new AnimationCompleteListener() {
            @Override
            public void onAnimationComplete() {
                System.out.println("Done");
            }
        });
    }

    //check if the tree need adjustment:
    private void checkForAdjust(TextFieldNode curTextFieldNode, Container curContainer) {
        curTextFieldNode.setEnabled(false);
        if (curTextFieldNode.branchValue == 12) {
            getAnimator( curTextFieldNode, curContainer);
        }else{
            addNewChildren(curTextFieldNode, curContainer);
        }
    }   

    //process the text and make adjust depend on the situation:
    private void textProcessing(ActionEvent e) {
        TextFieldNode curTextFieldNode = (TextFieldNode) e.getSource();
        Container curContainer = curTextFieldNode.getParent();
        String text = curTextFieldNode.getText();                // check the text:
        if (text.equals("-") || text.equals("|")) {
            checkForAdjust(curTextFieldNode, curContainer);
        } else if (text.matches("[A-Z]")) {
            curTextFieldNode.setEnabled(false);
        } else {
            curTextFieldNode.setText("");
        }
    }

    //adjust and add new children:
    private void getAnimator(TextFieldNode curTextFieldNode, Container curContainer) {
        TreeToRectangleView.textRoot.autoAdjust(0);
        Timer t= new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewChildren( curTextFieldNode, curContainer);
            }
            
        });
        t.setRepeats(false);
        t.start();
    }

    // create left child:
    public TextFieldNode createLeftNode(int parentX, int parentY, int branchValue) {
        int x = parentX - branchValue, y = parentY + 100;
        return new TextFieldNode(x, y, this.getWidth(), this.getHeight(), branchValue / 2);
    }

    // create right child:
    public TextFieldNode createRightNode(int parentX, int parentY, int branchValue) {
        int x = parentX + branchValue, y = parentY + 100;
        return new TextFieldNode(x, y, this.getWidth(), this.getHeight(), branchValue / 2);
    }

    //check nodes:
    public void visit(){
        System.out.println(branchValue);
        if(leftNode!=null){
            leftNode.visit();
        }
        if(rightNode!=null){
            rightNode.visit();
        }
    }

    //auto adjust the edges and nodes position: 
    public Animator autoAdjust(int shiftValue) {
        Animator animator =startTranslatingAnimation(shiftValue);

        if(animatedEdgePanel==null){
            this.setBranchValue(this.branchValue*2);
            return animator;
        }

        animatedEdgePanel.getDownFromParent().startTranslatingAnimation(shiftValue);
        
        //left horizantal edge:
        Animator animator2 =animatedEdgePanel.getToTheLeft().startStretchingAnimation(branchValue, true);
        animator2.addTarget(new TimingTargetAdapter(){
            @Override
            public void end(){
                animatedEdgePanel.getToTheLeft().startTranslatingAnimation(shiftValue);
            }
        });

        //right horizantal edge:
        Animator animator4 =animatedEdgePanel.getToTheRight().startStretchingAnimation(branchValue, false);
        animator4.addTarget(new TimingTargetAdapter(){
            @Override
            public void end(){
                animatedEdgePanel.getToTheRight().startTranslatingAnimation(shiftValue);
            }
        });

        //left vertical edge:
        animatedEdgePanel.getDownToLeftChild().startTranslatingAnimation(shiftValue-branchValue);

        //right vertical edge:
        animatedEdgePanel.getDownToRighChild().startTranslatingAnimation(shiftValue+branchValue);

        //call left child:
        if(leftNode!=null){
            leftNode.autoAdjust(shiftValue-branchValue);
        }
        if(rightNode!=null){
            rightNode.autoAdjust(shiftValue+branchValue);
        }
        this.setBranchValue(this.branchValue*2);
        return animator;
    }

    // animation to translate nodes:
    public Animator startTranslatingAnimation(int distance) {
        Animator animator = new Animator(300);
        int oldStartX = startX;
        int oldEndX = endX;
        animator.addTarget(new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                // Translate both start and end points of the line
                startX = oldStartX + (int) (distance * fraction);
                endX = oldEndX + (int) (distance * fraction);

                // Update bounds to accommodate new position
                int wid = Math.abs(endX - startX);
                int len = getHeight();
                int xx = Math.min(startX, endX);
                int yy = getY();
                if (wid == 0) {
                    wid = 6;
                    xx -= 3;
                } else if (len == 0) {
                    len = 6;
                    yy -= 3;
                }
                setBounds(xx, yy, wid, len);

                // Repaint the component to reflect the new positions
                repaint();
            }
        });
        animator.start();
        return animator;
    }

    //setter for branch Value:
    public void setBranchValue(int branchValue) {
        this.branchValue = branchValue;
    }
}
