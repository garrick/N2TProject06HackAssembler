package org.commandline.hackassembler.table;

import org.commandline.hackassembler.util.TokenUtils;

import java.util.HashMap;

public class UserSymbolTable {
    private final HashMap<String, Integer> symbolTable = new HashMap<>();
    private int userMemoryLocation = 15;  //Start at 16, but use pre-increment for each "put" into map

    public int getSymbolName(String symbolName) {
        String cleanSymbol = TokenUtils.sanitize(symbolName);
        if (symbolTable.containsKey(cleanSymbol)) return symbolTable.get(cleanSymbol);
        symbolTable.put(cleanSymbol, ++userMemoryLocation);
        return userMemoryLocation;
    }

    public void dump() {
        System.out.println("====SYMBOLS===");
        symbolTable.forEach((label, val) -> System.out.println(label + ": " + val));
    }

}
