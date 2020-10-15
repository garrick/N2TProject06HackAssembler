package org.commandline;

public class DecimalToBinaryConverter {
    public static String convert(int value) {
        //Format integer as binary, left-pad to 16 characters with spaces, then convert spaces to zeros ;)
        return String.format("%1$" + 16 + "s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
