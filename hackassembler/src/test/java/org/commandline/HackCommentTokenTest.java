package org.commandline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class HackCommentTokenTest {

    @Test
    public void shouldReturnNullValueAndNoPosition(){
        HackCommentToken unit = new HackCommentToken("//Foo", 7);
        assertNull(unit.getTokenValue());
    }
}
