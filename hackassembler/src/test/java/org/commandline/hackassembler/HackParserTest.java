package org.commandline.hackassembler;

import org.commandline.hackassembler.token.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackParserTest {

    private HackParser unit;

    @BeforeEach
    public void init() {
        unit = new HackParser();
    }

    @ParameterizedTest
    @ValueSource(strings = {"//A single comment", "  //Indented comment", "        //Comment with tab and space mixed"})
    public void firstPassRemovesCommentLines(String rawSingleComment) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleComment);
        List<HackToken> hackTokens = unit.firstPass(rawSingleLineComments);
        assertEquals(rawSingleComment, hackTokens.get(0).getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@21//A single comment", "   @21   //indented Value with Indented comment",
            "        @21//Comment with tab and space mixed"})
    public void firstPassRecognizesValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@21", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@ 21//bad space", "   !21 //exclamation",
            "        #1//pound sign"})
    public void firstPassRecognizesBadValues(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackBadToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"D=M", "   D=M     ",
            "        D=M   "})
    public void firstPassRecognizesAssignmentWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackAssignmentToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D=M", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"D;JGT", "   D;JGT     ",
            "        D;JGT   "})
    public void firstPassRecognizesJumpsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackJumpToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("D;JGT", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"(END)", "   (END)     ",
            "        (END)   "})
    public void firstPassRecognizesLabelsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackLabelToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("(END)", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@END", "   @END     ",
            "        @END   "})
    public void firstPassRecognizesUserSymbolsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackSymbolToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@END", hackToken.getTokenValue());
        //No symbol location should be set on first pass!
        assertEquals("@-1", hackToken.toHack());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@KBD", "   @KBD     ",
            "        @KBD   "})
    public void firstPassConvertsBuiltInSymbolsFromWordsWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@24576", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@R15", "   @R15     ",
            "        @R15   "})
    public void firstPassConvertsBuiltInSymbolsAsRegistersWithoutSpaces(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackValueToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@15", hackToken.getTokenValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"@output.getmap$if_true0", " @output.getmap$if_true0  ", "    @output.getmap$if_true0  "})
    public void firstPassCreatesUserDefinedHackSymbolTokens(String rawSingleLine) {
        List<String> rawSingleLineComments = Collections.singletonList(rawSingleLine);
        HackToken hackToken = unit.firstPass(rawSingleLineComments).get(0);
        assertEquals(HackSymbolToken.class, hackToken.getClass());
        assertEquals(rawSingleLine, hackToken.getRawValue());
        assertEquals("@output.getmap$if_true0", hackToken.getTokenValue());
    }

    @Test
    public void secondPassReplacesMultipleLabelReferenceWithAValues() {
        String[] rawLines = new String[]
                {
                        "//Nothing",
                        "//Junk",
                        "//Nada",
                        "(MY_EXTRA_LABEL)",
                        "(LOOP_screen.drawpixel0)",
                        "   @LOOP_screen.drawpixel0",
                        "   0;JMP",
                        "   @MY_EXTRA_LABEL"
                };
        List<String> rawSingleLines = Arrays.asList(rawLines);

        List<HackToken> hackTokensSecondPass = unit.secondPass(unit.firstPass(rawSingleLines));
        HackToken firstLabel = hackTokensSecondPass.get(7);
        assertEquals("@MY_EXTRA_LABEL", firstLabel.getTokenValue(), "We should get the first label");
        assertEquals("0000000000000000", firstLabel.toHack(), "We should get the AValue");
        HackToken secondLabel = hackTokensSecondPass.get(5);
        assertEquals("@LOOP_screen.drawpixel0", secondLabel.getTokenValue(), "We should get the second label");
        assertEquals("0000000000000000", secondLabel.toHack(), "We should get the AValue");
    }

    @Test
    public void secondPassReplacesUserSymbolsWithAValues() {
        String[] rawLines = new String[]
                {
                        "@sys.wait$while_end0",
                        "M=1"
                };
        List<String> rawSingleLines = Arrays.asList(rawLines);
        List<HackToken> hackTokensSecondPass = unit.secondPass(unit.firstPass(rawSingleLines));
        HackToken lastTokenSecondPass = hackTokensSecondPass.get(0);
        assertEquals(HackSymbolToken.class, lastTokenSecondPass.getClass());
        assertEquals("@sys.wait$while_end0", lastTokenSecondPass.getTokenValue(), "We should match");
        assertEquals("0000000000010000", lastTokenSecondPass.toHack(), "We should get the AValue");
    }

}