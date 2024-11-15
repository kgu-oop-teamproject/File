package compiler;

import file.FileCompo;
import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import manager.Keys;
import manager.ManagerCompo;
import runner.RunnerCompo;
import texteditor.TextEditorCompo;

import java.io.File;

public class CompilerCompo extends IDEComponent {
    public CompilerCompo(File compile, Mode mode) {
        this.fileToCompile = compile;
        setMode(mode);
    }

    @Override
    public void executeComponent() {
        switch (mode) {
            case Mode.compileC: {
                if(compilerRunner.CompileC(fileToCompile)) {
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
                break;
            }
            case Mode.compileJAVA: {
                if(compilerRunner.CompileJavaClass(fileToCompile)){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
                break;
            }
            case Mode.compileAUTO: {
                if(compilerRunner.CompileAuto(fileToCompile)){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
            }
        }

    }

    @Override
    public void showComponent() {
        switch (mode) {
            case Mode.compileNOFILE: {
                compilerViewer.showFileList(); break;
            }
            case Mode.compileHAVEFILE: {
                compilerViewer.showCompileList(fileToCompile.getName(), ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString())+"\\output"); break;
            }
            case Mode.compileCOMPLETE: {
                compilerViewer.showCompiledFile(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output"); break;
            }
            case Mode.compileNOTCOMPLETE: {
                compilerViewer.showCompileError(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output"); break;
            }
        }
    }

    @Override
    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x30 < modeValue && modeValue <= 0x32){
            indexMode = m;
        } else if (0x32 < modeValue && modeValue <= 0x37) {
            runableMode = m;
        } else if (0x3A < modeValue && modeValue <= 0x3F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.compileNOFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.FILE.getKeyString()), Mode.fileLIST)); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if(mode.equals(Mode.compileHAVEFILE)) {
            switch (command) {
                case "1": setMode(Mode.compileC); break;
                case "2": setMode(Mode.compileJAVA); break;
                case "3": setMode(Mode.compileAUTO); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if (mode.equals(Mode.compileCOMPLETE)) {
            switch (command) {
                case "1", "run" : IDE.compoCaller.callComponent(new RunnerCompo(CompilerCompo.getLastsuccessFile(), Mode.runJAVA)); break;
                case "2", "back": setMode(indexMode); break;
            }
        } else if (mode.equals(Mode.compileNOTCOMPLETE)) {
            switch (command) {
                case "1", "read": IDE.compoCaller.callComponent(new TextEditorCompo(CompilerCompo.getLastFailedFile(), Mode.textREAD)); break;
                case "2", "back": setMode(indexMode); break;
            }
        } else if(mode.equals(Mode.compileHELP) || mode.equals(Mode.compileVER) || mode.equals(Mode.compileERROR)) {
            switch (command) {
                case "back": setMode(indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.fileHELP); break;
                case "version": setMode(Mode.fileVER); break;
            }
        }
    }

    public static File getLastFailedFile() {
        return lastFailedFile;
    }

    public static File getLastsuccessFile(){
        return lastsuccessFile;
    }

    private File fileToCompile;
    public static File lastFailedFile = null;
    public static File lastsuccessFile = null;

    public final CompilerRunner compilerRunner = new CompilerRunner();
    public final CompilerViewer compilerViewer = new CompilerViewer();
}