package org.commandline.hackassembler.table;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSymbolTableTest {

    @Test
    public void testUserSymbolStartsAt16AndTableIncrementsAndHoldsNewValues() {
        UserSymbolTable unit = new UserSymbolTable();
        assertEquals(16, unit.getSymbolName("@foo"));
        assertEquals(17, unit.getSymbolName("@bar"));
        assertEquals(16, unit.getSymbolName("@foo"));
        assertEquals(17, unit.getSymbolName("@bar"));
    }
}
