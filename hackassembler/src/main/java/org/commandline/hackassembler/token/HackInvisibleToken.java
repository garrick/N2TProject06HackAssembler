package org.commandline.hackassembler.token;

public class HackInvisibleToken extends HackStandardToken {

    public HackInvisibleToken(String rawLine, String tokenValue, int labelPosition) {
        super(rawLine, tokenValue, labelPosition);
    }

    @Override
    public String toHack() {
        return "";
    }
}
