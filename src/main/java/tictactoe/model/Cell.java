package tictactoe.model;

public class Cell {

    public final int row;
    public final int col;

    public Cell(int row, int col) {
        if (row < 0 || col < 0)
            throw new IllegalArgumentException("Coordinates cannot be negative: " + this);
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", row, col);
    }
}
