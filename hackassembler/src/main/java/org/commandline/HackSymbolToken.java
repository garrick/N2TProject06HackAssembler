package org.commandline;

public class HackSymbolToken extends HackStandardToken implements SymbolValue, HackExecutableToken {

    private int symbolTableLocation = -1;

    public HackSymbolToken(String rawLine, String tokenValue, int labelPosition) {
        super(rawLine, tokenValue, labelPosition);
    }

    @Override
    public String toHack() {
        return "@"+this.symbolTableLocation;
    }

    @Override
    public void setSymbolTableLocation(int location) {
        this.symbolTableLocation = location;
    }

    @Override
    public int getSymbolTableLocation() {
        return symbolTableLocation;
    }
}
