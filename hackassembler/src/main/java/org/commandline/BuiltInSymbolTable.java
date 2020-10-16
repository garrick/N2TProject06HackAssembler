package org.commandline;

import java.util.HashMap;

public class BuiltInSymbolTable {

    private static HashMap<String, Integer> table = new HashMap();

    static {
        for (int i = 0; i <= 15; i++) {
            table.put("R" + i, i);
        }
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);

        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);

    }

    public static int getOrDefault(String candidateSymbol, int defaultValue) {
        return table.getOrDefault(candidateSymbol, defaultValue);
    }
}
