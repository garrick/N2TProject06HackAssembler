package org.commandline;

import java.util.HashMap;

public class HackAssignmentToken extends HackStandardToken
{
    public HackAssignmentToken(String rawValue, String tokenValue) {
        super(rawValue, tokenValue);
    }

    private String instrBits = "111";



    @Override
    public String toHack() {
        StringBuffer hackOut = new StringBuffer(16);
        hackOut.append("111"); //First 3 bytes
        String tokenValue = getTokenValue().replaceAll("\\s", "");
        String[] fragments = tokenValue.split("\\=");

        String comp = fragments[1];
        if(InstructionTables.mapAOneCInstruction(comp, "NOTAONE").equals("NOTAONE")) {
            hackOut.append("0"); //Zero A
            hackOut.append(InstructionTables.mapAZeroCInstruction(comp,""));
        } else {
            hackOut.append("1");
            hackOut.append(InstructionTables.mapAOneCInstruction(comp, ""));
        }
        String dest = fragments[0];
        hackOut.append(InstructionTables.mapDestinationBits(dest, "000"));
        return hackOut.toString() + "000";  //Zero jump bits
    }
}
