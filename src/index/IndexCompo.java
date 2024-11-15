package index;

import compiler.CompilerCompo;
import file.FileCompo;
import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import manager.Keys;
import manager.ManagerCompo;
import runner.RunnerCompo;
import texteditor.TextEditorCompo;

public class IndexCompo extends IDEComponent {

    public IndexCompo(Mode m) {
        super(m);
    }

    @Override
    public void executeComponent() {

    }

    @Override
    public void showComponent() {
        switch (mode.getValue()) {
            case 0x11:{
                indexViewer.showStartProgram(); break;
            }
            case 0x12:{
                indexViewer.showHavingFile(FileCompo.getUploadedFile()); break;
            }
            case 0x1D:{
                indexViewer.showError(1); break;
            }
            case 0x1E:{
                indexViewer.showManual(); break;
            }
            case 0x1F:{
                indexViewer.showVersion(); break;
            }
        }
    }

    /**
     * in runnable mode, if changeMode is viewing
     * @param m to set mode
     */
    @Override
    public void setMode(Mode m) {
        int modeValue = m.getValue();
        if(0x10 < modeValue && modeValue <= 0x12) {
            indexMode = m;
        } else if(0x1C < modeValue && modeValue <= 0x1F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.indNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileNOFILE)); break;
                case "2": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output\\Error", Mode.fileLIST)); break;
                case "3": IDE.compoCaller.callComponent(new TextEditorCompo(Mode.textNOFILE)); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerLIST)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        } else if (mode.equals(Mode.indHAVEFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileHAVEUP)); break;
                case "2": IDE.compoCaller.callComponent(new CompilerCompo(FileCompo.getUploadedFile(), Mode.compileHAVEFILE)); break;
                case "3": IDE.compoCaller.callComponent(new RunnerCompo(FileCompo.getUploadedFile(), Mode.runHAVEFILE)); break;
                case "4": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output\\Error", Mode.fileLIST)); break;
                case "5": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile(), Mode.textREAD)); break;
                case "6": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "7", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        } else if (mode.equals(Mode.indHELP) || mode.equals(Mode.indVER) || mode.equals(Mode.indERROR)) {
            switch (command) {
                case "back": setMode(indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        }
    }

    public final IndexViewer indexViewer = new IndexViewer();
    public final IndexRunner indexRunner = new IndexRunner();
}