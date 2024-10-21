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
        System.out.println("4. Set basic Compiler");
        System.out.println("5. Exit Compiler System");
    }

    public void showCompileJava(String[] javaC){
        int i = 1;
        System.out.println("##############################");
        System.out.println("Select Compiler Version");
        for(String s:javaC){
            System.out.println(i + ". "+ s);
        }
    }

    public void showCompileC(String[] C) {
        int i = 1;
        System.out.println("##############################");
        System.out.println("Select Compiler Version");
        for (String s : C) {
            System.out.println(i + ". " + s);
        }
    }
}