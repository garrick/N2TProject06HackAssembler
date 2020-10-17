package org.commandline;

public class TokenUtils {
    public static String sanitize(String rawLabel) {
        return rawLabel.replace("(", "").replace(")","").replace("@","");
    }
}
