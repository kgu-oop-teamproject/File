package runner;

import file.FileCompo;
import ide.IDEComponent;
import ide.Mode;

import java.io.File;

public class RunnerCompo extends IDEComponent {

    File runfile = FileCompo.getUploadedFile(); //파일
    public static int errorFlag; //컴파일 에러 났거나 컴파일 안돌렸거나
    public RunnerRunner runnerRunner = new RunnerRunner(runfile);
    public RunnerViewer runnerViewer = new RunnerViewer();

    public RunnerCompo() {
        if(runfile == null){
            setMode(Mode.runnerNOTHAVE); //0x51
        }else if(errorFlag == 0) {
            setMode(Mode.runnerHAVE); //0x52
        }else {
            setMode(Mode.runnerCompileError); //0x53 파일이 새로 올려진 상태면 자동으로 errorFlag 올라가야 함
        }
    }
    //Cmd 에서 엑시트 코드 받아올수 있음
    //컴파일 쪽에서 엑시트코드 받아다가 써먹으면 될듯
    //exitCode = process.waitFor();

    public void executeComponent(){
        switch (mode.getValue()) {
            case 0x52: {
                break;
            }
        }
    }

    public void showComponent() {
        switch (mode.getValue()) {
            case 0x51: {
                runnerViewer.runnerFinished(); break;
            }
            case 0x52: {
                runnerViewer.runnerNoFile(); break;
            }
            case 0x53: {
                runnerViewer.runnerCompileError(); break;
            }
            case 0x54: {
                runnerViewer.runnerRunTimeError(); break;
            }
        }
    }

    public void setMode(Mode m){

    }
}