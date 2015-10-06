package tictactoe.model;

import org.junit.Test;
import tictactoe.model.Player;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void shouldKnowTheNextPlayer() throws Exception {
        assertEquals(Player.O, Player.X.getNext());
        assertEquals(Player.X, Player.O.getNext());
    }
}