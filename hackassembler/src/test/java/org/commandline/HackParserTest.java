package org.commandline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        List<HackToken> hackTokens = unit.firstPass(rawSingleLineComments);
        assertEquals(rawSingleComment, hackTokens.get(0).getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@21//A single comment","   @21   //indented Value with Indented comment",
            "        @21//Comment with tab and space mixed"})
    public void tokenizeRecognizesValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@21", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@ 21//bad space","   !21 //exclamation",
            "        #1//pound sign"})
    public void tokenizeRecognizesBadValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackBadToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"D=M","   D=M     ",
            "        D=M   "})
    public void tokenizeRecognizesAssignmentWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackAssignmentToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D=M", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"D;JGT","   D;JGT     ",
            "        D;JGT   "})
    public void tokenizeRecognizesJumpsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackJumpToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D;JGT", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"(END)","   (END)     ",
            "        (END)   "})
    public void tokenizeRecognizesLabelsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackLabelToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("(END)", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@END","   @END     ",
            "        @END   "})
    public void tokenizeRecognizesUserSymbolsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackSymbolToken.class,hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@END", hackToken.getTokenValue());
        //No symbol location should be set on first pass!
        assertEquals(-1, ((HackSymbolToken)hackToken).getSymbolTableLocation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@KBD","   @KBD     ",
            "        @KBD   "})
    public void tokenizeConvertsBuiltInSymbolsFromWordsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class,hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@24576", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@R15","   @R15     ",
            "        @R15   "})
    public void tokenizeConvertsBuiltInSymbolsAsRegistersWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Arrays.asList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class,hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@15", hackToken.getTokenValue());
    }

}
