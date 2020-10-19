package org.commandline.hackassembler.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackJumpTokenTest {

    @Test
    public void testAZeroCompToHack() {
        HackJumpToken unit = new HackJumpToken("0;JMP", "0;JMP", 0);
        assertEquals("1110101010000111", unit.toHack());
    }
}
