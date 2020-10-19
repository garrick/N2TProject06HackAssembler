package org.commandline;

public class HackValueToken extends HackStandardToken implements HackExecutableToken {
    public HackValueToken(String rawLine, String tokenValue, int position) {
        super(rawLine, tokenValue, position);
    }

    @Override
    public String toHack() {
        return DecimalToBinaryConverter.convert16(Integer.parseInt(this.getTokenValue().substring(1)));
    }
}
