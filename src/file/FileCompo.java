package file;

import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import manager.ManagerCompo;
import texteditor.TextEditorCompo;
import java.io.File;

/**
 *
 */
public class FileCompo extends IDEComponent {

    public FileCompo(String sp, Mode m) {
        setMode(m);
        fileRunner.startPoint = sp;
    }

    @Override
    public void executeComponent() {
        switch (mode) {
            case Mode.fileNOFILE:selectedFile = null; uploadedFile = null; break;
            case Mode.fileHAVESEL: uploadedFile = null; break;
            case Mode.fileHAVEUP: selectedFile = null; break;
            case Mode.fileHAVEUPSEL: uploadedFile = selectedFile; break;
            case Mode.fileLIST: {
                if(selectedFile == null) {
                    if(uploadedFile == null) {
                        indexMode = Mode.fileNOFILE;
                    } else {
                        indexMode = Mode.fileHAVEUP;
                    }
                } else {
                    if(uploadedFile == null) {
                        indexMode = Mode.fileHAVESEL;
                    } else {
                        indexMode = Mode.fileHAVEUPSEL;
                    }
                }
                fileRunner.runFileSearcher();
                childFiles = fileRunner.getListofFile();
                break;
            }
            case Mode.fileSEL: {
                if(IDE.comInterpreter.getOption() == null){
                    break;
                }
                if(IDE.comInterpreter.getOption().equals("..")) {
                    fileRunner.goParentFolderOfList();
                    childFiles = fileRunner.getListofFile();
                    setMode(Mode.fileLIST);
                } else {
                    String option = IDE.comInterpreter.getOption();
                    File file = new File(fileRunner.startPoint+ "\\" + option);
                    if(file.exists()) {
                        if(file.isFile()) {
                            selectedFile = fileRunner.selectFileOfList(option);
                            if(uploadedFile == null) {
                                setMode(Mode.fileHAVESEL);
                            } else {
                                setMode(Mode.fileHAVEUPSEL);
                            }
                        } else {
                            fileRunner.goChildFolderOfList(option);
                            childFiles = fileRunner.getListofFile();
                            setMode(Mode.fileLIST);
                        }
                    }
                } break;
            }
            case Mode.fileDELETE: {
                fileRunner.deleteFile(selectedFile);
                selectedFile = null;
                if(uploadedFile != null) {
                    setMode(Mode.fileHAVEUP);
                } else {
                    setMode(Mode.fileNOFILE);
                } break;
            }
        }
    }

    @Override
    public void showComponent() {
        switch (mode) {
            case Mode.fileNOFILE: {
                fileViewer.showFileEmpty(fileRunner.startPoint); break;
            }
            case Mode.fileHAVESEL: {
                fileViewer.showFileSelected(fileRunner.startPoint, selectedFile); break;
            }
            case Mode.fileHAVEUP: {
                fileViewer.showFileUP(fileRunner.startPoint, uploadedFile); break;
            }
            case Mode.fileHAVEUPSEL: {
                fileViewer.showFileUPSelected(fileRunner.startPoint, selectedFile, uploadedFile); break;
            }
            case Mode.fileLIST: {
                fileViewer.showListFiles(fileRunner.startPoint, childFiles); break;
            }
            case Mode.fileERROR: {
                fileViewer.showHavingErrorFile(); break;
            }
            case Mode.fileHELP: {
                fileViewer.showManual(); break;
            }
            case Mode.fileVER: {
                fileViewer.showVersion(); break;
            }
        }
    }

    @Override
    public void setMode(Mode m) {//파일 리스트 선택에서 뒤로 갈 때 파일 상태로 다시 돌아가야 함
        int modeValue = m.getValue();
            if(0x20 < modeValue && modeValue <= 0x24) {
                indexMode = m;
            } else if(0x24 < modeValue && modeValue <= 0x28) {
                runableMode = m;
            } else if(0x2C < modeValue && modeValue <= 0x2F) {
                viewingMode = m;
            }

        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.fileNOFILE)){
            switch (command) {
                case "1", "list": setMode(Mode.fileLIST); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indNOFILE); break;
                case "help": setMode(Mode.fileHELP); break;
                case "version": setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (mode.equals(Mode.fileLIST)) {
            switch (command) {
                case "1", "back": setMode(indexMode); break;
                case "select": setMode(Mode.fileSEL); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
            }
        } else if(mode.equals(Mode.fileHAVESEL)) {
            switch (command) {
                case "1", "list": setMode(Mode.fileLIST); break;
                case "2", "upload": setMode(Mode.fileHAVEUPSEL); break;
                case "3", "delete": setMode(Mode.fileDELETE); break;
                case "4": IDE.compoCaller.callComponent(new TextEditorCompo(selectedFile, Mode.textREAD)); break;
                case "5": /*setMode(Mode.fileMAKE);*/ break;
                case "6", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.fileHELP); break;
                case "version": setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (mode.equals(Mode.fileHAVEUPSEL)) {
            switch (command) {
                case "1", "list": setMode(Mode.fileLIST); break;
                case "2", "upload": setMode(Mode.fileHAVESEL); break;
                case "3", "delete": setMode(Mode.fileDELETE); break;
                case "4": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile(), Mode.textREAD)); break;
                case "5": /*setMode(Mode.fileMAKE);*/ break;
                case "6", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "help": setMode(Mode.fileHELP); break;
                case "version": setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (mode.equals(Mode.fileHAVEUP)) {
            switch (command) {
                case "1", "list": setMode(Mode.fileLIST); break;
                case "2", "upload": setMode(Mode.fileNOFILE); break;
                case "3": /*setMode(Mode.fileMAKE);*/ break;
                case "4", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if(mode.equals(Mode.fileHELP) || mode.equals(Mode.fileVER) || mode.equals(Mode.fileERROR)) {
            switch (command) {
                case "back": setMode(indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.fileHELP); break;
                case "version": setMode(Mode.fileVER); break;
            }
        }
    }

    public static File getUploadedFile() {
        return uploadedFile;
    }

    public static File getSelectedFile() {
        return selectedFile;
    }

    private File[] childFiles = null;
    private static File uploadedFile = null;
    private static File selectedFile = null;

    public final FileRunner fileRunner = new FileRunner();
    public final FileViewer fileViewer = new FileViewer();
}