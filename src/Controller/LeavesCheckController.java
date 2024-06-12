package Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Model.Node;
import Views.Components.StaticMethods;
import Views.pages.LeavesCheckView;
import javaswingdev.Notification.Type;

public class LeavesCheckController {

    // declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    LeavesCheckView leavesCheckView;
    JFrame frame;
    ArrayList<Node> response = new ArrayList<>();
    int curRecInd = 0;

    // constructor:
    public LeavesCheckController(BinaryTreeModel model, LeavesCheckView leavesCheckView, JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.leavesCheckView = leavesCheckView;
        leavesCheckView.addBackButtonActionListener(e -> backToMainMenu());
        leavesCheckView.addEnterLeavesButtonListener(e -> add());
        leavesCheckView.addGoLeftActionListener(e -> goLeft());
        leavesCheckView.addGoRightButtonActionListener(e -> goRight());
    }

    // go to the previous rectangle:
    private void goLeft() {
        if (curRecInd == 0) {
            System.out.println("done");
            return;
        }
        curRecInd--;
        ArrayList<Node> oneRectangle = model.checkViewConvertToPaper(response.get(curRecInd));
        StaticMethods.addRectangles(oneRectangle, response.get(curRecInd), leavesCheckView);
        int newX = (1500 / 2) - (response.get(curRecInd).getWidth() / 2) - 300;
        int newY = (800 / 2) - 35;
        leavesCheckView.setBoundsGoLeft(newX, newY);
        newX = (1500 / 2) + (response.get(curRecInd).getWidth() / 2) + 230;
        newY = (800 / 2) - 35;
        leavesCheckView.setBoundsGoRight(newX, newY);
    }

    // go to the next rectangle:
    private void goRight() {
        if (curRecInd == response.size() - 1) {
            System.out.println("done");
            return;
        }
        curRecInd++;
        ArrayList<Node> oneRectangle = model.checkViewConvertToPaper(response.get(curRecInd));
        StaticMethods.addRectangles(oneRectangle, response.get(curRecInd), leavesCheckView);
        int newX = (1500 / 2) - (response.get(curRecInd).getWidth() / 2) - 300;
        int newY = (800 / 2) - 35;
        leavesCheckView.setBoundsGoLeft(newX, newY);
        newX = (1500 / 2) + (response.get(curRecInd).getWidth() / 2) + 230;
        newY = (800 / 2) - 35;
        leavesCheckView.setBoundsGoRight(newX, newY);
    }

    // add nodes:
    private void add() {
        ArrayList<Node> papers = new ArrayList<>();
        JDialog enterInfoDialog = leavesCheckView.createEnterInfoDialog(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] data = (Object[]) e.getSource();
                        String name = (String) data[0];
                        String widthText = (String) data[1];
                        String heightText = (String) data[2];
                        if (name.matches("[A-Z]") && !widthText.isEmpty() && !heightText.isEmpty()) {
                            try {
                                double width = Double.parseDouble(widthText);
                                double height = Double.parseDouble(heightText);
                                papers.add(new Node(name.charAt(0), (int) width, (int) height));
                            } catch (NumberFormatException ex) {
                                StaticMethods.showMassage("Please enter valid numbers for width and height.", frame,
                                        Type.WARNING);
                            }
                        } else {
                            StaticMethods.showMassage("Please Enter Valid input", frame, Type.WARNING);
                        }
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (papers.size() != 0) {
                            response = model.FormRectangles(papers);
                            if (response.size() != 0) {
                                StaticMethods.showMassage("Can form Rectangle yeah :)", frame, Type.SUCCESS);
                                leavesCheckView.setGoButtonsVisiblity(true);
                                curRecInd = -1;
                                goRight();
                                return;
                            }
                            StaticMethods.showMassage("Can not form Rectangle oh no :<", frame, Type.WARNING);

                        } else {
                            StaticMethods.showMassage("Please Enter papers next time", frame, Type.WARNING);
                        }
                    }
                });

        enterInfoDialog.setVisible(true);
    }

    // back to main menu
    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }
}
