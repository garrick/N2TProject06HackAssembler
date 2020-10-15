package org.commandline;

import java.util.ArrayList;
import java.util.List;

public class HackParser implements Parser {

    private final String singleLineCommentPattern = "(\\s*)(//.*)";
    private final String valuePattern = "(\\@\\d*)";
    private final String assignmentPattern = ".*=.*";
    private final String jumpPattern = ".*;.*";
    @Override
    public List<HackToken> tokenize(List<String> rawInput) {
        ArrayList<HackToken> returnList = new ArrayList<>();
        rawInput.forEach((String rawLine) -> {
            String noComments = rawLine.replaceAll(singleLineCommentPattern, "");
            String noCommentsTrimmed = noComments.trim();
            if(noCommentsTrimmed.isBlank()){
                returnList.add(new HackCommentToken(rawLine));
            } else if(noCommentsTrimmed.matches(valuePattern)){
                returnList.add(new HackValueToken(rawLine, noCommentsTrimmed));
            } else if(noCommentsTrimmed.matches(assignmentPattern)) {
                returnList.add(new HackAssignmentToken(rawLine, noCommentsTrimmed));
            } else if(noCommentsTrimmed.matches(jumpPattern)){
                    returnList.add(new HackJumpToken(rawLine, noCommentsTrimmed));
            } else {
                returnList.add(new HackBadToken(rawLine));
            }
        });
        return returnList;
    }
}
