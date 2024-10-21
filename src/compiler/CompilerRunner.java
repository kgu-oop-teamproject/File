package compiler;

import java.io.File;
import java.io.IOException;

public class CompilerRunner {
    public static boolean CompileJava (File compileFile, String[] args) {
        ProcessBuilder compilerBuilder = new ProcessBuilder("cmd", "dir");
        try{
            Process compilerProcess = compilerBuilder.start();
        } catch (IOException e) {}

        return true;
    }


}