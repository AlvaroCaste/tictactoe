package tictactoe.view;

import org.junit.Test;
import tictactoe.model.Cell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CellValidatorTest {

    private final Validator<Cell> cellValidator = new CellValidator();

    @Test
    public void shouldReturnCell() throws Exception {
        assertEquals(new Cell(0, 0), cellValidator.validate("0,0").get());
        assertEquals(new Cell(0, 1), cellValidator.validate("0,1").get());
    }

    @Test
    public void shouldReturnEmpty() throws Exception {
        assertFalse(cellValidator.validate("x").isPresent());
        assertFalse(cellValidator.validate(",,,").isPresent());
        assertFalse(cellValidator.validate("xy,x").isPresent());
    }
}