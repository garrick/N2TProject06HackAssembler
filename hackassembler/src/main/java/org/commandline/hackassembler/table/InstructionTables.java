package org.commandline.hackassembler.table;

import java.util.HashMap;

public class InstructionTables {
    private static final HashMap<String, String> aZeroCInstructions = new HashMap<>();

    static {
        aZeroCInstructions.put("0", "101010");
        aZeroCInstructions.put("1", "111111");
        aZeroCInstructions.put("-1", "111010");
        aZeroCInstructions.put("D", "001100");
        aZeroCInstructions.put("A", "110000"); //M
        aZeroCInstructions.put("!D", "001101");
        aZeroCInstructions.put("!A", "110001"); //!M
        aZeroCInstructions.put("-D", "001111");
        aZeroCInstructions.put("-A", "110011"); //-M
        aZeroCInstructions.put("D+1", "011111");
        aZeroCInstructions.put("A+1", "110111"); //M+1
        aZeroCInstructions.put("D-1", "001110");
        aZeroCInstructions.put("A-1", "110010"); //M-1
        aZeroCInstructions.put("D+A", "000010"); //D+M
        aZeroCInstructions.put("D-A", "010011"); //D-M
        aZeroCInstructions.put("A-D", "000111"); //M-D
        aZeroCInstructions.put("D&A", "000000"); //D&M
        aZeroCInstructions.put("D|A", "010101"); //D|M
    }

    private static final HashMap<String, String> aOneCInstructions = new HashMap<>();

    static {
        aOneCInstructions.put("M", "110000");
        aOneCInstructions.put("!M", "110001");
        aOneCInstructions.put("-M", "110011");
        aOneCInstructions.put("M+1", "110111");
        aOneCInstructions.put("M-1", "110010");
        aOneCInstructions.put("D+M", "000010");
        aOneCInstructions.put("D-M", "010011");
        aOneCInstructions.put("M-D", "000111");
        aOneCInstructions.put("D&M", "000000");
        aOneCInstructions.put("D|M", "010101");
    }

    private static final HashMap<String, String> destinationBits = new HashMap<>();

    static {
        destinationBits.put("M", "001");
        destinationBits.put("D", "010");
        destinationBits.put("MD", "011");
        destinationBits.put("A", "100");
        destinationBits.put("AM", "101");
        destinationBits.put("AD", "110");
        destinationBits.put("AMD", "111");
    }

    private static final HashMap<String, String> jumpBits = new HashMap<>();

    static {
        jumpBits.put("JGT", "001");
        jumpBits.put("JEQ", "010");
        jumpBits.put("JGE", "011");
        jumpBits.put("JLT", "100");
        jumpBits.put("JNE", "101");
        jumpBits.put("JLE", "110");
        jumpBits.put("JMP", "111");
    }

    public static String mapDestinationBits(String key, String defValue) {
        return destinationBits.getOrDefault(key, defValue);
    }

    public static String mapAZeroCInstruction(String key, String defValue) {
        return aZeroCInstructions.getOrDefault(key, defValue);
    }

    public static String mapAOneCInstruction(String key, String defValue) {
        return aOneCInstructions.getOrDefault(key, defValue);
    }

    public static String mapJumpBits(String key, String defValue) {
        return jumpBits.getOrDefault(key, defValue);
    }
}
