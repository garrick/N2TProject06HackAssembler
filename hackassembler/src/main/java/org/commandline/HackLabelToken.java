package org.commandline;

public class HackLabelToken extends HackStandardToken {

    public HackLabelToken(String rawLine, String tokenValue, int labelPosition) {
        super(rawLine, tokenValue, labelPosition);
    }

    @Override
    public String toHack() {
        return "";
    }

    @Override
    public int getPosition() {
        return super.getPosition()+1;
    }
}
