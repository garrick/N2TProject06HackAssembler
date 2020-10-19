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
        if(isOn()) {
        String tokenName = String.format("%1$" + 24 + "s", "Token Name");
        String tokenPosition = String.format("%1$" + 8 + "s", "Position");
        System.out.println(tokenName + "\t" + "Token ASM" + "\t\t" + tokenPosition + "\t" + "Token Value" + "\tToken Raw");
        System.out.println(String.format("%1$" + 120 + "s", " ").replace(' ', '='));
        }
    }

    public static void debugOut(HackToken token) {
        if(isOn()) {
            String tokenName = String.format("%1$" + 24 + "s", token.getClass().getSimpleName());
            String tokenPosition = String.format("%1$" + 8 + "s", token.getPosition());
            String hackValue = token.toHack();
            System.out.println(tokenName + "\t" + (hackValue.isBlank() ? "\t\t" : hackValue)
                    + "\t" + tokenPosition + "\t" + token.getTokenValue() + "\t\t" + token.getRawValue());
        }
    }
}
