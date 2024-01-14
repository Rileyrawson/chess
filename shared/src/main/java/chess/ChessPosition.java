package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {


    private int row;
    private int col;


    public ChessPosition(int row, int col) { //setter
        this.row = row; // this.row refers to the class row & row is the value being passed into the function. we're populating the class row with a value to "get" in functions below
        this.col = col;
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
