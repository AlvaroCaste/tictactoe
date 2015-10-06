package tictactoe.model.utils;

import tictactoe.model.Board;
import tictactoe.model.Cell;
import tictactoe.model.Player;

import java.util.Optional;

public class BoardParser {

    public static Board parseBoard(String lines) {
        Board board = new Board();
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                Cell cell = new Cell(row, col);
                char mark = lines.charAt(getCellIndex(cell));
                parseMark(mark).ifPresent(player -> board.mark(cell, player));
            }
        }
        return board;
    }

    private static int getCellIndex(Cell cell) {
        return cell.row * (Board.SIZE + 1) + cell.col;
    }

    private static Optional<Player> parseMark(char mark) {
        switch (mark) {
            case 'X':
                return Optional.of(Player.X);
            case 'O':
                return Optional.of(Player.O);
            default:
                return Optional.empty();
        }
    }
}
