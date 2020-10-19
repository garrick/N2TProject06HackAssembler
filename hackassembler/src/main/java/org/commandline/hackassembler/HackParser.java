package org.commandline.hackassembler;

import org.commandline.hackassembler.table.BuiltInSymbolTable;
import org.commandline.hackassembler.table.UserLabelTable;
import org.commandline.hackassembler.table.UserSymbolTable;
import org.commandline.hackassembler.token.*;
import org.commandline.hackassembler.util.DebugFlag;

import java.util.ArrayList;
import java.util.List;

public class HackParser implements Parser {

    UserLabelTable labelPositions = new UserLabelTable();

    private final String singleLineCommentPattern = "(\\s*)(//.*)";
    private final String valuePattern = "(\\@\\d*)";
    private final String symbolPattern = "(\\@([a-zA-Z0-9.$_]+))";
    private final String assignmentPattern = ".*=.*";
    private final String jumpPattern = ".*;.*";
    private final String labelPattern = "\\(([a-zA-Z0-9.$_]+)\\)";

    public List<HackToken> firstPass(List<String> rawInput) {
        ArrayList<HackToken> returnList = new ArrayList<>();
        int position = 0;
        ArrayList<HackLabelToken> lastOpenLabel = new ArrayList<>();
        for (String rawLine : rawInput) {
            String noComments = rawLine.replaceAll(singleLineCommentPattern, "");
            String noCommentsTrimmed = noComments.trim();
            HackToken token = null;
            if (noCommentsTrimmed.isBlank()) {
                token = new HackCommentToken(rawLine, position);
            } else if (noCommentsTrimmed.matches(valuePattern)) {
                token = new HackValueToken(rawLine, noCommentsTrimmed, position);
            } else if (noCommentsTrimmed.matches(symbolPattern)) {
                int builtInSymbol = BuiltInSymbolTable.getOrDefault(noCommentsTrimmed.substring(1), -1);
                if (builtInSymbol != -1) {
                    token = new HackValueToken(rawLine, "@" + builtInSymbol, position);
                } else {
                    token = new HackSymbolToken(rawLine, noCommentsTrimmed, position, labelPositions);
                }
            } else if (noCommentsTrimmed.matches(labelPattern)) {
                lastOpenLabel.add(new HackLabelToken(rawLine, noCommentsTrimmed, position));
                token = lastOpenLabel.get(lastOpenLabel.size() - 1);
            } else if (noCommentsTrimmed.matches(assignmentPattern)) {
                token = new HackAssignmentToken(rawLine, noCommentsTrimmed, position);
            } else if (noCommentsTrimmed.matches(jumpPattern)) {
                token = new HackJumpToken(rawLine, noCommentsTrimmed, position);
            } else {
                token = new HackBadToken(rawLine, position);
            }
            returnList.add(token);
            if (token instanceof HackExecutableToken) {
                if (!lastOpenLabel.isEmpty()) {
                    final int lambdaPosition = position;
                    lastOpenLabel.forEach((label) -> labelPositions.storeLabel(label.getTokenValue(), lambdaPosition));
                    lastOpenLabel.clear();
                }
            }
            if (!(token instanceof HackInvisibleToken)) {
                position++;
            }
        }
        return returnList;
    }

    @Override
    public List<HackToken> secondPass(List<HackToken> firstPassTokens) {
        UserSymbolTable userSymbolTable = new UserSymbolTable();
        ArrayList<HackToken> secondPassTokens = new ArrayList<>();
        for (HackToken token : firstPassTokens) {
            token.updateSymbols(userSymbolTable);
            secondPassTokens.add(token);
        }
        //TODO: address UserSymbolTable usage
        return secondPassTokens;
    }

    @Override
    public List<HackToken> parse(List<String> lines) {
        return secondPass(firstPass(lines));
    }

    public void dumpLabels() {
        if(DebugFlag.isOn()) labelPositions.dump();
    }
}
