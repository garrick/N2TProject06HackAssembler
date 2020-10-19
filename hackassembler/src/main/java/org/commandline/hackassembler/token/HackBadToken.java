package org.commandline.hackassembler.token;

public class HackBadToken extends HackInvisibleToken {
    public HackBadToken(String rawValue, int position) {
        super(rawValue, null, position);
    }
}
