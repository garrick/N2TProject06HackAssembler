package org.commandline.hackassembler.token;

public class HackCommentToken extends HackInvisibleToken {
    public HackCommentToken(String rawLine, int position) {
        super(rawLine, null, position);
    }
}
