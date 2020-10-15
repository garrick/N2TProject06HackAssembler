package org.commandline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HackParserTest {

    private HackParser unit;

    @BeforeEach
    public void init(){
        unit = new HackParser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"//A single comment","  //Indented comment", "        //Comment with tab and space mixed"})
    public void tokenizeRemovesCommentLines(String rawSingleComment) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleComment);
        List<HackToken> hackTokens = unit.tokenize(rawSingleLineComments);
        assertEquals(rawSingleComment, hackTokens.get(0).getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@21//A single comment","   @21   //indented Value with Indented comment",
            "        @21//Comment with tab and space mixed"})
    public void tokenizeRecognizesValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.tokenize(rawSingleLineComments).get(0);
        assertEquals(hackToken.getClass(), HackValueToken.class);
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@21", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@ 21//bad space","   !21 //exclamation",
            "        #1//pound sign"})
    public void tokenizeRecognizesBadValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.tokenize(rawSingleLineComments).get(0);
        assertEquals(hackToken.getClass(), HackBadToken.class);
        assertEquals(rawSingleLine, hackToken.getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"D=M","   D=M     ",
            "        D=M   "})
    public void tokenizeRecognizesAssignmentWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.tokenize(rawSingleLineComments).get(0);
        assertEquals(hackToken.getClass(), HackAssignmentToken.class);
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D=M", hackToken.getTokenValue());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"D;JGT","   D;JGT     ",
            "        D;JGT   "})
    public void tokenizeRecognizesJumpsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.tokenize(rawSingleLineComments).get(0);
        assertEquals(hackToken.getClass(), HackJumpToken.class);
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D;JGT", hackToken.getTokenValue());
    }
}
