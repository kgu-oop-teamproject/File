package file;

import java.io.File;

public class FileViewer {
    public FileViewer() {

    }

    public void showFileEmpty(String sp) {
        System.out.println("##############################");
        System.out.println("Searching and Make File at : " + sp);
        System.out.println("no uploaded or selected file");
        System.out.println("1. View File List");
        System.out.println("2. Exit File Searcher");
    }

    public void showFileUP(String sp, File uploadedFile) {
        System.out.println("##############################");
        System.out.println("Searching and Make File at : " + sp);
        System.out.println("Uploaded File : " + uploadedFile.getName());
        System.out.println("1. View File List");
        System.out.println("2. Unload File : " + uploadedFile.getName());
        System.out.println("3. Make new File");
        System.out.println("4. Exit File Searcher");
    }

    public void showFileSelected(String sp, File selectedFile) {
        System.out.println("##############################");
        System.out.println("Searching and Make File at : " + sp);
        System.out.println("Selected File : " + selectedFile.getName());
        System.out.println("1. View File List");
        System.out.println("2. Upload File");
        System.out.println("3. Delete File : " + selectedFile.getName());
        System.out.println("4. Read File : " + selectedFile.getName());
        System.out.println("5. Make new File");
        System.out.println("6. Exit File Searcher");
    }

    public void showFileUPSelected(String sp, File selectedFile, File uploadedFile) {
        System.out.println("##############################");
        System.out.println("Searching and Make File at : " + sp);
        System.out.println("Selected File : " + selectedFile.getName());
        System.out.println("Uploaded File : " + uploadedFile.getName());
        System.out.println("1. View File List");
        System.out.println("2. Unload File : " + uploadedFile.getName());
        System.out.println("3. Delete File : " + selectedFile.getName());
        System.out.println("4. Read File : " + selectedFile.getName());
        System.out.println("5. Make new File");
        System.out.println("6. Exit File Searcher");
    }

    public void showListFiles(String sp, File[] child) {
        int i = 0;
        System.out.println("##############################");
        System.out.println(sp + " have...");
        if(child != null) {
            for (File file : child) {
                i++;
                System.out.println(i + " -> " + file.getName());
            }
            System.out.println("\n1. Back to File Searcher");
        } else {
            System.out.println("No files found");
            System.out.println("1. Back to File Searcher");
        }
    }

    public void showHavingErrorFile() {
        System.out.println("##############################");
        System.out.println("have Err");
    }

    public void showManual() {
        System.out.println("##############################");
        System.out.println("you can input 1 - 9 for select menu");
        System.out.println("you can input word {\"help, version, settings, back, exit, select\"}");
        System.out.println("**help** used itself ++ you can see the manual of index");
        System.out.println("**version** used itself ++ you can see the history of versions");
        System.out.println("**settings** used itself ++ you can enter IDE Settings, likewise you can also put number 6");
        System.out.println("**back** used itself ++ when you are in **help**, **version**, **file not founded**, you can comeback to File Searcher");
        System.out.println("**exit** used itself ++ when exit File System");
        System.out.println("**select** used with -[option] ++ when you are seeing List of Files, not allow number command except 1");
        System.out.println("**list** used itself ++ you can enter File Searcher, likewise you can also put number 1");
        System.out.println("    select-[fileName] ex {select-test.txt} ++ select File");
        System.out.println("    select-.. ++ go to Parent Directory");
        System.out.println("    select-[folderName] ex {select-ChildFolder} -- go to Child Directory");
    }

    public void showVersion() {
        System.out.println("##############################");
        System.out.println("File version 0.1.2 @2024.10.10-");
        System.out.println("0.1.1 set Enum of FileSystem and add Viewer by 201812478");
        System.out.println("0.1.2 make file select function by 201812478");
    }
}