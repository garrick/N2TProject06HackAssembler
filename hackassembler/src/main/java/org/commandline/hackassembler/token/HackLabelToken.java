package org.commandline;

public class HackLabelToken extends HackInvisibleToken {

    public HackLabelToken(String rawLine, String tokenValue, int labelPosition) {
        super(rawLine, tokenValue, labelPosition);
    }

    @Override
    public int getPosition() {
        return super.getPosition();
    }
}
