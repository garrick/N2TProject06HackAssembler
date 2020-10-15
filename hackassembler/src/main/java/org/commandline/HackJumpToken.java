package org.commandline;

public class HackJumpToken extends HackStandardToken {
    public HackJumpToken(String rawLine, String tokenValue) {
        super(rawLine, tokenValue);
    }

    @Override
    public String toHack() {
        return "FIXME";
    }
}
