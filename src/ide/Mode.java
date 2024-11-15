package ide;

/**
 * Mode are divided two modes, running mode and viewing mode.
 * running mode means IDE Component can execute function and can receive from user
 * viewing mode means that user only viewing the procedure of function or guide statements.
 * reason using binary is only for comfort to read.
 */
public enum Mode {

    //index
    indNOFILE(0x11), indHAVEFILE(0x12),

    //view only.
    indERROR(0x1D),indHELP(0x1E),indVER(0x1F),

    /*####################################################*/

    //index
    fileNOFILE(0x21), fileHAVESEL(0x22), fileHAVEUP(0x23), fileHAVEUPSEL(0x24),

    //executing
    fileLIST(0x25), fileSEL(0x26), fileMAKE(0x27), fileDELETE(0x28),

    //view only.
    fileERROR(0x2D), fileHELP(0x2E), fileVER(0x2F),

    /*####################################################*/

    //index
    compileNOFILE(0x31), compileHAVEFILE(0x32),

    //executing
    compileAUTO(0x33), compileC(0x34), compileJAVA(0x35), compileSIC(0x36), compileERR(0x37),

    //view only
    compileNOTCOMPLETE(0x3B), compileCOMPLETE(0x3C), compileERROR(0x3D), compileHELP(0x3E), compileVER(0x3F),

    /*####################################################*/

    runNOFILE(0x41), runHAVEFILE(0x42),

    //executing
    runAUTO(0x43), runC(0x44), runJAVA(0x45), runSIC(0x46),
    //view only
    runERROR(0x4D),runHELP(0x4E),runVER(0x4F),

    /*####################################################*/

    //index
    managerHAVE(0x51),//

    //executing
    managerLIST(0x52), managerCHECK(0x53), managerINSTALLER(0x54), managerSETPATH(0x55),//

    //view only
    managerERROR(0x5D), managerHELP(0x5E), managerVER(0x5F),

    /*####################################################*/

    //index
    textHAVE(0x61), textNOFILE(0x62),

    //executing
    textREAD(0x63), textWRITE(0x64), textBOTH(0x65),

    //view only
    textERROR(0x6D), textHELP(0x6E),textVER(0x6F)

    /*####################################################*/;

    Mode (int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    private final int value;
}