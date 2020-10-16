package org.commandline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackAssignmentTokenTest {

    @Test
    public void testAZeroCompToHack() {
        HackAssignmentToken unit = new HackAssignmentToken("D=A", "D=A", 0);
        assertEquals("1110110000010000", unit.toHack());
    }

    @Test
    public void testAOneToHack() {
        HackAssignmentToken unit = new HackAssignmentToken("D=D-M", "D=D-M", 0);
        assertEquals("1111010011010000", unit.toHack());
    }
}
