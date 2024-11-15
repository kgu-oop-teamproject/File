package runner;

public class RunnerViewer {
    public void showFileList() {
        System.out.println("##############################");
        System.out.println("Compiled File didn't uploaded");
        System.out.println("1. View File List");
        System.out.println("2. Exit File Searcher");
    }

    public void showRunList(String fileName) {
        System.out.println("##############################");
        System.out.println("Run " + fileName);
        System.out.println("1. Run C");
        System.out.println("2. Run Java");
        System.out.println("3. Run Auto");
        System.out.println("4. Exit Run System");
    }

    public void showRunSuccess(String folder) {
        System.out.println("##############################");
        System.out.println("File Run Successfully");
        System.out.println("Enter \"back\" to go back");
    }

    public void showRunError(String folder) {
        System.out.println("##############################");
        System.out.println("Compile error");
        System.out.println("you can see ErrorFile at " + folder);
        System.out.println("1. Read error file");
        System.out.println("2. Back to compiler system");
    }
}
