package file;

import ide.IDE;
import ide.IDEComponent;
import ide.Mode;

import java.io.File;

/**
 *
 */
public class FileCompo extends IDEComponent {
    public FileCompo() {
        super(Mode.fileNOTFILE);
        fileRunner.startPoint = "C:\\Users\\USER\\Downloads";
    }

    public FileCompo(Mode m) {
        super(m);
        fileRunner.startPoint = "C\\";
    }

    public FileCompo(String sp) {
        super(Mode.fileNOTFILE);
        fileRunner.startPoint = sp;
    }

    public FileCompo(String sp, Mode m) {
        super(m);
        fileRunner.startPoint = sp;
    }

    @Override
    public void executeComponent() {
        switch (mode.getValue()) {
            case 0x21:{
                break;
            }
            case 0x22: {
                uploadedFile = null;
                break;
            }
            case 0x23: {
                break;
            }
            case 0x24: {
                uploadedFile = selectedFile;
                break;
            }
            case 0x25: {
                fileRunner.runFileSearcher();
                childFiles = fileRunner.getListofFile();
            }break;
            case 0x26: {
                if(IDE.comInterpreter.getOptionLine() == null){
                    break;
                }
                if(IDE.comInterpreter.getOptionLine().equals("..")) {
                    fileRunner.goParentFolderOfList();
                    childFiles=fileRunner.getListofFile();
                    setMode(Mode.fileLIST);
                } else {
                    File file = new File(fileRunner.startPoint+ "\\" + IDE.comInterpreter.getOptionLine());
                    if(file.exists()) {
                        if(file.isFile()) {
                            selectedFile = fileRunner.selectFileOfList(IDE.comInterpreter.getOptionLine());
                            setMode(Mode.fileHAVESEL);
                        } else {
                            fileRunner.goChildFolderOfList(IDE.comInterpreter.getOptionLine());
                            childFiles=fileRunner.getListofFile();
                            setMode(Mode.fileLIST);
                        }
                    }
                }
            }break;
        }
    }

    @Override
    public void showComponent() {
        switch (mode.getValue()) {
            case 0x21: {
                fileViewer.showFileEmpty(fileRunner.startPoint); break;
            }
            case 0x22: {
                fileViewer.showFileSelected(fileRunner.startPoint, selectedFile); break;
            }
            case 0x23: {
                fileViewer.showFileUP(fileRunner.startPoint, uploadedFile); break;
            }
            case 0x24: {
                fileViewer.showFileUPSelected(fileRunner.startPoint, selectedFile, uploadedFile); break;
            }
            case 0x25: {
                fileViewer.showListFiles(fileRunner.startPoint, childFiles); break;
            }
            case 0x2D: {
                fileViewer.showHavingErrorFile(); break;
            }
            case 0x2E: {
                fileViewer.showManual(); break;
            }
            case 0x2F: {
                fileViewer.showVersion(); break;
            }
        }
    }

    public static File getUploadedFile() {
        return uploadedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    /**
     * if mode is file not have or have selected, uploaded, or both then it can change runnable mode to m.
     * if mode is not runnable, then change viewing mode to m.
     * @param m is Mode to change
     */
    @Override
    public void setMode(Mode m) {
        int modeValue = m.getValue();
        switch(modeValue) {
            case 0x21, 0x22, 0x23, 0x24, 0x25, 0x26: {
                runableMode = m;
                if(modeValue == 0x21 || modeValue == 0x22 || modeValue == 0x23 || modeValue == 0x24) {
                    filemode = m;
                }
                mode = runableMode;
                break;
            }
            case 0x2D, 0x2E, 0x2F: {
                viewingMode = m;
                mode = viewingMode;
                break;
            }
        }
    }

    /**
     *
     */
    @Override
    public void changeMode() {
        if(mode == viewingMode) {
            mode = filemode;
        } else if (mode == runableMode) {
            runableMode = filemode;
            mode = runableMode;
        }
    }

    public File[] childFiles = null;
    private static File uploadedFile = null;
    public File selectedFile = null;

    private Mode filemode = null;

    public FileRunner fileRunner = new FileRunner();
    public FileViewer fileViewer = new FileViewer();
}