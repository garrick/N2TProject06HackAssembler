package org.commandline;

public class HackCommentToken extends HackStandardToken {
    public HackCommentToken(String rawLine, int position) {
        super(rawLine, null, position);
    }

    @Override
    public String toHack() {
        return "";
    }
}
