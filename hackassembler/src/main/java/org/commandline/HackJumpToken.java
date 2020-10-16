package org.commandline;

public class HackJumpToken extends HackStandardToken implements HackExecutableToken {

    public HackJumpToken(String rawLine, String tokenValue, int position) {
        super(rawLine, tokenValue, position);
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
