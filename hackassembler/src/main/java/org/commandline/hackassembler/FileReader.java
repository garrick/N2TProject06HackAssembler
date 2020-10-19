package org.commandline.hackassembler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<String> readSourceLines(String filePath) {
        List<String> fileLines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines;
    }
}
