package guiModel;

import java.util.Arrays;
import java.util.List;
import java.io.*;



public class Compiler {

    public static int errorFlag = 0;

    public String compile(File file, File javac, File java) {
        File currentDir = new File(".").getAbsoluteFile();
        String filePath = file.getAbsolutePath();
        String outPath = new File(currentDir.getParentFile(), "default").getPath();
        String javacPath = javac.getAbsolutePath();
        String javaPath = java.getAbsolutePath();
        String classPath = file.getName().replaceFirst("[.][^.]+$", "");//초기 인수들

        int exitCode;
        String compileError;
        String runOutput; //결과들

        File defaultDir = new File(outPath);
        if (defaultDir.exists() && defaultDir.isDirectory()) {
            File[] files = defaultDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.delete()) {
                        return "Failed to empty default file";
                    }
                }
            }
        } //default 폴더 비우기

        List<String> compileCommand = Arrays.asList( //컴파일커맨드 구성
                javacPath,
                "-d",
                outPath,
                filePath
        );
        List<String> runCommand = Arrays.asList( //런커맨드구성
                javaPath,
                "-cp",
                outPath,
                classPath
        );

        ProcessBuilder compileBuilder = new ProcessBuilder(compileCommand);
        compileBuilder.redirectErrorStream(true);
        Process compileProcess; //프로세스 설정

        try {
            compileProcess = compileBuilder.start();
            exitCode = compileProcess.waitFor();
        }
        catch (InterruptedException | IOException e) {
            errorFlag = 1;
            return "Failed to start compiler process: " + e.getMessage();
        }//프로세스 실행

        if(exitCode != 0) {
            compileError = new Error().errorRead(compileProcess);
            errorFlag = 1;
            return compileError;
        }
        else {
            runOutput = new Run().runClass(runCommand);
            return runOutput;
        }
    }
}
