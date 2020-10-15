package org.commandline;

public class HackCommentToken extends HackStandardToken {
    public HackCommentToken(String rawLine) {
        super(rawLine, null);
    }

    @Override
    public String toHack() {
        return "";
    }
}
