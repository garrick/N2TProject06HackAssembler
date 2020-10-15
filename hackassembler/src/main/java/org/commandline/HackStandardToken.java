package org.commandline;

public abstract class HackStandardToken implements HackToken{

    private String rawLine;
    private String tokenValue;

    public HackStandardToken(String rawLine, String tokenValue) {

        this.rawLine = rawLine;
        this.tokenValue = tokenValue;
    }

    @Override
    public String getRawValue() {
        return rawLine;
    }

    @Override
    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toHack() {
        return null;
    }

    @Override
    public String toString() {
        return toHack();
    }
}
