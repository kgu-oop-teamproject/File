package ide;

import index.IndexCompo;
import manager.ManagerCompo;

/**
 * IDE has a Component Caller and Command Interpreter.
 * the flow of IDE
 * 1. call Manager to check is IDE has a setting file
 * 1.1. if IDE has a setting file, then load values of setting file
 * 1.2. if IDE don't have setting file, then get values and make setting file and put into the setting file
 * 2. call Index to use each IDE's function.
 * 3. if there are no exist Component, then exit IDE.
 */
public class IDE {
    /*
    public static void main(String[] args) {
        //these statements run flow 1 to flow 2
        compoCaller.callComponent(new ManagerCompo(Mode.managerCHECK));
        compoCaller.runComponent(); //check "settings.properties"
        compoCaller.runComponent(); //install or get setting file.
        compoCaller.returnComponent();
        //end checking.

        //these statements run flow 2 to flow 3
        compoCaller.callComponent(new IndexCompo(Mode.indNOFILE));
        do {
            compoCaller.runComponent(); //execute first and show result after.
            comInterpreter.getCommandLine();
            comInterpreter.interpretCommand(compoCaller.getRunningComponent());
        } while(compoCaller.getRunningComponent() != null);
    }
    */
    public static final ComponentCaller compoCaller = new ComponentCaller();
    public static final CommandInterpreter comInterpreter = new CommandInterpreter();
}