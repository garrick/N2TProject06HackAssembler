package org.commandline.hackassembler.token;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HackStandardTokenTest {

    @Mock
    private PrintStream outputStream;

    @Test
    void testRendersToPrintStream() {
        HackStandardToken unit = new HackStandardToken("@42", "@42", 7) {
            @Override
            public String getRawValue() {
                return super.getRawValue();
            }

            @Override
            public String toHack() {
                return this.getTokenValue();
            }
        };
        unit.renderTo(outputStream);
        verify(outputStream).println("@42");
    }

    @Test
    void testDoesNotRenderToPrintStreamWhenToHackIsBlank() {
        String emptyString = "";
        HackStandardToken unit = new HackStandardToken("//Comment", emptyString, 7) {
            @Override
            public String getRawValue() {
                return super.getRawValue();
            }

            @Override
            public String toHack() {
                return emptyString;
            }
        };
        unit.renderTo(outputStream);
        verify(outputStream, never()).println(emptyString);
    }
}
