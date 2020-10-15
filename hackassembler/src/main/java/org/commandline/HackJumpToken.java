package org.commandline;

import java.util.HashMap;

public class HackJumpToken extends HackStandardToken {




    public HackJumpToken(String rawLine, String tokenValue) {
        super(rawLine, tokenValue);
    }

    @Override
    public String toHack() {
        StringBuffer hackOut = new StringBuffer(16);
        hackOut.append("1110"); //First 4 bytes
        String tokenValue = getTokenValue().replaceAll("\\s", "");
        String[] fragments = tokenValue.split("\\;");

        String comp = fragments[0];
        hackOut.append(InstructionTables.mapAZeroCInstruction(comp, "BAD"));
        hackOut.append("000"); //No Destination
        String jump = fragments[1];
        hackOut.append(InstructionTables.mapJumpBits(jump, "000"));
        return hackOut.toString();
    }
}
