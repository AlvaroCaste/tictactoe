package tictactoe.view;

import tictactoe.model.Cell;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellValidator implements Validator<Cell> {

    private final Pattern CELL_PATTERN = Pattern
            .compile("\\s*(\\d+),\\s*(\\d+)\\s*");

    @Override
    public Optional<Cell> validate(String input) {
        Matcher matcher = CELL_PATTERN.matcher(input);
        return matcher.find()
                ? Optional.of(buildCell(matcher))
                : Optional.empty();
    }

    private Cell buildCell(Matcher matcher) {
        int row = Integer.parseInt(matcher.group(1));
        int col = Integer.parseInt(matcher.group(2));
        return new Cell(row, col);
    }
}
