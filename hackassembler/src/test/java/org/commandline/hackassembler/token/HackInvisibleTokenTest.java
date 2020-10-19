package org.commandline.hackassembler.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackInvisibleTokenTest {

    @Test
    public void testToHackReturnsBlankString() {
        HackInvisibleToken unit = new HackInvisibleToken("(SOME_LABEL)", "(SOME_LABEL)", -1);
        assertEquals("", unit.toHack());
    }
}
