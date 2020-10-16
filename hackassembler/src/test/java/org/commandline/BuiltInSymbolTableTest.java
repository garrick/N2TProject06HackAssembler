package org.commandline;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuiltInSymbolTableTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15})
    public void testStandardBuiltInSymbolsForRValues(int rValue) {
        assertEquals(rValue, BuiltInSymbolTable.getOrDefault("R" + rValue, -1), "Bad value for R" + rValue);
    }

    @Test
    public void testHardwareSymbols() {
        assertEquals(16384, BuiltInSymbolTable.getOrDefault("SCREEN", -1));
        assertEquals(24576, BuiltInSymbolTable.getOrDefault("KBD", -1));
    }

    @Test
    public void testCommonMnemonicSymbols() {
        assertEquals(0, BuiltInSymbolTable.getOrDefault("SP", -1));
        assertEquals(1, BuiltInSymbolTable.getOrDefault("LCL", -1));
        assertEquals(2, BuiltInSymbolTable.getOrDefault("ARG", -1));
        assertEquals(3, BuiltInSymbolTable.getOrDefault("THIS", -1));
        assertEquals(4, BuiltInSymbolTable.getOrDefault("THAT", -1));
    }
}
