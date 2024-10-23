package ide;

import compiler.CompilerCompo;
import file.FileCompo;
import index.IndexCompo;
import manager.ManagerCompo;
import runner.RunnerCompo;
import texteditor.TextEditorCompo;

import java.util.Scanner;

/**
 *  Command Interpreter have command line.
 *  Command Interpreter can get command and interpret command.
 */
public class CommandInterpreter {
    public CommandInterpreter() {
        commandLine = null;
    }

    protected void getCommandLine() {
        Scanner scanner = new Scanner(System.in);
        String[] tmp;
        do {
            System.out.print(": ");
            commandLine = scanner.nextLine();
            if(commandLine.contains("-")) {
                tmp = commandLine.split("-");
                commandLine = tmp[0];
                if(tmp.length > 1)
                    optionLine = tmp[1];
            } else {
                optionLine = null;
            }
        } while (!isValidCommand());
    }

    public String getOptionLine() {
        return optionLine;
    }

    /**
     * 1-9, and IDE common command are valid.
     * developer can add or delete commands through switch-case statement.
     * @return true if valid, false if invalid.
     */
    private boolean isValidCommand() {
        if (commandLine == null || commandLine.isEmpty()) {
            System.out.println("Invalid command");
            return false;
        } else if (commandLine.length() == 1 && '0' < commandLine.charAt(0) && commandLine.charAt(0) <= '9' && optionLine == null) {
            return true;
        } else if ('a' <= commandLine.charAt(0) && commandLine.charAt(0) <= 'z' || 'A'<= commandLine.charAt(0) && commandLine.charAt(0) <= 'Z') {
            return switch (commandLine.toLowerCase()) {
                case "help", "exit", "version", "back", "set", "upload", "list", "select" -> true;
                default -> false;
            };
        } else {
            System.out.println("Invalid command");
            return false;
        }
    }

    protected void interpretCommand(IDEComponent component) {
        if (component instanceof IndexCompo) {
            interpretIndex(component);
        } else if (component instanceof FileCompo) {
            interpretFile(component);
        } else if (component instanceof CompilerCompo) {
            interpretCompiler(component);
        } else if (component instanceof RunnerCompo) {
            interpretRunner(component);
        } else if (component instanceof TextEditorCompo) {
            interpretTextEditor(component);
        } else if (component instanceof ManagerCompo) {
            interpretManager(component);
        }
    }

    private void interpretIndex(IDEComponent component) {
        if(component.mode.equals(Mode.indNOFILE)){
            switch (commandLine.charAt(0)) {
                case '1': IDE.compoCaller.callComponent(new FileCompo()); break;
                case '2': IDE.compoCaller.callComponent(new FileCompo()); break;
                case '3': IDE.compoCaller.callComponent(new TextEditorCompo()); break;
                case '4', 's', 'S': IDE.compoCaller.callComponent(new ManagerCompo()); break;
                case '5', 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.indHELP); break;
                case 'v', 'V': component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHAVEFILE)) {
            switch (commandLine.charAt(0)) {
                case '1': IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicFileStarting, Mode.fileHAVEUP)); break;
                //매니저에 저장된 주소로 변경해야함
                case '2': IDE.compoCaller.callComponent(new CompilerCompo(FileCompo.getUploadedFile())); break;
                case '3': IDE.compoCaller.callComponent(new RunnerCompo(/*FileCompo.getUploadedFile()*/)); break;
                case '4': IDE.compoCaller.callComponent(new FileCompo()); break;
                case '5': IDE.compoCaller.callComponent(new TextEditorCompo()); break;
                case '6': IDE.compoCaller.callComponent(new ManagerCompo()); break;
                case '7', 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.indHELP); break;
                case 'v', 'V': component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHELP) || component.mode.equals(Mode.indVER) || component.mode.equals(Mode.indERROR)) {
            switch (commandLine.charAt(0)) {
                case 'b', 'B': component.changeMode(); break;
                case 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.indHELP); break;
                case 'v', 'V': component.setMode(Mode.indVER); break;
            }
        }
    }

    private void interpretFile(IDEComponent component) {
        if(component.mode.equals(Mode.fileNOFILE)){
            switch (commandLine.charAt(0)) {
                case '1', 'l', 'L': component.setMode(Mode.fileLIST); break;
                case '2', 'e', 'E': IDE.compoCaller.returnComponent(Mode.indNOFILE); break;
                case 'h', 'H': component.setMode(Mode.fileHELP); break;
                case 'v', 'V': component.setMode(Mode.fileVER); break;
                case 's', 'S': IDE.compoCaller.callComponent(new ManagerCompo()); break;
            }
        } else if (component.mode.equals(Mode.fileLIST)) {
            switch (commandLine.charAt(0)) {
                case '1', 'b', 'B': component.changeMode(); break;
                case 's', 'S': component.setMode(Mode.fileSEL); break;
                case 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.fileHELP); break;
                case 'v', 'V': component.setMode(Mode.fileVER); break;
            }
        } else if(component.mode.equals(Mode.fileHAVESEL)) {
            switch (commandLine.charAt(0)) {
                case '1', 'l', 'L': component.setMode(Mode.fileLIST); break;
                case '2', 'u', 'U': component.setMode(Mode.fileHAVEUPSEL); break;
                case '3': /*component.setMode(Mode.fileDEL);*/ break;
                case '4': /*IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile()));*/ break;
                case '5': /*component.setMode(Mode.fileMAKE);*/ break;
                case '6', 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.fileHELP); break;
                case 'v', 'V': component.setMode(Mode.fileVER); break;
                case 's', 'S': IDE.compoCaller.callComponent(new ManagerCompo()); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUPSEL)) {
            switch (commandLine.charAt(0)) {
                case '1', 'l', 'L': component.setMode(Mode.fileLIST); break;
                case '2', 'u', 'U': component.setMode(Mode.fileHAVESEL); break;
                case '3': /*component.setMode(Mode.fileDEL);*/ break;
                case '4': /*IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile()));*/ break;
                case '5': /*component.setMode(Mode.fileMAKE);*/ break;
                case '6', 'e', 'E': IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case 'h', 'H': component.setMode(Mode.fileHELP); break;
                case 'v', 'V': component.setMode(Mode.fileVER); break;
                case 's', 'S': IDE.compoCaller.callComponent(new ManagerCompo()); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUP)) {
            switch (commandLine.charAt(0)) {
                case '1', 'l', 'L': component.setMode(Mode.fileLIST); break;
                case '2', 'u', 'U': component.setMode(Mode.fileNOFILE); break;
                case '3': /*component.setMode(Mode.fileMAKE);*/ break;
                case '4', 'e', 'E': IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case 's', 'S': IDE.compoCaller.callComponent(new ManagerCompo()); break;
            }
        } else if(component.mode.equals(Mode.fileHELP) || component.mode.equals(Mode.fileVER) || component.mode.equals(Mode.fileERROR)) {
            switch (commandLine.charAt(0)) {
                case 'b', 'B': component.changeMode(); break;
                case 'e', 'E': IDE.compoCaller.returnComponent(); break;
                case 'h', 'H': component.setMode(Mode.fileHELP); break;
                case 'v', 'V': component.setMode(Mode.fileVER); break;
            }
        }
    }

    private void interpretCompiler(IDEComponent component) {
        if(component.mode.equals(Mode.compileJAVA)) {

        }

    }

    private void interpretRunner(IDEComponent component) {

    }

    private void interpretTextEditor(IDEComponent component) {

    }

    private void interpretManager(IDEComponent component) {

    }

    private static String commandLine;
    private static String optionLine;
}