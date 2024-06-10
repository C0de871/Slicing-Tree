package Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Model.BinaryTreeModel;
import Model.Paper;
import Views.Components.StaticMethods;
import Views.pages.LeavesCheckView;
import javaswingdev.Notification.Type;

public class LeavesCheckController {

    // declare components:
    @SuppressWarnings("unused")
    private BinaryTreeModel model;
    LeavesCheckView leavesCheckView;
    JFrame frame;

    // constructor:
    public LeavesCheckController(BinaryTreeModel model, LeavesCheckView leavesCheckView, JFrame frame) {
        this.frame = frame;
        this.model = model;
        this.leavesCheckView = leavesCheckView;
        leavesCheckView.addBackButtonActionListener(e -> backToMainMenu());
        leavesCheckView.addEnterLeavesButtonListener(e -> add());

    }

    private void add() {
        ArrayList<Paper> papers = new ArrayList<>();
        JDialog enterInfoDialog = leavesCheckView.createEnterInfoDialog(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object[] data = (Object[]) e.getSource();
                        String name = (String) data[0];
                        String widthText = (String) data[1];
                        String heightText = (String) data[2];
                        if (!name.isEmpty() && !widthText.isEmpty() && !heightText.isEmpty()) {
                            try {
                                double width = Double.parseDouble(widthText);
                                double height = Double.parseDouble(heightText);
                                papers.add(new Paper(name, (int) width, (int) height));
                            } catch (NumberFormatException ex) {
                                StaticMethods.showMassage("Please enter valid numbers for width and height.", frame,
                                        Type.WARNING);
                            }
                        } else {
                            StaticMethods.showMassage("Please fill in all field.", frame, Type.WARNING);
                        }
                    }
                },
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (papers.size() != 0) {
                            boolean response = model.CanFormRec(papers);
                            if (response) {
                                StaticMethods.showMassage("Can form Rectangle yeah :)", frame, Type.SUCCESS);
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

    private void backToMainMenu() {
        ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "MainMenu");
    }
}
