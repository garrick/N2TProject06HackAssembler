package org.commandline.hackassembler.token;

import org.commandline.hackassembler.table.InstructionTables;

public class HackAssignmentToken extends HackStandardToken implements HackExecutableToken {
    private static final String INSTRUCTION_BITS = "111";

    public HackAssignmentToken(String rawValue, String tokenValue, int position) {
        super(rawValue, tokenValue, position);
    }


    @Override
    public String toHack() {
        StringBuilder hackOut = new StringBuilder(16);
        hackOut.append(INSTRUCTION_BITS); //First 3 bytes
        String tokenValue = getTokenValue().replaceAll("\\s", "");
        String[] fragments = tokenValue.split("=");

        String comp = fragments[1];
        if (InstructionTables.mapAOneCInstruction(comp, "NOTAONE").equals("NOTAONE")) {
            hackOut.append("0"); //Zero A
            hackOut.append(InstructionTables.mapAZeroCInstruction(comp, ""));
        } else {
            hackOut.append("1");
            hackOut.append(InstructionTables.mapAOneCInstruction(comp, ""));
        }
        String dest = fragments[0];
        hackOut.append(InstructionTables.mapDestinationBits(dest, "000"));
        return hackOut.toString() + "000";  //Zero jump bits
    }
}
