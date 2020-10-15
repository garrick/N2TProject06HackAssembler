package org.commandline;

public class DecimalToBinaryConverter {
    public static String convert16(int value) {
        return convertAndPad(value, 16);
    }

    public static String convertAndPad(int value, int padding) {
        //Format integer as binary, left-pad to 16 characters with spaces, then convert spaces to zeros ;)
        return String.format("%1$" + padding + "s", Integer.toBinaryString(value)).replace(' ', '0');
    }
}
