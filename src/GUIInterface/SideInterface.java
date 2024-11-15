package GUIInterface;

import javax.swing.tree.TreeNode;

public interface SideInterface {

    interface SideViewInterface {
        void showFileTree();

        void fileSearch();

        void showTextList(String[] fileName);
    }

    interface SidePresenterInterface {

        void treeNodeClicked(TreeNode node);

        void listElementClicked();

        void makingFileButtonClicked();

        void deleteFileButtonClicked();
    }
}
