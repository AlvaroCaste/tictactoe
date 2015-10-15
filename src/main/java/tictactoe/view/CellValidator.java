package tictactoe.view;

import tictactoe.model.Cell;

import java.util.Arrays;
import java.util.Optional;

public class CellValidator implements Validator<Cell> {

    private final int NUMBER_OF_COORDINATES = 2;

    @Override
    public Optional<Cell> validate(String input) {
        //TODO: Refactor
        int[] coords = Arrays.stream(input.split(","))
                .filter(this::isANumber)
                .mapToInt(Integer::parseInt)
                .toArray();

        return (coords.length == NUMBER_OF_COORDINATES) ?
                Optional.of(new Cell(coords[0], coords[1])) :
                Optional.<Cell>empty();
    }

    private boolean isANumber(String number) {
        for (char digit : number.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        return true;
    }
}
