package ide;

/**
 * Mode are divided two modes, running mode and viewing mode.
 * running mode means IDE Component can execute function and can receive from user
 * viewing mode means that user only viewing the procedure of function or guide statements.
 * reason using binary is only for comfort to read.
 */
public enum Mode {

    //run smt or call another Component or End program. main roles are providing index and limiting options.
    indNOFILE(0x11),//first state of IDE, it means IDE don't have a File.
    indHAVEFILE(0x12),//if Source code or executable file is uploaded.

    //view only.
    indERROR(0x1D),//show ErrorCode of index's Error
    indHELP(0x1E),
    indVER(0x1F),

    /*####################################################*/

    //run smt or call another Component or End File Component. main roles are providing index and limiting options.
    fileNOFILE(0x21),//call FileComponent first time, didn't select and upload file
    fileHAVESEL(0x22),//file is selected
    fileHAVEUP(0x23),//file is uploaded
    fileHAVEUPSEL(0x24),//file is selected and uploaded, can run functions about both files

    //Component executing
    fileLIST(0x25),//get file List of Folder. able to go back mode:21-24
    fileSEL(0x26),//select file, it can be changed from fileList,
    fileMAKE(0x27),//
    fileDELETE(0x28),//delete selected file.

    //view only.
    fileERROR(0x2D),
    fileHELP(0x2E),
    fileVER(0x2F),

    /*####################################################*/

    //run smt or call another Component or End program. main roles are providing index and limiting options.
    compileNOFILE(0x31),//for Compiling source, need to call File Component and upload file.
    compileHAVEFILE(0x32),//choose Compiler

    //executing
    compileAUTO(0x33),
    compileC(0x34),
    compileJAVA(0x35),
    compileSIC(0x36),
    compileERR(0x37),

    compileERROR(0x3D),
    compileHELP(0x3E),
    compileVER(0x3F),

    /*####################################################*/

    runNOFILE(0x40),
    runHAVEFILE(0x41),

    //executing
    runAUTO(0x42),
    runC(0x43),
    runJAVA(0x44),
    runSIC(0x45),
    runERR(0x46),


    //view only
    runERROR(0x4D),
    runHELP(0x4E),
    runVER(0x4F),

    /*####################################################*/

    //check setting File
    managerNOTHAVE(0x51),//run installer
    managerHAVE(0x52),//

    //executing
    managerSETPATH(0x53),//


    managerERROR(0x5D),
    managerHELP(0x5E),
    managerVER(0x5F),
    ;

    /*####################################################*/

    Mode (int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    private final int value;
}