package org.commandline.hackassembler.token;

import org.commandline.hackassembler.table.UserLabelTable;
import org.commandline.hackassembler.table.UserSymbolTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackSymbolTokenTest {

    @Test
    public void testUpdateLooksUpValue() {
        UserSymbolTable ust = new UserSymbolTable();
        ust.getSymbolName("@example");
        HackSymbolToken unit = new HackSymbolToken("@example", "@example", -1, new UserLabelTable());
        unit.updateSymbols(ust);
        assertEquals("@example", unit.getTokenValue());
        assertEquals("0000000000010000", unit.toHack());
    }
}