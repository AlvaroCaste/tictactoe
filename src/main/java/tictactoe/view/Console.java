package tictactoe.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Optional;

public class Console {

    private final BufferedReader input;
    private final Writer output;

    public Console(Reader input, Writer output) {
        this.input = new BufferedReader(input);
        this.output = output;
    }

    public void display(String text) throws IOException {
        output.write(text);
    }

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

    public<T> T ask(String prompt, Validator<T> validator) throws IOException {
        Optional<T> answer = validator.validate(ask(prompt));
        return answer.isPresent() ? answer.get() : ask(prompt, validator);
    }
}
