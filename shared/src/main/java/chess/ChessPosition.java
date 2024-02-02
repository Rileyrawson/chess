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

    // auto-generated equals and hash code override functions. Sorts values into correct order so when comparing, actually compares value
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPosition that)) return false;
        return getRow() == that.getRow() && getColumn() == that.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), col);
    }

    public ChessPosition(int row, int col) {
        this.row = row - 1; // this.row refers to the class row & row is the value being passed into the function. we're populating the class row with a value to "get" in functions below
        this.col = col - 1; // -1 to account for arrays starting at 0
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return this.col;
    }

    public enum Direction {
        UP,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN,
        DOWN_LEFT,
        LEFT,
        UP_LEFT
    }

    public void move(Direction direction){  // helper function calculates the change in row & col depending on direction
        switch (direction){
            case UP -> moveUp();
            case UP_RIGHT -> moveUpRight();
            case RIGHT -> moveRight();
            case DOWN_RIGHT -> moveDownRight();
            case DOWN -> moveDown();
            case DOWN_LEFT -> moveDownLeft();
            case LEFT -> moveLeft();
            case UP_LEFT -> moveUpLeft();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    private void moveUp(){
        this.row++;
    }

    private void moveUpRight(){
        this.row++;
        this.col++;
    }

    private void moveRight(){
        this.col++;
    }

    private void moveDownRight(){
        this.row--;
        this.col++;
    }

    private void moveDown(){
        this.row--;
    }

    private void moveDownLeft(){
        this.row--;
        this.col--;
    }
    private void moveLeft(){
        this.col--;
    }
    private void moveUpLeft(){
        this.row++;
        this.col--;
    }

}
