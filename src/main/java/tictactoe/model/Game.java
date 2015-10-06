package tictactoe.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {

    private final static List<List<Cell>> WIN_LINES;
    static {
        WIN_LINES = Arrays.asList(
                Arrays.asList(new Cell(0,0), new Cell(0, 1), new Cell(0, 2)),
                Arrays.asList(new Cell(1,0), new Cell(1, 1), new Cell(1, 2)),
                Arrays.asList(new Cell(2,0), new Cell(2, 1), new Cell(2, 2)),
                Arrays.asList(new Cell(0,0), new Cell(1, 0), new Cell(2, 0)),
                Arrays.asList(new Cell(0,1), new Cell(1, 1), new Cell(2, 1)),
                Arrays.asList(new Cell(0,2), new Cell(1, 2), new Cell(2, 2)),
                Arrays.asList(new Cell(0,0), new Cell(1, 1), new Cell(2, 2)),
                Arrays.asList(new Cell(0,2), new Cell(1, 1), new Cell(2, 0))
                );
    }

    private Board board = new Board();
    private Player player = Player.X;

    public Player currentPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public void play(Cell cell) {
        board.mark(cell, currentPlayer());
        player = player.getNext();
    }

    public Optional<Player> getWinner() {
        return WIN_LINES.stream()
                .map(this::getWinner)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private Optional<Player> getWinner(List<Cell> line) {
        return winnerPlayer(countMarks(line));
    }

    private Optional<Player> winnerPlayer(Map<Player, Long> counts) {
        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() == Board.SIZE)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    private Map<Player, Long> countMarks(List<Cell> line) {
        return line.stream()
                .map(board::getContent)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));
    }

    public boolean isOver() {
        return getWinner().isPresent() || board.isComplete();
    }
}
