package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {


    private int row;
    private int col;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == null || getClass() != o.getClass()) return false;
        ChessPosition that = (ChessPosition) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public ChessPosition(int row, int col) { //setter
        this.row = row - 1; // this.row refers to the class row & row is the value being passed into the function. we're populating the class row with a value to "get" in functions below
        this.col = col - 1; // -1 to account for arrays starting at 0
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() { //getter
        return this.row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() { //getter
        return this.col;
    }
}