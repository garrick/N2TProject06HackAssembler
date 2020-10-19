package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackToken;

import java.util.List;

public interface Parser {
    List<HackToken> firstPass(List<String> rawInput);

    List<HackToken> secondPass(List<HackToken> firstPassTokens);

    List<HackToken> parse(List<String> lines);
}
