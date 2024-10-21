package compiler;

import file.FileCompo;
import ide.IDEComponent;
import ide.Mode;

import java.io.File;

public class CompilerCompo extends IDEComponent {
    public CompilerCompo() {
        setMode(Mode.compileNOTHAVE);
    }

    public CompilerCompo(File compileFile) {
        setMode(Mode.compileHAVE);
    }

    public void executeComponent() {

    }

    public void showComponent() {

    }

    public void setMode(Mode m){

    }

    File compileFile = FileCompo.getUploadedFile();

    public CompilerRunner compilerRunner = new CompilerRunner();
    public CompilerViewer compilerViewer = new CompilerViewer();
}