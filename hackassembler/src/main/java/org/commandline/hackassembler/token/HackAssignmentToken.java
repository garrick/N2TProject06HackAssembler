package org.commandline;

public class HackAssignmentToken extends HackStandardToken implements HackExecutableToken {
    public HackAssignmentToken(String rawValue, String tokenValue, int position) {
        super(rawValue, tokenValue, position);
    }

    private String instrBits = "111";


    @Override
    public String toHack() {
        StringBuffer hackOut = new StringBuffer(16);
        hackOut.append("111"); //First 3 bytes
        String tokenValue = getTokenValue().replaceAll("\\s", "");
        String[] fragments = tokenValue.split("\\=");

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
