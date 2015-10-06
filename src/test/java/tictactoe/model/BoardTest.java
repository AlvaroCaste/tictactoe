package tictactoe.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tictactoe.model.utils.BoardParser.parseBoard;

public class BoardTest {

    static final List<Cell> cells = Arrays.asList(
            new Cell(0,0),new Cell(0,1),new Cell(0,2),
            new Cell(1,0),new Cell(1,1),new Cell(1,2),
            new Cell(2,0),new Cell(2,1),new Cell(2,2));

    @Test
    public void hashCodeShouldBeEqualsToContentsHashCode() throws Exception {
        Board board = new Board();
        board.mark(new Cell(0, 0), Player.X);
        Board otherBoard = new Board();
        otherBoard.mark(new Cell(0, 0), Player.X);

        assertEquals(board, otherBoard);
        assertEquals(otherBoard.hashCode(), board.hashCode());
    }

    @Test
    public void theInitialBoardShouldBeEmpty() throws Exception {
        Board board = new Board();
        for(Cell cell : cells) {
            assertEquals(Optional.empty(), board.getContent(cell));
        }
    }

    @Test
    public void shouldMarkACell() throws Exception {
        Board board = new Board();
        board.mark(cells.get(0), Player.X);
        assertEquals(Player.X, board.getContent(cells.get(0)).get());
    }

    @Test
    public void shouldNotBeComplete() throws Exception {
        assertFalse(parseBoard("---\n---\n---\n").isComplete());
        assertFalse(parseBoard("O--\n-X-\nOX-\n").isComplete());
        assertFalse(parseBoard("XOX\nOXO\nOX-\n").isComplete());
    }

    @Test
    public void shouldBeComplete() throws Exception {
        assertTrue(parseBoard("XOX\nOXO\nOXX\n").isComplete());
    }
}
