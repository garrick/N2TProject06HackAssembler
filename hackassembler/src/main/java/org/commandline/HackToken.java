package org.commandline;

public interface HackToken {
    String getRawValue();
    String getTokenValue();
    public abstract String toHack();
}
