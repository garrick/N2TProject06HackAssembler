package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.token.HackToken;
import org.commandline.hackassembler.token.HackValueToken;

public class ValueTokenizer extends HackTokenizer {

    public static final String VALUE_PATTERN = "(\\@\\d*)";

    public ValueTokenizer(HackTokenizer nextTokenizer) {
        super(nextTokenizer);
    }

    @Override
    protected String getPattern() {
        return VALUE_PATTERN;
    }

    @Override
    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        return new HackValueToken(rawLine, sanitizedLine, position);
    }
}