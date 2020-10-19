package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackAssignmentToken;
import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.tokenizer.HackTokenizer;

public class AssignmentTokenizer extends HackTokenizer {
    public static final String PATTERN = ".*=.*";

    public AssignmentTokenizer(HackTokenizer nextTokenizer) {
        super(nextTokenizer);
    }

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        return new HackAssignmentToken(rawLine, sanitizedLine, position);
    }
}
