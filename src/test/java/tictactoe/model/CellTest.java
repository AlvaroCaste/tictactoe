package tictactoe.model;

import org.junit.Test;
import tictactoe.model.Cell;

import static org.junit.Assert.assertEquals;

public class CellTest {

    @Test
    public void cellShouldBeConvertedToString() throws Exception {
        assertEquals("[0,0]", new Cell(0, 0).toString());
        assertEquals("[1,2]", new Cell(1, 2).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cellRowShouldBeNonNegative() throws Exception {
        new Cell(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cellColShouldBeNonNegative() throws Exception {
        new Cell(0, -1);
    }
}
