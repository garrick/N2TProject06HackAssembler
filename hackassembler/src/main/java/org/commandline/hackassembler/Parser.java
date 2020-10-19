package org.commandline.hackassembler;

import org.commandline.hackassembler.token.HackToken;

import java.util.List;

public interface Parser {

    List<HackToken> parse(List<String> lines);
}
