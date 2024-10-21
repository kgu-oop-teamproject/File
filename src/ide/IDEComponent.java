package ide;

/**
 * all methods must be overridden by each Component
 */
public abstract class IDEComponent {
    public IDEComponent() {

    }

    public IDEComponent(Mode m) {
        mode = m;
        runableMode = m;
    }

    /**
     * execute function of runner's method
     */
    public abstract void executeComponent();

    /**
     * display textUI by mode
     */
    public abstract void showComponent();

    public abstract void setMode(Mode m);

    public Mode getMode() {
        return mode;
    }

    public void changeMode() {
        if(runableMode != null) {
            if (mode == runableMode) {
                mode = viewingMode;
            } else {
                mode = runableMode;
            }
        }
    }

    public void fileUploaded(){
        isUploaded = true;
    }

    protected boolean isUploaded;
    protected Mode mode = null;
    protected Mode runableMode = null;
    protected Mode viewingMode = null;
}