package org.commandline;

import java.util.List;

public interface Parser {
    List<HackToken> firstPass(List<String> rawInput);

    List<HackToken> secondPass(List<HackToken> firstPassTokens);
}
