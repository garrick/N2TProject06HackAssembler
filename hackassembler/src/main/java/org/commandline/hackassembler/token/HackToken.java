package org.commandline.hackassembler.token;

import org.commandline.hackassembler.table.UserSymbolTable;

import java.io.PrintStream;

public interface HackToken {
    String getRawValue();

    String getTokenValue();

    String toHack();

    int getPosition();

    void updateSymbols(UserSymbolTable ust);

    void renderTo(PrintStream outputStream);
}
