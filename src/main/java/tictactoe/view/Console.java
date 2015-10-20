package tictactoe.view;

import java.io.IOException;
import java.util.Optional;

public interface Console {

    void display(String text) throws IOException;

    String ask(String prompt) throws IOException;

    default <T> T ask(String prompt, Validator<T> validator) throws IOException {
        Optional<T> answer = validator.validate(ask(prompt));
        return answer.isPresent() ? answer.get() : ask(prompt, validator);
    }
}
