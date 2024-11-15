package runner;

import manager.Keys;
import manager.ManagerCompo;
import java.io.*;

public class RunnerRunner {
    /**
     *
     * @param file is javaFile, it has extension ".class".
     * @return true if not having runtime error.
     */
    public boolean runJava(File file) {
        String javaPath = ManagerCompo.getPropertyValue(Keys.BASICJAVA.getKeyString()) + "//bin//java.exe";
        String withoutExtension = file.getName().substring(0, file.getName().lastIndexOf("."));

        ProcessBuilder javaProcessBuilder = new ProcessBuilder();
        javaProcessBuilder.directory(file.getParentFile());
        javaProcessBuilder.command("cmd.exe", "/c", javaPath, withoutExtension);
        javaProcessBuilder.redirectErrorStream(true);

        BufferedReader processOutput; //실행한 프로세스가 출력하는 스트림을 받는 버퍼 리더
        PrintWriter processInput;  //실행한 프로세스에 값을 전달하는 프린트 라이터
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));//사용자 입력

        int exitCode;

        try {
            Process javaProcess = javaProcessBuilder.start();

            processOutput = new BufferedReader(new InputStreamReader(javaProcess.getInputStream()));
            processInput = new PrintWriter(new OutputStreamWriter(javaProcess.getOutputStream()), true);

            Thread javaProcessThread = new Thread(() -> {
               String processOutLine;
               try {
                   while ((processOutLine = processOutput.readLine()) != null) {
                       System.out.println(processOutLine);
                   }
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
            });
            javaProcessThread.start();

            String userInputLine;
            while(true) {
                System.out.print("> ");
                userInputLine = userInput.readLine();
                if(userInputLine != null) {
                    break;
                }
            }

            processInput.println(userInputLine);
            processInput.flush();

            javaProcessThread.interrupt();

            exitCode = javaProcess.waitFor();
            System.out.println("Process has exited with code " + exitCode);

            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean runC(File file) {
        return false;
    }

    public boolean runAuto(File file){
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if(extension.equals("class"))
            return runJava(file);
        else if(extension.equals("exe"))
            return runC(file);
        else
            return false;
    }
}