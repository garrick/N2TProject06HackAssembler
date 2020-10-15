package org.commandline;

import java.util.List;

public interface Parser {
    List<HackToken> tokenize(List<String> rawInput);
}
