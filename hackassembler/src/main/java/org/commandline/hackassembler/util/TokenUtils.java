package org.commandline.hackassembler.util;

public class TokenUtils {

    private static final String SINGLE_LINE_COMMENT_PATTERN = "(\\s*)(//.*)";

    public static String sanitizeLabel(String rawLabel) {
        return rawLabel.replace("(", "").replace(")", "").replace("@", "");
    }

    public static String sanitizeComments(String rawLine) {
        return rawLine.replaceAll(SINGLE_LINE_COMMENT_PATTERN, "").trim();
    }
}
