package org.commandline;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackJumpTokenTest {

    @Test
    @Disabled
    public void testAZeroCompToHack() {
        HackJumpToken unit = new HackJumpToken("", "D=A");
        assertEquals("BADFOOD",unit.toHack());
    }
}
