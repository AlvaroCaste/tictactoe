package tictactoe.model;

public enum Player {
    O, X;

    public Player getNext() {
        return (this == Player.X) ? Player.O : Player.X;
    }
}
