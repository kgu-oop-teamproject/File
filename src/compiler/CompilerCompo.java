package compiler;

import file.FileCompo;
import ide.IDEComponent;
import ide.Mode;

import java.io.File;

public class CompilerCompo extends IDEComponent {
    private File compileFile; // 컴파일할 파일
    public CompilerRunner compilerRunner; // 컴파일 작업을 수행하는 객체
    public CompilerViewer compilerViewer; // 사용자 인터페이스 출력을 관리하는 객체

    // 기본 생성자 - 컴파일 파일 없이 객체 초기화
//    public CompilerCompo() {
//        this(null);
//    }

    // 컴파일할 파일을 인자로 받는 생성자
    // 컴파일 파일이 있을 경우 mode를 compileHAVE로, 없을 경우 compileNOTHAVE로 설정
    public CompilerCompo(File compileFile) {
        this.compileFile = compileFile;
        if (compileFile != null) {
            setMode(Mode.compileHAVE);
        } else {
            setMode(Mode.compileNOTHAVE);
        }
        compilerRunner = new CompilerRunner();
        compilerViewer = new CompilerViewer();
    }

    // 컴포넌트를 실행하는 메서드로, 현재 mode에 따라 컴파일 작업을 수행
    @Override
    public void executeComponent() {
        // compileHAVE 모드일 경우 컴파일 파일이 존재하면 컴파일 수행
        if (mode == Mode.compileHAVE && compileFile != null) {
            boolean success = compilerRunner.runCompiler(compileFile, mode);
            if (!success) {
                // 컴파일 실패 시 compileERROR 모드로 변경하고 파일 목록 표시
                setMode(Mode.compileERROR);
                compilerViewer.showFileList();
            }
        } else {
            // 파일이 없을 때 compileNOTHAVE 모드로 설정하고 파일 목록 표시
            setMode(Mode.compileNOTHAVE);
            compilerViewer.showFileList();
        }
    }

    // 컴포넌트를 화면에 표시하는 메서드
    @Override
    public void showComponent() {
        if (compileFile != null) {
            // 컴파일 파일이 있을 경우 파일명과 경로 정보를 포함한 컴파일 목록을 출력
            compilerViewer.showCompileList(compileFile.getName(), compileFile.getParent());
        } else {
            // 컴파일 파일이 없을 경우 파일 목록을 표시
            compilerViewer.showFileList();
        }
    }

    // 현재 모드를 설정하는 메서드
    @Override
    public void setMode(Mode m) {
        mode = m;
    }

    // 현재 모드에 따라 컴파일러의 버전 또는 도움말을 표시하는 메서드
    public void showCompile() {
        switch (mode.getValue()) {
            case 0x3F:
                // 버전을 요청하는 명령일 때
                showVersion(new String[]{});
                break;
            case 0x2E:
                // 도움말 요청 명령일 때
                requestHelp();
                break;
        }
    }

    // 컴파일할 파일을 설정하고 mode를 변경하는 메서드
    public void setCompileFile(File file) {
        this.compileFile = file;
        setMode(file != null ? Mode.compileHAVE : Mode.compileNOTHAVE);
    }

    // 도움말을 요청하는 메서드로, 사용자에게 컴파일러 사용법 안내를 표시
    public void requestHelp() {
        compilerViewer.showManual();
        // System.out.println("도움말: 컴파일러 사용법에 대한 안내...");
    }

    // 컴파일러 버전을 표시하는 메서드로, 버전 정보를 포함한 메시지를 출력
    public void showVersion(String[] name) {
        compilerViewer.showCompileC(name);
        // System.out.println("컴파일러 버전: v1.0");
    }
}
