package org.commandline.hackassembler.token;

import org.commandline.hackassembler.table.UserSymbolTable;
import org.commandline.hackassembler.util.DebugFlag;

import java.io.PrintStream;

public abstract class HackStandardToken implements HackToken {

    private String rawLine;
    private String tokenValue;
    private int labelPosition;

    public HackStandardToken(String rawLine, String tokenValue, int labelPosition) {

        this.rawLine = rawLine;
        this.tokenValue = tokenValue;
        this.labelPosition = labelPosition;
    }

    @Override
    public String getRawValue() {
        return rawLine;
    }

    @Override
    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toHack() {
        return null;
    }

    @Override
    public int getPosition() {
        return labelPosition;
    }

    @Override
    public String toString() {
        return toHack();
    }

    @Override
    public void updateSymbols(UserSymbolTable ust) {
    }

    @Override
    public void renderTo(PrintStream outputStream) {
        String hackOutput = toHack();
        if(DebugFlag.isOn()) {
            DebugFlag.debugOut(this);
        } else {
            if(hackOutput != null && !hackOutput.isBlank())outputStream.println(this.toHack());
        }
    }
}
