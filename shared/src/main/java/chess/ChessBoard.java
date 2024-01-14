package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {


    private final int row = 8;
    private final int col = 8;
    private ChessPiece[][] board;
    //private ChessPiece[][] board = new ChessPiece[8][8];


    public ChessBoard() {
        this.board = new ChessPiece[row][col];
        System.out.print(this.board.toString());
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {  //position = row,col
        int row = position.getRow();
        int col = position.getColumn();
        this.board[row][col] = piece; // piece object holds color and type. linking the piece to the "spot" on the board
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return this.board[position.getRow()][position.getColumn()];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = row-1; i >= 0; i--) {
            stringBuilder.append("\n");
            for (int j = 0; j < col; j++) {
                ChessPosition position = new ChessPosition(i + 1,j + 1);
                if (getPiece(position) == null) {
                    int stringLabel = j + 1;
                    stringBuilder.append(" [" + stringLabel + "] ");
                }
                else {
                    stringBuilder.append(getPiece(position).toString());
                }
            }
            stringBuilder.append(i + 1);
        }
        return stringBuilder.toString();
    }
}