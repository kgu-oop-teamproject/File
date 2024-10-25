package runner;

public class RunnerViewer {
    public void runnerNoFile() {
        System.out.println("##############################");
        System.out.println("File didn't uploaded");
        System.out.println("1. View File List");
        System.out.println("2. Exit Run Menu");
    }
    public void runnerCompileError() {
        System.out.println("##############################");
        System.out.println("Please compile and run again");
        System.out.println("1. Exit Run Menu");
    }
    public void runnerFinished() {
        System.out.println("##############################");
        System.out.println("Runner Finished");
        System.out.println("1. Exit Run Menu");
    }
    public void runnerRunTimeError() {
        System.out.println("##############################");
        System.out.println("Run-Time Error Occured");
        System.out.println("1. Exit Run Menu");
    }
}
