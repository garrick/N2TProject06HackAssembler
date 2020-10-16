package org.commandline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackSymbolTokenTest {

    @Test
    public void testToHack() {
        HackSymbolToken unit = new HackSymbolToken("@example", "@exampleToken", 7);
        unit.setSymbolTableLocation(12);
        assertEquals("@12", unit.toHack());
    }
}
