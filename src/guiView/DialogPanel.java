package guiView;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
    private JButton dialogButton;
    private JFrame parentFrame;

    public DialogPanel(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        dialogButton = new JButton("Dialog Button");
        add(dialogButton);

        // 다이얼로그 버튼 클릭 시 MainDialog 표시
        dialogButton.addActionListener(e -> {
            MainDialog dialog = new MainDialog(parentFrame);
            dialog.setVisible(true);
        });
    }

    // MainDialog 이너 클래스
    public class MainDialog extends JDialog {
        public MainDialog(JFrame frame) {
            super(frame, "Main Dialog", true);
            setSize(400, 300);
            setLocationRelativeTo(frame);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            add(new JLabel("Main Dialog Content"), BorderLayout.CENTER);
        }
    }

    // InstallerDialog 이너 클래스
    public class InstallerDialog extends JDialog {
        private Container contentPane;

        public InstallerDialog() {
            setTitle("Installer");
            setSize(600, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            setModal(false);
            setVisible(false);

            contentPane = getContentPane();
        }

        public void showInstallerDialog() {
            setVisible(true);
            setModal(true);
        }
    }

    // SettingDialog 이너 클래스
    public class SettingDialog extends JPanel {
        public SettingDialog() {
            // 설정 관련 UI 구성 요소 초기화 및 추가 가능
        }
    }
}
