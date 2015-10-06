package tictactoe.model;

import static org.junit.Assert.assertEquals;
import static tictactoe.model.utils.BoardParser.parseBoard;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class GameTest {

    Game game;

    @Before
    public void init() {
        game = new Game();
    }

    private void givenMoves(int... coords) {
        for(int pair = 0; pair < coords.length / 2; pair++) {
            game.play(new Cell(coords[pair * 2], coords[pair * 2 + 1]));
        }
    }

    @Test
    public void boardShouldBeConvertedToString() throws Exception {
        String board = "---\n" +
                       "---\n" +
                       "---\n";
        assertEquals(board, parseBoard(board).toString());
        board = "X--\n" +
                "-X-\n" +
                "--O\n";
        assertEquals(board, parseBoard(board).toString());
    }

    @Test
    public void theInitialPlayerShouldBeX() throws Exception {
        assertEquals(Player.X, game.currentPlayer());
    }

    @Test
    public void theInitialBoardShouldBeEmpty() throws Exception {
        assertEquals(new Board(), game.getBoard());

    }

    @Test
    public void shouldMarkSelectedCell() throws Exception {
        game.play(new Cell(0, 0));
        assertEquals(parseBoard("X--\n---\n---\n"), game.getBoard());
    }

    @Test
    public void theNextPlayerShouldBeO() throws Exception {
        game.play(new Cell(0, 0));
        game.play(new Cell(0, 1));
        assertEquals(parseBoard("XO-\n---\n---\n"), game.getBoard());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAvoidPlayingTwiceOnTheSameCell() throws Exception {
        game.play(new Cell(0, 0));
        game.play(new Cell(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAvoidPlayingOutOfTheBoard() throws Exception {
        game.play(new Cell(10, 10));
    }

    @Test
    public void shouldHaveNoWinnerIfThereAreNoLines() throws Exception {
        assertEquals(Optional.empty(), game.getWinner());
    }

    @Test
    public void shouldBeWinnerPlayerXDoingLineInFirstRow() throws Exception {
        givenMoves(0, 0,
                   1, 0,
                   0, 1,
                   1, 1,
                   0, 2);
        assertEquals(parseBoard("XXX\nOO-\n---\n"), game.getBoard());
        assertEquals(Player.X, game.getWinner().get());
    }

    @Test
    public void shouldBeWinnerPlayerODoingLineInFirstRow() throws Exception {
        givenMoves(1, 0,
                   0, 0,
                   1, 1,
                   0, 1,
                   2, 0,
                   0, 2);
        assertEquals(parseBoard("OOO\nXX-\nX--\n"), game.getBoard());
        assertEquals(Player.O, game.getWinner().get());
    }

    @Test
    public void shouldBeWinnerPlayerXDoingLineInFirstColumn() throws Exception {
        givenMoves(0, 0,
                   0, 1,
                   1, 0,
                   1, 1,
                   2, 0);
        assertEquals(parseBoard("XO-\nXO-\nX--\n"), game.getBoard());
        assertEquals(Player.X, game.getWinner().get());
    }

    @Test
    public void shouldBeWinnerPlayerODoingLineInFirstColumn() throws Exception {
        givenMoves(0, 1,
                   0, 0,
                   1, 1,
                   1, 0,
                   2, 2,
                   2, 0);
        assertEquals(parseBoard("OX-\nOX-\nO-X\n"), game.getBoard());
        assertEquals(Player.O, game.getWinner().get());
    }

    @Test
    public void shouldBeWinnerPlayerXDoingLineInDiagonal() throws Exception {
        givenMoves(0, 0,
                   0, 1,
                   1, 1,
                   1, 0,
                   2, 2);
        assertEquals(parseBoard("XO-\nOX-\n--X\n"), game.getBoard());
        assertEquals(Player.X, game.getWinner().get());
    }

    @Test
    public void shouldBeWinnerPlayerODoingLineInSecondDiagonal() throws Exception {
        givenMoves(0, 0,
                   0, 2,
                   0, 1,
                   1, 1,
                   1, 0,
                   2, 0);
        assertEquals(parseBoard("XXO\nXO-\nO--\n"), game.getBoard());
        assertEquals(Player.O, game.getWinner().get());
    }

    @Test
    public void shouldContinueGameIfGameIsEmpty() throws Exception {
        assertEquals(false, game.isOver());
    }

    @Test
    public void gameShouldEndWhenPlayerXWins() throws Exception {
        givenMoves(0, 0,
                   1, 0,
                   0, 1,
                   1, 1,
                   0, 2);
        assertEquals(true, game.isOver());
    }

    @Test
    public void gameShouldEndWhenThereAreNoMoreMoves() throws Exception {
        givenMoves(0, 0, 0, 1,
                   0, 2, 1, 1,
                   1, 0, 2, 0,
                   2, 1, 2, 2,
                   1, 2);
        assertEquals(true, game.isOver());
    }

}
