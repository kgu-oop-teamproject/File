package file;

import java.util.ArrayList;
import java.io.File;

public class FileRunner {

    public void runFileSearcher() {
        if(fileSearcher == null) {
            fileSearcher = new File(startPoint);
        }
    }

    /**
     * @return array of child files except hidden files.
     */
    public File[] getListofFile() {
        if(fileSearcher.isDirectory()) {
            listOfFiles = fileSearcher.listFiles();
            ArrayList<File> notHiddenFiles = new ArrayList<>();

            for(File file : listOfFiles) {
                if(!file.isHidden()){
                    notHiddenFiles.add(file);
                }
            }

            listOfFiles = notHiddenFiles.toArray(new File[notHiddenFiles.size()]);

            return listOfFiles;
        } else {
            return null;
        }
    }

    /**
     * @param fileName is file to select.
     * @return selected file.
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

    public void deleteFile(File file) {
        file.delete();
    }

    public String startPoint = null;
    private File fileSearcher = null;
    private File[] listOfFiles = null;
}