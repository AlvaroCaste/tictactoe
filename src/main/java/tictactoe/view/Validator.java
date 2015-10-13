package tictactoe.view;

import java.util.Optional;

@FunctionalInterface
public interface Validator<T> {

    Optional<T> validate(String input);
}
