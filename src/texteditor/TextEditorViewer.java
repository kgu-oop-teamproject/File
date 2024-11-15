package texteditor;

import java.util.Iterator;
import java.util.LinkedList;

public class TextEditorViewer {
    public void showReadingFile(LinkedList<String> file) {
        Iterator<String> line = file.iterator();

        System.out.println("##############################");
        while (line.hasNext()) {
            System.out.println("| " + line.next());
        }
        System.out.println("############END OF FILE###########");
    }
}