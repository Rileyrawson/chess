package chess;

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
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {  //position = row,col
        //throw new RuntimeException("Not implemented");

        int row = position.getRow(); //-1 because the board starts at 1 not 0
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
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        throw new RuntimeException("Not implemented");
    }

    //TODO: create toString function that prints out what the board looks like

}



