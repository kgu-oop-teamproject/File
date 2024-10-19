package manager;

import ide.IDEComponent;
import ide.Mode;

public class ManagerCompo extends IDEComponent {
    public ManagerCompo() {

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

    public static ManagerViewer managerViewer = new ManagerViewer();
    public static ManagerRunner managerRunner = new ManagerRunner();
}