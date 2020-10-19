package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackLabelToken;
import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.tokenizer.HackTokenizer;

import java.util.ArrayList;

public class LabelTokenizer extends HackTokenizer{
    public static final String PATTERN = "\\(([a-zA-Z0-9.$_]+)\\)";
    private final ArrayList<HackLabelToken> lastOpenLabel;

    public LabelTokenizer(HackTokenizer nextTokenizer, ArrayList<HackLabelToken> lastOpenLabel) {
        super(nextTokenizer);
        this.lastOpenLabel = lastOpenLabel;
    }


    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        HackLabelToken token = new HackLabelToken(rawLine, sanitizedLine, position);
        lastOpenLabel.add(token);
        return token;
    }
}
