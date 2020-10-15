package org.commandline;

public class HackValueToken extends HackStandardToken{
    public HackValueToken(String rawLine, String tokenValue) {
        super(rawLine, tokenValue);
    }

    @Override
    public String toHack() {
        return DecimalToBinaryConverter.convert16(Integer.parseInt(this.getTokenValue().substring(1)));
    }
}
