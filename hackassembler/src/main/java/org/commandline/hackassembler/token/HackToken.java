package org.commandline;

public interface HackToken {
    String getRawValue();

    String getTokenValue();

    String toHack();

    int getPosition();

    void updateSymbols(UserSymbolTable ust);
}
