package org.commandline;

import java.util.HashMap;

public class HackAssignmentToken extends HackStandardToken
{
    public HackAssignmentToken(String rawValue, String tokenValue) {
        super(rawValue, tokenValue);
    }

    private String instrBits = "111";

    private HashMap<String, String> aZeroCInstructions = new HashMap<>();
    {
        aZeroCInstructions.put("0",  "101010");
        aZeroCInstructions.put("1",  "111111");
        aZeroCInstructions.put("-1", "111010");
        aZeroCInstructions.put("D",  "001100");
        aZeroCInstructions.put("A",  "110000"); //M
        aZeroCInstructions.put("!D", "001101");
        aZeroCInstructions.put("!A", "110001"); //!M
        aZeroCInstructions.put("-D", "001111");
        aZeroCInstructions.put("-A", "110011"); //-M
        aZeroCInstructions.put("D+1","011111");
        aZeroCInstructions.put("A+1","110111"); //M+1
        aZeroCInstructions.put("D-1","001110");
        aZeroCInstructions.put("A-1","110010"); //M-1
        aZeroCInstructions.put("D+A","000010"); //D+M
        aZeroCInstructions.put("D-A","010011"); //D-M
        aZeroCInstructions.put("A-D","000111"); //M-D
        aZeroCInstructions.put("D&A","000000"); //D&M
        aZeroCInstructions.put("D|A","010101"); //D|M
    }

    private HashMap<String, String> aOneCInstructions = new HashMap<>();
    {
        aOneCInstructions.put("M",  "110000");
        aOneCInstructions.put("!M", "110001");
        aOneCInstructions.put("-M", "110011");
        aOneCInstructions.put("M+1","110111");
        aOneCInstructions.put("M-1","110010");
        aOneCInstructions.put("D+M","000010");
        aOneCInstructions.put("D-M","010011");
        aOneCInstructions.put("M-D","000111");
        aOneCInstructions.put("D&M","000000");
        aOneCInstructions.put("D|M","010101");
    }

    private HashMap<String, String> destinationBits = new HashMap<>();
    {
        destinationBits.put("M",   "001");
        destinationBits.put("D",   "010");
        destinationBits.put("MD",  "011");
        destinationBits.put("A",   "100");
        destinationBits.put("AM",  "101");
        destinationBits.put("AD",  "110");
        destinationBits.put("AMD", "111");
    }

    @Override
    public String toHack() {
        StringBuffer hackOut = new StringBuffer(16);
        hackOut.append("111"); //First 3 bytes
        String tokenValue = getTokenValue().replaceAll("\\s", "");
        String[] fragments = tokenValue.split("\\=");

        String comp = fragments[1];
        if(aZeroCInstructions.containsKey(comp)) {
            hackOut.append("0"); //Zero A
            hackOut.append(aZeroCInstructions.get(comp));
        } else {
            hackOut.append("1");
            hackOut.append(aOneCInstructions.get(comp));
        }
        String dest = fragments[0];
        hackOut.append(destinationBits.getOrDefault(dest, "000"));
        return hackOut.toString() + "000";  //Zero jump bits
    }
}
