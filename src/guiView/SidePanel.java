package guiView;

import GUIInterface.SideInterface;
import guiPresenter.SidePresenter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class SidePanel extends JPanel implements SideInterface.SideViewInterface {
    private SidePresenter sidePresenter;

    private FileView fileView;
    private TextListView textListView;

    private JPanel buttonPanel;
    private ArrayList<JButton> buttonArrayList;

    private TreeSelectionListener fileTreeListener;
    private ListSelectionListener textListListener;
    private ActionListener sideButtonListener;

    public SidePanel() {
        sidePresenter = new SidePresenter(this);
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout());

        buttonArrayList = new ArrayList<>();
        buttonArrayList.add(new JButton("Make"));
        buttonArrayList.add(new JButton("delete"));

        for(JButton button : buttonArrayList) {
            buttonPanel.add(button);
            button.addActionListener(sideButtonListener);
        }

        // FileView와 TextListView 초기화
        fileView = new FileView();
        textListView = new TextListView();

        // JSplitPane으로 FileView와 TextListView를 분할
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileView, textListView);
        splitPane.setDividerLocation(300); // 초기 분할 위치 설정
        splitPane.setContinuousLayout(true); // 크기 조절 중 실시간 업데이트
        splitPane.setOneTouchExpandable(true); // 분할 조절 버튼 표시
        splitPane.setDividerSize(8); // 분할선 두께 설정

        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    // FileView와 TextListView에 접근하기 위한 Getters
    public FileView getFileView() {
        return fileView;
    }

    public TextListView getTextListView() {
        return textListView;
    }

    @Override
    public void showFileTree() {

    }

    @Override
    public void fileSearch() {

    }

    @Override
    public void showTextList(String[] fileName) {

    }

    // FileView 이너 클래스
    public class FileView extends JPanel {
        private JTextField searchField;
        private JTree fileTree;

        public FileView() {
            setLayout(new BorderLayout());

            // 파일 찾기 필드와 파일 기능 라벨
            JPanel filePanel = new JPanel(new GridLayout(2, 1));
            searchField = new JTextField("파일 찾기");
            JLabel fileFunctionLabel = new JLabel("파일 기능 (아이콘)");

            filePanel.add(fileFunctionLabel);
            filePanel.add(searchField);

            // 파일 트리 구성
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("File Folder");
            root.add(new DefaultMutableTreeNode("자바1.java"));
            root.add(new DefaultMutableTreeNode("자바2.java"));
            root.add(new DefaultMutableTreeNode("자바3.java"));
            root.add(new DefaultMutableTreeNode(new File(System.getProperty("user.dir")).getName()));
            fileTree = new JTree(root);

            // JScrollPane을 사용하여 fileTree에 스크롤 추가
            JScrollPane fileTreeScrollPane = new JScrollPane(fileTree);
            fileTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            fileTreeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            // 구성 요소 추가
            add(filePanel, BorderLayout.NORTH);
            add(fileTreeScrollPane, BorderLayout.CENTER); // 스크롤 가능한 파일 트리 추가
        }

        // 파일 찾기 필드에 접근하기 위한 Getter
        public JTextField getSearchField() {
            return searchField;
        }

        // 파일 트리에 접근하기 위한 Getter
        public JTree getFileTree() {
            return fileTree;
        }
    }

    // TextListView 이너 클래스
    public class TextListView extends JPanel {
        private JList<String> textList;
        public TextListView() {
            setLayout(new BorderLayout());

            // 텍스트 리스트 구성
            textList = new JList<>(new String[]{"자바1.java [x]", "자바2.java [x]"});
            JScrollPane scrollPane = new JScrollPane(textList);

            add(scrollPane, BorderLayout.CENTER);
        }

        // 텍스트 리스트에 접근하기 위한 Getter
        public JList<String> getTextList() {
            return textList;
        }

    }

    public void setEventListner(TreeSelectionListener fileTreeListener, ListSelectionListener textListListener, ActionListener buttonListener) {
        this.fileTreeListener = fileTreeListener;
        this.textListListener = textListListener;
        this.sideButtonListener = buttonListener;
    }
}
