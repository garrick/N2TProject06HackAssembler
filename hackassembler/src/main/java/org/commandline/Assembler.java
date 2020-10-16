package org.commandline;

import java.io.*;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;
import java.util.List;

public class Assembler {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"/" + args[0];
        boolean debug = false;
        if(args.length == 2 && "--debug".equals(args[1])){
            debug = true;
        }
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
        List<HackToken> hackTokens = parser.firstPass(fileLines);
        final boolean finalDebug = debug;
        hackTokens.forEach((token) -> {
            if(finalDebug) {
                System.out.println(token.getClass().getSimpleName()+"\t\t\t"+token.getPosition() + "\t\t" + token.getTokenValue()+"\\t"+token.getRawValue());
            } else {
                if(token.getTokenValue() != null) System.out.println(token);
            }
        });
    }
}
