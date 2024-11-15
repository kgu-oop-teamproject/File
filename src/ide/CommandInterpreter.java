package ide;

import java.util.Scanner;

/**
 *  Command Interpreter have command line.
 *  Command Interpreter can get command and interpret command.
 */
public class CommandInterpreter {
    public CommandInterpreter() {

    }

    /**
     * tokenizing by '-'
     */
    protected void getCommandLine() {
        commandLine = null;
        String[] tmp;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(": ");
            commandLine = scanner.nextLine();
            if(commandLine.contains("-")) {
                tmp = commandLine.split("-");
                command = tmp[0].toLowerCase();
                if(tmp.length == 2)
                    option = tmp[1];
            } else {
                command = commandLine.toLowerCase();
                option = null;
            }
        } while (!isValidCommand());
    }

    public String getOption() {
        return option;
    }

    /**
     * 1-9, and IDE common command are valid.
     * developer can add or delete commands through switch-case statement.
     * @return true if valid, false if invalid.
     */
    private boolean isValidCommand() {
        if (commandLine == null || commandLine.isEmpty()) {
            System.out.println("Didn't enter any command.");
            return false;
        } else if (command.length() == 1 && '0' < command.charAt(0) && command.charAt(0) <= '9' && option == null) {
            return true;
        } else if ('a' <= command.charAt(0) && command.charAt(0) <= 'z' || 'A'<= command.charAt(0) && command.charAt(0) <= 'Z') {
            switch (command.toLowerCase()) {
                case "help", "exit", "version","back",
                     "set", "list", "gcc", "jdk", "run", "read", "change",
                     "upload", "select", "delete" : { return true;}
                default : {
                    System.out.println("Invalid command"); return false;
                }
            }
        } else {
            System.out.println("Invalid command");
            return false;
        }
    }

    protected void interpretCommand(IDEComponent component) {
        component.interpretCommand(command, option);
    }

    /*
    private void interpretIndex(IDEComponent component) {
        if(component.mode.equals(Mode.indNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileNOFILE)); break;
                case "2": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output\\Error", Mode.fileLIST)); break;
                case "3": IDE.compoCaller.callComponent(new TextEditorCompo(Mode.textNOFILE)); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerLIST)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHAVEFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileHAVEUP)); break;
                case "2": IDE.compoCaller.callComponent(new CompilerCompo(FileCompo.getUploadedFile(), Mode.compileHAVEFILE)); break;
                case "3": IDE.compoCaller.callComponent(new RunnerCompo(FileCompo.getUploadedFile(), Mode.runHAVEFILE)); break;
                case "4": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output\\Error", Mode.fileLIST)); break;
                case "5": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile(), Mode.textREAD)); break;
                case "6": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "7", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHELP) || component.mode.equals(Mode.indVER) || component.mode.equals(Mode.indERROR)) {
            switch (command) {
                case "back": component.setMode(component.indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        }
    }

    private void interpretFile(IDEComponent component) {
        if(component.mode.equals(Mode.fileNOFILE)){
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indNOFILE); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileLIST)) {
            switch (command) {
                case "1", "back": component.setMode(component.indexMode); break;
                case "select": component.setMode(Mode.fileSEL); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        } else if(component.mode.equals(Mode.fileHAVESEL)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileHAVEUPSEL); break;
                case "3", "delete": component.setMode(Mode.fileDELETE); break;
                case "4": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getSelectedFile(), Mode.textREAD)); break;
                case "5": component.setMode(Mode.fileMAKE); break;
                case "6", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUPSEL)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileHAVESEL); break;
                case "3", "delete": component.setMode(Mode.fileDELETE); break;
                case "4": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile(), Mode.textREAD)); break;
                case "5": component.setMode(Mode.fileMAKE); break;
                case "6", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUP)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileNOFILE); break;
                case "3": component.setMode(Mode.fileMAKE); break;
                case "4", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if(component.mode.equals(Mode.fileHELP) || component.mode.equals(Mode.fileVER) || component.mode.equals(Mode.fileERROR)) {
            switch (command) {
                case "back": component.setMode(component.runableMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        }
    }

    private void interpretCompiler(IDEComponent component) {
        if(component.mode.equals(Mode.compileNOFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue(Keys.FILE.getKeyString()), Mode.fileLIST)); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if(component.mode.equals(Mode.compileHAVEFILE)) {
            switch (command) {
                case "1": component.setMode(Mode.compileC); break;
                case "2": component.setMode(Mode.compileJAVA); break;
                case "3": component.setMode(Mode.compileAUTO); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if (component.mode.equals(Mode.compileCOMPLETE)) {
            switch (command) {
                case "1", "run" : IDE.compoCaller.callComponent(new RunnerCompo(CompilerCompo.getLastsuccessFile(), Mode.runJAVA)); break;
                case "2", "back": component.setMode(component.indexMode); break;
            }
        } else if (component.mode.equals(Mode.compileNOTCOMPLETE)) {
            switch (command) {
                case "1", "read": IDE.compoCaller.callComponent(new TextEditorCompo(CompilerCompo.getLastFailedFile(), Mode.textREAD)); break;
                case "2", "back": component.setMode(component.indexMode); break;
            }
        } else if(component.mode.equals(Mode.compileHELP) || component.mode.equals(Mode.compileVER) || component.mode.equals(Mode.compileERROR)) {
            switch (command) {
                case "back": component.setMode(component.indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        }
    }

    private void interpretRunner(IDEComponent component) {
        if(component.mode.equals(Mode.runHAVEFILE)){
            switch (command) {
                case "1": component.setMode(Mode.runC); break;
                case "2": component.setMode(Mode.runJAVA); break;
                case "3": component.setMode(Mode.runAUTO); break;
                case "4", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } if(component.mode.equals(Mode.runC) || component.mode.equals(Mode.runJAVA) || component.mode.equals(Mode.runAUTO)) {
            switch (command){
                case "back", "exit": IDE.compoCaller.returnComponent(); break;
            }
        }
    }

    private void interpretTextEditor(IDEComponent component) {
        if(component.mode.equals(Mode.textNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.getPropertyValue("file searching"), Mode.fileLIST)); break;
                case "read", "2": component.setMode(Mode.textREAD);
                case "3", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } else if (component.mode.equals(Mode.textREAD)) {
            switch (command) {
                case "back", "exit": IDE.compoCaller.returnComponent(); break;
            }
        }
    }

    private void interpretManager(IDEComponent component) {
        if(component.mode.equals(Mode.managerLIST)) {
            switch (command) {
                case "1", "exit": IDE.compoCaller.returnComponent(); break;
                case "change" :
            }
        }
    }
    */

    private String commandLine;
    private String command;
    private String option;
}