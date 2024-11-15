package texteditor;

import file.FileCompo;
import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import manager.ManagerCompo;
import java.io.File;
import java.util.LinkedList;

/**
 * 컴파일러에서 에러파일 읽는 것과 업로드 된 파일 읽는것 구분
 */
public class TextEditorCompo extends IDEComponent {
    public TextEditorCompo(String readFile, Mode mode) {
        setMode(mode);
        editingFile = new File(readFile);
    }

    public TextEditorCompo(File readFile, Mode mode) {
        setMode(mode);
        editingFile = readFile;
    }

    public TextEditorCompo(Mode mode) {
        setMode(mode);
    }

    @Override
    public void executeComponent() {
        switch (mode) {
            case Mode.textHAVE: setMode(Mode.textREAD); break;
            case Mode.textREAD: fileLine = textEditorRunner.readFile(editingFile); break;
            case Mode.textWRITE: break;
            case Mode.textBOTH: break;
        }
    }

    @Override
    public void showComponent() {
        switch (mode) {
            case Mode.textNOFILE: break;
            case Mode.textHAVE: break;
            case Mode.textREAD: textEditorViewer.showReadingFile(fileLine); break;
        }
    }

    @Override
    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x60 < modeValue && modeValue <= 0x62) {
            indexMode = m;
        } else if (0x62< modeValue && modeValue <= 0x65) {
            runableMode = m;
        } else if (0x6C < modeValue && modeValue <= 0x6F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.textNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileLIST)); break;
                case "2", "read" : setMode(Mode.textREAD);
                case "3", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } else if (mode.equals(Mode.textREAD)) {
            switch (command) {
                case "1", "back", "exit": IDE.compoCaller.returnComponent(); break;
            }
        }
    }

    private File editingFile = null;
    private LinkedList<String> fileLine = new LinkedList<>();

    private final TextEditorRunner textEditorRunner = new TextEditorRunner();
    private final TextEditorViewer textEditorViewer = new TextEditorViewer();
}