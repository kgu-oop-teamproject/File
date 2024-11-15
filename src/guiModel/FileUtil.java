package guiModel;

import java.util.ArrayList;

import java.io.File;
/**
 *  ide에서 파일에 대한 기능들을 제공
 *  열린 파일들을 추적
 */
public class FileUtil {
    public static void addActivatedFile(File file) {
        activatedFile.add(file);
    }


    private static ArrayList<File> activatedFile = new ArrayList<File>();
}
