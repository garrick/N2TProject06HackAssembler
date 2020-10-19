package org.commandline.hackassembler;

import org.commandline.hackassembler.table.UserLabelTable;
import org.commandline.hackassembler.table.UserSymbolTable;
import org.commandline.hackassembler.token.*;
import org.commandline.hackassembler.tokenizer.*;
import org.commandline.hackassembler.util.DebugFlag;
import org.commandline.hackassembler.util.TokenUtils;

import java.util.ArrayList;
import java.util.List;

public class HackParser implements Parser {

    UserLabelTable labelPositions = new UserLabelTable();
    UserSymbolTable userSymbolPositions = new UserSymbolTable();

    @Override
    public List<HackToken> parse(List<String> lines) {
        return secondPass(firstPass(lines));
    }

    public List<HackToken> firstPass(List<String> rawInput) {
        ArrayList<HackToken> returnList = new ArrayList<>();
        ArrayList<HackLabelToken> lastOpenLabel = new ArrayList<>();
        int position = 0;
        //Tokenizer chain of responsibility
        JumpTokenizer jumpTokenizer = new JumpTokenizer(new BadTokenizer());
        AssignmentTokenizer assignmentTokenizer = new AssignmentTokenizer(jumpTokenizer);
        LabelTokenizer labelTokenizer = new LabelTokenizer(assignmentTokenizer, lastOpenLabel);
        SymbolTokenizer symbolTokenizer = new SymbolTokenizer(labelTokenizer, labelPositions);
        ValueTokenizer valueTokenizer = new ValueTokenizer(symbolTokenizer);
        CommentTokenizer commentTokenizer = new CommentTokenizer(valueTokenizer);
        for (String rawLine : rawInput) {
            String sanitizedLine = TokenUtils.sanitizeComments(rawLine);
            HackToken token = commentTokenizer.tokenize(rawLine, sanitizedLine, position);
            returnList.add(token);
            updateLastOpenLabel(lastOpenLabel, position, token);
            position = updatePosition(position, token);
        }
        return returnList;
    }


    List<HackToken> secondPass(List<HackToken> firstPassTokens) {
        ArrayList<HackToken> secondPassTokens = new ArrayList<>();
        for (HackToken token : firstPassTokens) {
            token.updateSymbols(userSymbolPositions);
            secondPassTokens.add(token);
        }
        return secondPassTokens;
    }
    private void updateLastOpenLabel(ArrayList<HackLabelToken> lastOpenLabel, int position, HackToken token) {
        if (token instanceof HackExecutableToken) {
            if (!lastOpenLabel.isEmpty()) {
                final int lambdaPosition = position;
                lastOpenLabel.forEach((label) -> labelPositions.storeLabel(label.getTokenValue(), lambdaPosition));
                lastOpenLabel.clear();
            }
        }
    }

    private int updatePosition(int position, HackToken token) {
        if (!(token instanceof HackInvisibleToken)) {
            position++;
        }
        return position;
    }

    public void dumpParserInfo() {
        if(DebugFlag.isOn()) {
            labelPositions.dump();
            userSymbolPositions.dump();
        }
    }
}
