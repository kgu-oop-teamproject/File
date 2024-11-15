package index;

import java.io.File;

public class IndexViewer {

    public void showStartProgram() {
        System.out.println("##############################");
        System.out.println("1. Search File");
        System.out.println("2. View Error File");
        System.out.println("3. Edit Text File");
        System.out.println("4. IDE Settings");
        System.out.println("5. Exit");
    }

    public void showHavingFile(File file) {
        System.out.println("##############################");
        System.out.println("1. Search File");
        System.out.println("2. Compile File :" + file.getName());
        System.out.println("3. Run File :" +file.getName());
        System.out.println("4. View Log File");
        System.out.println("5. Edit Text File :" + file.getName());
        System.out.println("6. IDE Settings");
        System.out.println("7. Exit");
    }

    public void showManual() {
        System.out.println("##############################");
        System.out.println("you can input 1 - 7 for select menu");
        System.out.println("you can input word {\"help, version, settings, back, exit\"}");
        System.out.println("**help** used itself ++ you can see the manual of index");
        System.out.println("**version** used itself ++ you can see the history of versions");
        System.out.println("**set** used itself ++ you can enter IDE Settings, likewise you can also put number 6");
        System.out.println("**back** used itself ++ when you put **help** or **version**, you can comeback to Main Index");
        System.out.println("**exit** used itself ++ when you exit the program");
    }

    public void showVersion() {
        System.out.println("##############################");
        System.out.println("Index version 0.1.0 @2024.10.10- @made by 201812478");
    }

    public void showError(int ErrorCode) {
        System.out.println("##############################");
        System.out.println("Error " + ErrorCode + " occurred");
    }
}