package org.commandline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackLabelTokenTest {

    @Test
    public void testPosition() {
        HackLabelToken unit = new HackLabelToken("(END)", "(END)", 7);
        assertEquals(7, unit.getPosition());
    }
}
