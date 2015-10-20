package tictactoe.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TerminalConsole implements Console {

    private final BufferedReader input;
    private final Writer output;

    public TerminalConsole(Reader input, Writer output) {
        this.input = new BufferedReader(input);
        this.output = output;
    }

    @Override
    public void display(String text) throws IOException {
        output.write(text);
    }

    @Override
    public String ask(String prompt) throws IOException {
        display(prompt);
        return readLine();
    }

    private String readLine() throws IOException {
        String line = input.readLine();
        if (line == null)
            throw new IOException("Input is already closed");
        return line;
    }

}
