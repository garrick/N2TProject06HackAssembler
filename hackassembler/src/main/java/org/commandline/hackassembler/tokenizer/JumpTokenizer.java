package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.token.HackJumpToken;
import org.commandline.hackassembler.token.HackToken;

public class JumpTokenizer extends HackTokenizer {
    public static final String PATTERN = ".*;.*";

    public JumpTokenizer(HackTokenizer nextTokenizer) {
        super(nextTokenizer);
    }

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        return new HackJumpToken(rawLine, sanitizedLine, position);
    }
}
