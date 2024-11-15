package manager;

import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import java.util.Properties;

/**
 *
 */
public class ManagerCompo extends IDEComponent {

    public ManagerCompo(Mode mode) {
        setMode(mode);
    }

    @Override
    public void executeComponent() {
        switch(mode) {
            case managerCHECK: {
                if(managerRunner.settingFileExits(System.getProperty("user.dir"))){
                    setMode(Mode.managerHAVE);
                } else {
                    setMode(Mode.managerINSTALLER);
                } break;
            }
            case managerHAVE: settings = managerRunner.getSettingFile(System.getProperty("user.dir")); break;
            case managerINSTALLER: settings = managerRunner.installIDE(System.getProperty("user.dir")); break;
            case managerSETPATH: {
                break;
            }
        }
    }

    @Override
    public void showComponent() {
        switch (mode) {
            case managerCHECK: managerViewer.showChecking(); break;
            case managerLIST: managerViewer.showPropertyList(settings); break;
        }
    }

    @Override
    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x50 < modeValue && modeValue <= 0x51) {
            indexMode = m;
        } else if(0x51 < modeValue && modeValue <= 0x55) {
            runableMode = m;
        } else if(0x5C < modeValue && modeValue <= 0x5F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.managerLIST)) {
            switch (command) {
                case "1", "change": setMode(Mode.managerSETPATH); break;
                case "2", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } if(mode.equals(Mode.managerSETPATH)) {
            switch (command) {

            }
        }
    } //Event -> model // event -> 어떤 이벤트 -> 어느 모델에게 어떤 값 전달해줘야하나 Presenter

    public static String getPropertyValue(String PropertyName) {
        return settings.getProperty(PropertyName);
    }

    private static Properties settings;

    private final ManagerViewer managerViewer = new ManagerViewer();
    private final ManagerRunner managerRunner = new ManagerRunner();
}