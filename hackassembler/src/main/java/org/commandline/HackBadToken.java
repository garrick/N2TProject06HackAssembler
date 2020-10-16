package org.commandline;

public class HackBadToken extends HackStandardToken
{
    public HackBadToken(String rawValue, int position) {
        super(rawValue, null, position);
    }
}
