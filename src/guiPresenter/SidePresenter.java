package guiPresenter;

import GUIInterface.SideInterface;
import guiView.SidePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePresenter implements SideInterface.SidePresenterInterface {
    public SidePresenter(SidePanel sideLinkPanel) {
        sidePanel = sideLinkPanel;
        sideFileTreeListener = new SideFileTreeListener();
        sideTextListListener = new SideTextListListener();
        sideButtonListener = new SideButtonListener();
        sidePanel.setEventListner(sideFileTreeListener, sideTextListListener, sideButtonListener);
    }

    @Override
    public void treeNodeClicked(TreeNode node) {

    }

    @Override
    public void listElementClicked() {

    }

    @Override
    public void makingFileButtonClicked() {

    }

    @Override
    public void deleteFileButtonClicked() {

    }

    public class SideButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            switch (button.getText()) {
                case "": break;
                default: break;
            }
        }
    }

    public class SideFileTreeListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            JTree node = (JTree) e.getSource();

        }

    }

    public class SideTextListListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }

    private SidePanel sidePanel;
    private SideTextListListener sideTextListListener;
    private SideFileTreeListener sideFileTreeListener;
    private SideButtonListener sideButtonListener;
}