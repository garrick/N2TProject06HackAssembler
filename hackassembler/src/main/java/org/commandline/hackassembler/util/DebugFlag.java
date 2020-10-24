package org.commandline.hackassembler.util;

import org.commandline.hackassembler.token.HackToken;

public class DebugFlag {
    private static String flagValue;

    public static void setFlag(final String[] args) {
        if (args.length == 2 && "--debug".equals(args[1])) {
            flagValue = args[1];
        }
    }

    public static boolean isOn() {
        return flagValue != null;
    }

    public static void debugHeader() {
        if (isOn()) {
            String tokenName = padOut(24, "Token Name");
            String tokenPosition = padOut(8, "Position");
            String tokenValue = padOut(16, "Token Value");
            System.out.println(leftMarginPadding() + tokenName + "\t" + "Token ASM" + "\t" + tokenPosition + "\t" + tokenValue + "\tToken Raw");
            System.out.println(leftMarginPadding() + String.format("%1$" + 140 + "s", " ").replace(' ', '='));
        }
    }

    public static String leftMarginPadding() {
        return "\t";
    }

    private static String padOut(int padValue, String padString) {
        return String.format("%1$" + padValue + "s", padString);
    }

    public static void debugOut(HackToken token) {
        if (isOn()) {
            String tokenName = padOut(24, token.getClass().getSimpleName());
            String tokenPosition = padOut(8, "" + token.getPosition());
            String tokenValue = padOut(16, "" + token.getTokenValue());
            String hackValue = token.toHack();
            System.out.println(leftMarginPadding() + tokenName + "\t" + (hackValue.isBlank() ? "\t\t" : hackValue)
                    + tokenPosition + "\t" + tokenValue + "\t" + token.getRawValue());
        }
    }


}
