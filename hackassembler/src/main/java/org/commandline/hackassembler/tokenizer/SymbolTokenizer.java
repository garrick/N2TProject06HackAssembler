package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.table.BuiltInSymbolTable;
import org.commandline.hackassembler.table.UserLabelTable;
import org.commandline.hackassembler.token.HackSymbolToken;
import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.token.HackValueToken;

public class SymbolTokenizer extends HackTokenizer {
    public static String PATTERN = "(\\@([a-zA-Z0-9.$_]+))";
    private final UserLabelTable labelPositions;

    public SymbolTokenizer(HackTokenizer nextTokenizer, UserLabelTable labelPositions) {
        super(nextTokenizer);
        this.labelPositions = labelPositions;
    }

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        int builtInSymbol = BuiltInSymbolTable.getOrDefault(sanitizedLine.substring(1), -1);
        if (builtInSymbol != -1)
            return new HackValueToken(rawLine, "@" + builtInSymbol, position);
        return new HackSymbolToken(rawLine, sanitizedLine, position, labelPositions);
    }
}
