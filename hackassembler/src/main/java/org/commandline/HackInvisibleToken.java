package org.commandline;

public class HackInvisibleToken extends HackStandardToken {

    public HackInvisibleToken(String rawLine, String tokenValue, int labelPosition) {
        super(rawLine, tokenValue, labelPosition);
    }

    @Override
    public String toHack() {
        return String.format("%1$" + 16 + "s", " ");
    }
}
