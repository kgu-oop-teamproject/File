package guiModel;

import java.io.BufferedReader;
import java.io.*;

public class Error {
    public String errorRead(Process process) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            StringBuilder errorBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                errorBuilder.append(line).append("\n");
            }

            return  errorBuilder.toString();
        }
        catch (IOException e) {
            Compiler.errorFlag = 1;
            return "Failed to read Error:\n" + e.getMessage();
        }
    }
}
