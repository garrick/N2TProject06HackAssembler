package org.commandline;

import java.util.ArrayList;
import java.util.List;

public class HackParser implements Parser {

    UserLabelTable labelPositions = new UserLabelTable();

    private final String singleLineCommentPattern = "(\\s*)(//.*)";
    private final String valuePattern = "(\\@\\d*)";
    private final String symbolPattern = "(\\@[A-Z,a-z]+\\w*)";
    private final String assignmentPattern = ".*=.*";
    private final String jumpPattern = ".*;.*";
    private final String labelPattern = "\\(.*\\)";

    public List<HackToken> firstPass(List<String> rawInput) {
        ArrayList<HackToken> returnList = new ArrayList<>();
        int position = 0;
        HackLabelToken lastOpenLabel = null;
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
                lastOpenLabel = new HackLabelToken(rawLine, noCommentsTrimmed, position);
                token = lastOpenLabel;
            } else if (noCommentsTrimmed.matches(assignmentPattern)) {
                token = new HackAssignmentToken(rawLine, noCommentsTrimmed, position);
            } else if (noCommentsTrimmed.matches(jumpPattern)) {
                token = new HackJumpToken(rawLine, noCommentsTrimmed, position);
            } else {
                token = new HackBadToken(rawLine, position);
            }
            returnList.add(token);
            if (token instanceof HackExecutableToken) {
                if (lastOpenLabel != null) {
                    labelPositions.storeLabel(lastOpenLabel.getTokenValue(), position);
                    lastOpenLabel = null;
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
        //userSymbolTable.dump();
        return secondPassTokens;
    }

    public void dumpLabels() {
        labelPositions.dump();
    }
}
