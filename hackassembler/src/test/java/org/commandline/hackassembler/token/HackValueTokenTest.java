package org.commandline.hackassembler.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackValueTokenTest {

    @Test
    public void testValueToHack() {
        HackValueToken unit = new HackValueToken("@2", "@2", 0);
        assertEquals("0000000000000010", unit.toHack());
    }
}