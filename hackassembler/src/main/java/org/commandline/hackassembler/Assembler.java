package org.commandline;

import org.commandline.hackassembler.token.HackToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assembler {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/" + args[0];
        boolean debug = false;
        if (args.length == 2 && "--debug".equals(args[1])) {
            debug = true;
        }
        BufferedReader reader;
        List<String> fileLines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
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
        HackParser parser = new HackParser();
        List<HackToken> hackTokens = parser.secondPass(parser.firstPass(fileLines));
        final boolean finalDebug = debug;
        if (finalDebug) {
            parser.dumpLabels();
            debugHeader();
        }
        hackTokens.forEach((token) -> {
            if (finalDebug) {
                debugOut(token);
            } else {
                String hackValue = token.toHack();
                if (!hackValue.isBlank()) System.out.println(hackValue);
            }
        });
    }

    private static void debugHeader() {
        String tokenName = String.format("%1$" + 24 + "s", "Token Name");
        String tokenPosition = String.format("%1$" + 8 + "s", "Position");
        System.out.println(tokenName + "\t" + "Token ASM" + "\t\t" + tokenPosition + "\t" + "Token Value" + "\tToken Raw");
        System.out.println(String.format("%1$" + 120 + "s", " ").replace(' ', '='));
    }

    private static void debugOut(HackToken token) {
        String tokenName = String.format("%1$" + 24 + "s", token.getClass().getSimpleName());
        String tokenPosition = String.format("%1$" + 8 + "s", token.getPosition());
        String hackValue = token.toHack();
        System.out.println(tokenName + "\t" + (hackValue.isBlank() ? "\t\t" : hackValue)
                + "\t" + tokenPosition + "\t" + token.getTokenValue() + "\t\t" + token.getRawValue());

    }
}
