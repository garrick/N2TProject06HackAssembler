package org.commandline;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Assembler {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"/" + args[0];
        BufferedReader reader;
        List<String> fileLines = new ArrayList<>();
        try{
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while(line != null) {
                fileLines.add(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HackParser parser = new HackParser();
        List<HackToken> hackTokens = parser.tokenize(fileLines);
        hackTokens.forEach((token) -> {
            if(token.getTokenValue() != null) System.out.println(token);
        });
    }
}
