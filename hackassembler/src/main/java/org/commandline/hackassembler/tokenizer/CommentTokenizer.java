package org.commandline.hackassembler.tokenizer;

import org.commandline.hackassembler.token.HackCommentToken;
import org.commandline.hackassembler.token.HackToken;

public class CommentTokenizer extends HackTokenizer {

    public CommentTokenizer(HackTokenizer nextTokenizer) {
        super(nextTokenizer);
    }

    @Override
    protected boolean matchesPattern(String toMatch) {
        return toMatch == null || toMatch.isBlank();
    }

    @Override
    protected String getPattern() {
        throw new UnsupportedOperationException("We shouldn't be using a pattern!");
    }

    protected HackToken buildHackToken(String rawLine, String sanitizedLine, int position) {
        return new HackCommentToken(rawLine, position);
    }
}
