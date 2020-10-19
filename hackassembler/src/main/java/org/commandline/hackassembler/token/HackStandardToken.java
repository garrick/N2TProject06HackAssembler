package org.commandline;

public abstract class HackStandardToken implements HackToken {

    private String rawLine;
    private String tokenValue;
    private int labelPosition;

    public HackStandardToken(String rawLine, String tokenValue, int labelPosition) {

        this.rawLine = rawLine;
        this.tokenValue = tokenValue;
        this.labelPosition = labelPosition;
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
    public int getPosition() {
        return labelPosition;
    }

    @Override
    public String toString() {
        return toHack();
    }

    @Override
    public void updateSymbols(UserSymbolTable ust) {
    }
}
