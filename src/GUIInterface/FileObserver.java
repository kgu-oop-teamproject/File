package GUIInterface;

import java.io.File;

public interface FileObserver {
    abstract void newFileUpdate(File newFile);
    abstract void fileSelectedUpdate(File file);
}
