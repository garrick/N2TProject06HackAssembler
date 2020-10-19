package org.commandline;

public class HackSymbolToken extends HackStandardToken implements HackExecutableToken {

    private int symbolTableLocation = -1;
    private UserLabelTable labelPositions;

    public HackSymbolToken(String rawLine, String tokenValue, int labelPosition, UserLabelTable labelPositions) {
        super(rawLine, tokenValue, labelPosition);
        this.labelPositions = labelPositions;
    }

    @Override
    public String toHack() {
        if (labelPositions.hasLabel(getTokenValue())) {
            return DecimalToBinaryConverter.convertAndPad(this.labelPositions.getPositionForLabel(getTokenValue()), 16);
        }
        if (symbolTableLocation == -1)
            return "@" + this.symbolTableLocation;
        return DecimalToBinaryConverter.convertAndPad(this.symbolTableLocation, 16);
    }

    @Override
    public void updateSymbols(UserSymbolTable ust) {
        if (!labelPositions.hasLabel(getTokenValue())) {
            symbolTableLocation = ust.getSymbolName(getTokenValue().substring(1));
        }
    }
}
