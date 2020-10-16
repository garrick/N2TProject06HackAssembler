package org.commandline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecimalToBinaryConverterTest {

    @Test
    public void testPaddingAndValue() {
        assertEquals("0000000000000010", DecimalToBinaryConverter.convert16(2));
    }
}
