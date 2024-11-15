package compiler;

public class CompilerViewer {
    public void showFileList() {
        System.out.println("##############################");
        System.out.println("Compiled File didn't uploaded");
        System.out.println("1. View File List");
        System.out.println("2. Exit File Searcher");
    }

    public void showCompileList(String fileName, String folder) {
        System.out.println("##############################");
        System.out.println("Compile " + fileName + " at " + folder);
        System.out.println("1. Compile C");
        System.out.println("2. Compile Java");
        System.out.println("3. Compile Auto");
        System.out.println("4. Set basic Compiler");
        System.out.println("5. Exit Compiler System");
    }

    public void showCompiledFile(String folder) {
        System.out.println("##############################");
        System.out.println("File Compiled Successfully, Locate at " + folder);
        System.out.println("1. Run File");
        System.out.println("2. Back to compiler system");
    }

    public void showCompileError(String folder) {
        System.out.println("##############################");
        System.out.println("Compile error");
        System.out.println("you can see ErrorFile at " + folder);
        System.out.println("1. Read error file");
        System.out.println("2. Back to compiler system");
    }
}