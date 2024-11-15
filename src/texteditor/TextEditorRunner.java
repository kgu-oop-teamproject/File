package texteditor;

import java.io.*;
import java.util.LinkedList;

public class TextEditorRunner {

    public LinkedList<String> readFile(File file) {
        String line = null;
        LinkedList<String> list = new LinkedList<>();

        try {
            BufferedReader fileBuffer = new BufferedReader(new FileReader(file));
            while ((line = fileBuffer.readLine()) != null) {
            list.add(line);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return list;
    }
}