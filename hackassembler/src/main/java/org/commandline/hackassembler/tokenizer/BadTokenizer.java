package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.token.HackBadToken;
import org.commandline.hackassembler.token.HackToken;

public class BadTokenizer extends HackTokenizer {
    public BadTokenizer() {
        super(null);
    }

    @Override
    protected String getPattern() {
        return ".*";
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        return new HackBadToken(rawLine, position);
    }
}
