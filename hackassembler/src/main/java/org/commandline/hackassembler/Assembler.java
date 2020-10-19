package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.util.DebugFlag;

import java.util.List;

public class Assembler {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/" + args[0];
        DebugFlag.setFlag(args);
        final List<String> fileLines = new FileReader().readSourceLines(filePath);
        HackParser parser = new HackParser();
        List<HackToken> hackTokens = parser.parse(fileLines);
        parser.dumpParserInfo();
        DebugFlag.debugHeader();
        hackTokens.forEach((token) -> token.renderTo(System.out));
    }

}
