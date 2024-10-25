package compiler;

import ide.Mode;

import java.io.File;

import static ide.Mode.compileJAVA;
import static ide.Mode.compileSIC;

public class CompilerRunner {
    private CompilerViewer compilerViewer = new CompilerViewer(); // 메시지 출력을 담당하는 뷰어 인스턴스

    // Java 파일을 컴파일하는 메서드
    // compileJava 메서드는 전달된 Java 소스 파일을 javac 명령어로 컴파일합니다.
    public static boolean compileJava(File compileFile, String[] args) {
        // javac 명령어를 사용해 Java 파일을 컴파일하는 ProcessBuilder 생성
        ProcessBuilder compilerBuilder = new ProcessBuilder("javac", compileFile.getAbsolutePath());
        compilerBuilder.redirectErrorStream(true); // 표준 출력과 오류를 하나의 스트림으로 결합

        try {
            // 컴파일 프로세스를 시작하고, 그 출력 결과를 System.out으로 전송
            Process compilerProcess = compilerBuilder.start();
            compilerProcess.getInputStream().transferTo(System.out); // 컴파일 결과를 콘솔에 출력

            // 프로세스 종료 코드 확인 (0: 성공, 그 외: 오류)
            int exitCode = compilerProcess.waitFor();
            return exitCode == 0;

        } catch (Exception e) {
            // 컴파일 오류 발생 시 에러 메시지를 출력하고 false 반환
            System.err.println("Java 컴파일 오류: " + e.getMessage());
            return false;
        }
    }

    // SIC 파일을 컴파일하는 메서드
    // compileSIC 메서드는 전달된 SIC 소스 파일을 sic-assembler 명령어로 컴파일합니다.
    public static boolean compileSIC(File compileFile) {
        // sic-assembler 명령어를 사용해 SIC 파일을  ProcessBuilder 생성
        ProcessBuilder compilerBuilder = new ProcessBuilder("sic-assembler", compileFile.getAbsolutePath()); // 가상의 SIC 컴파일러 명령어
        compilerBuilder.redirectErrorStream(true); // 표준 출력과 오류를 하나의 스트림으로 결합

        try {
            // 컴파일 프로세스를 시작하고, 그 출력 결과를 System.out으로 전송
            Process compilerProcess = compilerBuilder.start();
            compilerProcess.getInputStream().transferTo(System.out);

            // 프로세스 종료 코드 확인 (0: 성공, 그 외: 오류)
            int exitCode = compilerProcess.waitFor();
            return exitCode == 0;

        } catch (Exception e) {
            // 컴파일 오류 발생 시 에러 메시지를 출력하고 false 반환
            System.err.println("SIC 컴파일 오류: " + e.getMessage());
            return false;
        }
    }

    // 파일과 모드에 따라 Java 또는 SIC 컴파일을 수행하는 runCompiler 메서드
    // runCompiler 메서드는 컴파일할 파일과 모드를 입력받아, 모드에 따라 Java 또는 SIC 컴파일을 수행합니다.
    public boolean runCompiler(File compileFile, Mode mode) {
        // 파일 유효성 검사 - null이거나 존재하지 않으면 파일 목록 표시 후 false 반환
        if (compileFile == null || !compileFile.exists()) {
            compilerViewer.showFileList(); // 파일 목록을 표시하여 사용자가 파일을 확인할 수 있도록 함
            return false;
        }

        boolean success = false; // 컴파일 성공 여부를 나타내는 변수 초기화

        // 모드에 따라 컴파일 방식 선택
        switch (mode) {
            case compileJAVA:
                // Java 컴파일 모드일 경우 compileJava 메서드 호출
                success = compileJava(compileFile, new String[]{});
                break;

            case compileSIC:
                // SIC 컴파일 모드일 경우 compileSIC 메서드 호출
                success = compileSIC(compileFile);
                break;

            default:
                // 지원되지 않는 모드일 경우 에러 메시지 표시
                compilerViewer.showCompileError(compileFile.getName());
                success = false;
                break;
        }

        // 컴파일 결과에 따라 성공 또는 실패 메시지 출력
        if (success) {
            // 성공 시 컴파일 성공 메시지를 표시
            // compilerViewer.showCompileSuccess(compileFile.getName());  // 주석으로 남겨둠
        } else {
            // 실패 시 오류 메시지를 표시하고 오류 모드로 전환
            // compilerViewer.showCompileFailure(compileFile.getName());  // 주석으로 남겨둠
        }

        return success; // 컴파일 성공 여부 반환
    }
}
