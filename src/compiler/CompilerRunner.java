package compiler;

import manager.Keys;
import manager.ManagerCompo;

import java.io.File;
import java.io.IOException;

public class CompilerRunner {
    /**
     * compiled file locate at user's output directory.
     * @param file is Compiled by gcc
     * @return true if compiled, false if it has compiling error.
     */
    public boolean CompileC(File file) {
        String gccPath = ManagerCompo.getPropertyValue(Keys.BASICGCC.getKeyString()) + "\\bin\\gcc.exe";
        String outputPath = ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output";
        String compiled = outputPath + "\\Compiled\\C";
        String failed = outputPath + "\\Error\\C";
        String withoutExtension = file.getName().substring(0, file.getName().lastIndexOf("."));

        ProcessBuilder gccProcessBuilder = new ProcessBuilder();
        gccProcessBuilder.directory(file.getParentFile());
        gccProcessBuilder.command(gccPath, file.getName(), "-o", compiled + "\\" + withoutExtension);//test
        gccProcessBuilder.redirectError(new File(failed + "\\" + file.getName() + ".error"));

        int exitCode;

        try {
            Process gccProcess = gccProcessBuilder.start();

            exitCode = gccProcess.waitFor();
            if(exitCode == 0) {
                File error = new File(failed + "\\" + file.getName() + ".error");
                error.delete();

                CompilerCompo.lastsuccessFile = new File(compiled + "\\" + withoutExtension + ".exe");//Window only
                System.out.println(CompilerCompo.lastsuccessFile.getAbsolutePath());

                return true;
            } else {
                //에러파일 넘겨주기
                CompilerCompo.lastFailedFile = new File(failed + "\\" + file.getName() + ".error");
                return false;
            }
        } catch (IOException | InterruptedException e){
            return false;
        }
    }

    /**
     * compiling java not included a package.
     * @param file is Compiled by javac
     * @return true if compile success.
     */
    public boolean CompileJavaClass(File file) {
        String javacPath = ManagerCompo.getPropertyValue(Keys.BASICJAVA.getKeyString()) + "\\bin\\javac.exe";
        String outputPath = ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output";
        String compiled = outputPath + "\\Compiled\\Java";
        String failed = outputPath + "\\Error\\Java";
        String withoutExtension = file.getName().substring(0, file.getName().lastIndexOf("."));

        ProcessBuilder javacProcessBuilder = new ProcessBuilder();
        javacProcessBuilder.directory(file.getParentFile());
        javacProcessBuilder.command(javacPath, file.getName(), "-d", compiled);
        javacProcessBuilder.redirectError(new File(failed + "\\" + file.getName() + ".error"));

        int exitCode;

        try{
            Process javacProcess = javacProcessBuilder.start();
            exitCode = javacProcess.waitFor();

            if(exitCode == 0) {
                File error = new File(failed + "\\" +file.getName() + ".error");
                error.delete();

                CompilerCompo.lastsuccessFile = new File(compiled + "\\" + withoutExtension + ".class");
                System.out.println(CompilerCompo.lastsuccessFile.getAbsolutePath());
                return true;
            } else {
                CompilerCompo.lastFailedFile = new File(failed + "\\" + file.getName() + ".error");//실패 성공 스트링 매개변수로 전달
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean CompileJavaPackage(File file) {
        return true;
    }

    public boolean CompileAuto(File file) {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if(extension.equals("java"))
            return CompileJavaClass(file);
        else if(extension.equals("c"))
            return CompileC(file);
        else
            return false;
    }
}