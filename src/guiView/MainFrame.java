package guiView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SidePanel sidePanel;
    private MainPanel mainPanel;

    public MainFrame() {
        setTitle("Java Code Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // SidePanel과 MainPanel 초기화
        sidePanel = new SidePanel();
        mainPanel = new MainPanel();

        // JSplitPane으로 SidePanel과 MainPanel을 가로로 분할
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        mainSplitPane.setDividerLocation(300); // SidePanel의 초기 너비 설정
        mainSplitPane.setContinuousLayout(true);
        mainSplitPane.setDividerSize(8);

        add(mainSplitPane, BorderLayout.CENTER);

        setSize(1280, 720);
        setLocationRelativeTo(null); // 화면 중앙에 위치
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
