package tictactoe.view;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

public class ConsoleTest {

    private PipedWriter input;
    private Writer output;
    private Console console;
    private Validator<Boolean> booleanValidator;

    @Before
    public void setUp() throws Exception {
        input = new PipedWriter();
        output = new CharArrayWriter();
        console = new Console(new PipedReader(input), output);
        booleanValidator = input -> {
            switch (input) {
                case "true": return Optional.of(true);
                case "false": return Optional.of(false);
                default: return Optional.empty();
            }
        };
    }

    @Test
    public void shouldDisplayText() throws Exception {
        String text = "Hello World\n";
        console.display(text);
        assertEquals(text, output.toString());
    }

    @Test
    public void shouldAskForInput() throws Exception {
        String prompt = "Name: ";
        String name = "John";
        typeLine(name);
        String answer = console.ask(prompt);
        assertEquals(prompt, output.toString());
        assertEquals(name, answer);
    }

    @Test(expected = IOException.class)
    public void shouldThrowWhenThereAreNoInput() throws Exception {
        input.close();
        console.ask("Name: ");
    }

    @Test
    public void shouldConvertAnswers() throws Exception {
        String prompt = "type a boolean: ";
        typeLine("false");
        assertFalse(console.ask(prompt, booleanValidator));
    }

    @Test
    public void shouldValidateAnswers() throws Exception {
        String prompt = "type a boolean: ";
        typeLine("I'm not a boolean");
        typeLine("me neither");
        typeLine("true");
        assertTrue(console.ask(prompt, booleanValidator));
        //TODO: clarify
        String allOutputLines = Collections.nCopies(3, prompt)
                .stream().reduce("", String::concat);
        assertEquals(allOutputLines, output.toString());
    }

    public void typeLine(String line) throws IOException {
        input.write(line + "\n");
        input.flush();
    }
}
