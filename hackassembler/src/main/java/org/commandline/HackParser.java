package org.commandline;

import java.util.ArrayList;
import java.util.List;

public class HackParser implements Parser {

    private final String singleLineCommentPattern = "(\\s*)(//.*)";
    private final String valuePattern = "(\\@\\d*)";
    private final String symbolPattern = "(\\@[A-Z]+\\w*)";
    private final String assignmentPattern = ".*=.*";
    private final String jumpPattern = ".*;.*";
    private final String labelPattern = "\\(.*\\)";
    @Override
    public List<HackToken> firstPass(List<String> rawInput) {
        ArrayList<HackToken> returnList = new ArrayList<>();
        int position = 0;
        for(String rawLine: rawInput){
            String noComments = rawLine.replaceAll(singleLineCommentPattern, "");
            String noCommentsTrimmed = noComments.trim();
            HackToken token = null;
            if(noCommentsTrimmed.isBlank()){
                token = new HackCommentToken(rawLine, position);
            }  else if(noCommentsTrimmed.matches(valuePattern)){
                token = new HackValueToken(rawLine, noCommentsTrimmed, position);
            } else if(noCommentsTrimmed.matches(symbolPattern)){
                int builtInSymbol = BuiltInSymbolTable.getOrDefault(noCommentsTrimmed.substring(1), -1);
                if(builtInSymbol != -1){
                    token = new HackValueToken(rawLine, "@"+builtInSymbol, position);
                } else {
                    token = new HackSymbolToken(rawLine, noCommentsTrimmed, position);
                }
            } else if(noCommentsTrimmed.matches(labelPattern)){
                token = new HackLabelToken(rawLine, noCommentsTrimmed, position);
            } else if(noCommentsTrimmed.matches(assignmentPattern)) {
                token = new HackAssignmentToken(rawLine, noCommentsTrimmed, position);
            } else if(noCommentsTrimmed.matches(jumpPattern)){
                token = new HackJumpToken(rawLine, noCommentsTrimmed, position);
            } else {
                token = new HackBadToken(rawLine, position);
            }
            returnList.add(token);
            if(token instanceof HackExecutableToken) {
                position++;
            }
        };
        return returnList;
    }
}
