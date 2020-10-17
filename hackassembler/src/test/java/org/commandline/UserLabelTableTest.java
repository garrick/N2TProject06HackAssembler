package org.commandline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserLabelTableTest {

    private UserLabelTable unit;

    @BeforeEach
    public void setUp() {
        unit = new UserLabelTable();
        unit.storeLabel("(LOOP)", 1);
    }

    @Test
    public void testStoreLabelStripsParens() {
        assertTrue(unit.hasLabel("LOOP"));
    }

    @Test
    public void testStoreLabelFindsAsAddress() {
        assertTrue(unit.hasLabel("@LOOP"));
    }
}
