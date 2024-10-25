package runner;

import java.io.*;

public class RunnerRunner {
    File runfile;

    public RunnerRunner(File runfile) {
        this.runfile = runfile;
    }

    public void runnerExecute() {
        try {
            String runFileName = runfile.getName().replace(".class","");
            String runFilePath = runfile.getParent() == null ? "." : runfile.getParent();
            //경로얻기

            ProcessBuilder builder = new ProcessBuilder(
                    "java", "-cp", runFilePath, runFileName
            );
            //ProcessBuilder 생성 및 명령어 설정

            builder.directory(runfile.getParentFile());
            //현재 작업 디렉토리 설정

            System.out.print("==========START : " + runFileName + "\n");

            Process runProcess = builder.start();
            //프로세스 시작

            BufferedReader runReader = new BufferedReader(
                    new InputStreamReader(runProcess.getInputStream(), "EUC-kr")
            );
            //프로그램 출력을 읽기위한 버퍼리더 및 인코딩 설정

            String runLine;
            System.out.println("Program Output");
            System.out.println("##############################");
            while ((runLine = runReader.readLine()) != null) {
                System.out.println(runLine);
            }
            System.out.println("##############################");
            //프로그램 출력 읽기
            
            int runExitCode = runProcess.waitFor();
            //exitcode

            if (runExitCode == 0) {
                System.out.println("Program Run Complete");
            } else {
                System.out.println("Runtime Error Occured, exitcode : " + runExitCode);
            }
            //exitcode로 오류판단
        }catch (IOException | InterruptedException e) {
            System.err.println("IDE ERROR : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
