package index;

import file.FileCompo;
import ide.IDEComponent;
import ide.Mode;

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
        if(m == Mode.indNOFILE || m == Mode.indHAVEFILE) {
            runableMode = m;
            mode = runableMode;
        } else {
            viewingMode = m;
            mode = viewingMode;
        }
    }

    public static IndexViewer indexViewer = new IndexViewer();
    //public static IndexRunner indexRunner = new IndexRunner();
}