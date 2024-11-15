package guiModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Run {
    public String runClass(List<String> runCommand) {
        int exitCode;

        ProcessBuilder runBuilder = new ProcessBuilder(runCommand);
        runBuilder.redirectErrorStream(true);
        Process runProcess; //프로세스 설정

        try {
            runProcess = runBuilder.start();
            exitCode = runProcess.waitFor();
        }
        catch (InterruptedException | IOException e) {
            Compiler.errorFlag = 1;
            return "Failed to start Java process: " + e.getMessage();
        }//프로세스 실행

        if(exitCode != 0){
            Compiler.errorFlag = 1;
        }
        else {
            Compiler.errorFlag = 0;
        }

        return runRead(runProcess);
    }

    public String runRead(Process process){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            StringBuilder runBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                runBuilder.append(line).append("\n");
            }

            return  runBuilder.toString();
        }
        catch (IOException e) {
            Compiler.errorFlag = 1;
            return "Failed to read run result:\n" + e.getMessage();
        }
    }
}
