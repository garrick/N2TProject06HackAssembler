package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.token.HackToken;

public abstract class HackTokenizer {


    protected HackTokenizer nextTokenizer;

    public HackTokenizer(HackTokenizer nextTokenizer) {
        this.nextTokenizer = nextTokenizer;
    }
    public HackToken tokenize(String rawLine, String sanitizedLine, int position) {
        if (matchesPattern(sanitizedLine)) return buildHackToken(rawLine, sanitizedLine, position);
        return nextTokenizer.tokenize(rawLine, sanitizedLine, position);
    }

    protected boolean matchesPattern(String toMatch) {
        return toMatch.matches(getPattern());
    }

    protected abstract String getPattern();

    protected abstract HackToken buildHackToken(String rawLine, String sanitizedLine, int position);
}
