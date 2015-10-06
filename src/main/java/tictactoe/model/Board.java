package tictactoe.model;

import java.util.*;

public class Board {

    public static final int SIZE = 3;
    private static final List<Cell> CELLS = Arrays.asList(
            new Cell(0, 0), new Cell(0, 1), new Cell(0, 2),
            new Cell(1, 0), new Cell(1, 1), new Cell(1, 2),
            new Cell(2, 0), new Cell(2, 1), new Cell(2, 2));

    private final Map<Cell,Optional<Player>> contents;

    public Board() {
        contents = new HashMap<>();
        for (Cell cell : CELLS) {
            this.contents.put(cell, Optional.empty());
        }

    }

    public Optional<Player> getContent(Cell position) {
        return contents.get(position);
    }

    public void mark(Cell cell, Player player) {
        if (!contents.containsKey(cell))
            throw new IllegalArgumentException(cell + " is out of the board");
        if (contents.get(cell).isPresent())
            throw new IllegalArgumentException(cell + " has been already taken");
        contents.put(cell, Optional.of(player));
    }

    public boolean isComplete() {
        return contents.values()
                .stream()
                .allMatch(Optional::isPresent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return !(contents != null ? !contents.equals(board.contents) : board.contents != null);

    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBoard = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                stringBoard.append(
                        formatCell(contents.get(new Cell(row, col))));
            }
            stringBoard.append("\n");
        }
        return stringBoard.toString();
    }

    private String formatCell(Optional<Player> cellContent) {
        return cellContent.map(Enum::toString).orElse("-");
    }
}
