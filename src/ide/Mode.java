package ide;

/**
 * Mode are divided two modes, running mode and viewing mode.
 * running mode means IDE Component can execute function and can receive from user
 * viewing mode means that user only viewing the procedure of function or guide statements.
 */
public enum Mode {

    indNOTFILE (0x11),
    indHAVEFILE(0x12),

    indERROR(0x1D),
    indHELP(0x1E),
    indVER(0x1F),

    fileNOTFILE(0x21),
    fileHAVESEL(0x22),
    fileHAVEUP(0x23),
    fileHAVEUPSEL(0x24),

    fileLIST(0x25),
    fileSEL(0x26),

    fileERROR(0x2D),
    fileHELP(0x2E),
    fileVER(0x2F),

    CompileC(0x31),
    compileJAVA(0x32),
    compileSIC(0x33),

    compileERROR(0x3D),
    compileHELP(0x3E),
    compileVER(0x3F),
    ;

    Mode (int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    private final int value;
}