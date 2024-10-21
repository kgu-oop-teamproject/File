package manager;

import ide.IDEComponent;
import ide.Mode;

import java.util.LinkedList;

public class ManagerCompo extends IDEComponent {
    public ManagerCompo() {
        mode = Mode.managerHAVE;
    }

    @Override
    public void executeComponent() {
        managerRunner.runManager();
    }

    @Override
    public void showComponent() {

    }

    @Override
    public void setMode(Mode m){

    }

    public static String basicJavaC;
    public static String basicJavaJVM;
    public static LinkedList<String> javaCompiler = new LinkedList<>();
    public static LinkedList<String> javaJVM = new LinkedList<>();
    public static String CCompiler;
    public static String settingFilePath;
    public static String basicFileStarting = "C:\\Users\\USER\\Downloads";

    public static ManagerViewer managerViewer = new ManagerViewer();
    public static ManagerRunner managerRunner = new ManagerRunner();
}