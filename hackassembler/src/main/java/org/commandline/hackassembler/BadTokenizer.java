package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackBadToken;
import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.tokenizer.HackTokenizer;

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
