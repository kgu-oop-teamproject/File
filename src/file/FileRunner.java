package file;

import java.io.File;

public class FileRunner {

    public void runFileSearcher() {
        if(fileSearcher == null) {
            fileSearcher = new File(startPoint);
        }
    }

    /**
     *
     * @return
     */
    public File[] getListofFile() {
        if(fileSearcher.isDirectory()) {
            listOfFiles = fileSearcher.listFiles();
            return fileSearcher.listFiles();
        } else {
            return null;
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public File selectFile(String fileName) {
        File file = new File(fileName);
        return file;
    }

    /**
     *
     * @param fileName
     * @return
     */
    public File selectFileOfList(String fileName) {
        File file = new File(startPoint + "\\"+ fileName);
        if(file.isFile()){
            return file;
        } else {
            return null;
        }
    }

    public void goChildFolderOfList(String fileName) {
        File tmp = new File(startPoint + "\\" + fileName);
        if(tmp.isDirectory()){
            startPoint = tmp.getAbsolutePath();
            fileSearcher = new File(startPoint);
        }
    }

    public void goParentFolderOfList() {
        startPoint = fileSearcher.getParentFile().getAbsolutePath();
        fileSearcher = new File(startPoint);
    }

    public String startPoint = null;
    private File fileSearcher = null;
    private File[] listOfFiles = null;
}