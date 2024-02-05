package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private final int boardRow = 8;
    private final int boardCol = 8;
    private ChessPiece[][] board; //the board will consist of an array of chess pieces

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    public ChessPiece[][] getBoard() {
        return this.board;
    }

    public ChessBoard makeMove(ChessBoard newBoard, ChessMove move, ChessPiece piece){
        newBoard.addPiece(move.getStartPosition(), null);
        newBoard.addPiece(move.getEndPosition(), piece);
        return newBoard;
    }

    public ChessBoard() {
        this.board = new ChessPiece[boardRow][boardCol]; //sets board to a ChessPiece 2d array
//        System.out.print(this.board); // print board for debugging
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;
        return Arrays.deepEquals(board, that.board); // deep so checks contents 2d array
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(boardRow, boardCol);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    public ChessPosition getKingPosition(ChessGame.TeamColor teamColor){
        ChessPiece[][] pieceArray = this.board;
        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) {
                if (pieceArray[i][j] != null){ //if this position on the board isn't null
                    if (pieceArray[i][j].getPieceType() == ChessPiece.PieceType.KING && pieceArray[i][j].getTeamColor() == teamColor){ //if king and same team
                        return new ChessPosition(i + 1,j + 1); //iterating through array so already 0 based
                    }
                }
            }
        }
        return null;
    }
    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow();
        int col = position.getColumn();
        this.board[row][col] = piece; // assigns the passed in piece to the designated position in the 2d array
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        if (isOutOfBounds(position)){
            return null;
        }
        return this.board[position.getRow()][position.getColumn()]; // position object holds row and col. Need to call .getrow and .getcol to seperate
    }

    public ChessGame.TeamColor getTeamAtPosition(ChessPosition position){
        if (this.getPiece(position) == null){
            return null;
        }
        return this.getPiece(position).getTeamColor();
    }

    public boolean isOutOfBounds(ChessPosition position){
        int row = position.getRow();
        int col = position.getColumn();
        return ((row < 0 || row >= boardRow) || (col < 0 || col >= boardCol)); //the conditions outside of board
    }

    public boolean isValidMove(ChessPosition endPosition, ChessGame.TeamColor currTeam){
        if (isOutOfBounds(endPosition)){ //out of bounds
            return false;
        } else if (getPiece(endPosition) == null ){ //empty square
            return true;
        } else if (getTeamAtPosition(endPosition) == currTeam) { //blocked by same team
            return false;
        } else{ //capture move
            return true;
        }
    }

    public boolean isCapturePosition(ChessPosition endPosition, ChessGame.TeamColor currTeam){
        if (!isValidMove(endPosition,currTeam)){ // false == out of bounds or blocked by same team
            return false;
        } else if (getPiece(endPosition) == null) { //empty square
            return false;
        }
        return true; //other piece is at endPosition
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        //White
        //pawn = row 2
        for (int i = 1; i < 9; i++){
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(2,i);
            addPiece(position, newPiece);
        }

        //rook 1,1 & 1,8
        ChessPiece whiteRook1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition whiteRookPosition1 = new ChessPosition(1,1);
        addPiece(whiteRookPosition1, whiteRook1);
        ChessPiece whiteRook2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition whiteRookPosition2 = new ChessPosition(1,8);
        addPiece(whiteRookPosition2, whiteRook2);

        //kight 1,2 & 1,7
        ChessPiece whiteKnight1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition whiteKnightPosition1 = new ChessPosition(1,2);
        addPiece(whiteKnightPosition1, whiteKnight1);
        ChessPiece whiteKnight2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition whiteKnightPosition2 = new ChessPosition(1,7);
        addPiece(whiteKnightPosition2, whiteKnight2);

        //bishop 1,3 & 1,6
        ChessPiece whiteBishop1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition whiteBishopPosition1 = new ChessPosition(1,3);
        addPiece(whiteBishopPosition1, whiteBishop1);
        ChessPiece whiteBishop2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition whiteBishopPosition2 = new ChessPosition(1,6);
        addPiece(whiteBishopPosition2, whiteBishop2);

        //queen 1,4
        ChessPiece whiteQueen1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPosition whiteQueenPosition1 = new ChessPosition(1,4);
        addPiece(whiteQueenPosition1, whiteQueen1);

        //king 1,5
        ChessPiece whiteKing1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPosition whiteKingPosition1 = new ChessPosition(1,5);
        addPiece(whiteKingPosition1, whiteKing1);


        //black

        //row 7 = pawn
        for (int i = 1; i < 9; i++){
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(7,i);
            addPiece(position, newPiece);
        }

        //rook 8,1 & 8,8
        ChessPiece bRook1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition bRookPosition1 = new ChessPosition(8,1);
        addPiece(bRookPosition1, bRook1);
        ChessPiece bRook2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition bRookPosition2 = new ChessPosition(8,8);
        addPiece(bRookPosition2, bRook2);

        //kight 8,2 & 8,7
        ChessPiece bKnight1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition bKnightPosition1 = new ChessPosition(8,2);
        addPiece(bKnightPosition1, bKnight1);
        ChessPiece bKnight2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition bKnightPosition2 = new ChessPosition(8,7);
        addPiece(bKnightPosition2, bKnight2);

        //bishop 8,3 & 8,6
        ChessPiece bBishop1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition bBishopPosition1 = new ChessPosition(8,3);
        addPiece(bBishopPosition1, bBishop1);
        ChessPiece bBishop2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition bBishopPosition2 = new ChessPosition(8,6);
        addPiece(bBishopPosition2, bBishop2);

        //queen 8,4
        ChessPiece bQueen1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        ChessPosition bQueenPosition1 = new ChessPosition(8,4);
        addPiece(bQueenPosition1, bQueen1);

        //king 8,5
        ChessPiece bKing1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        ChessPosition bKingPosition1 = new ChessPosition(8,5);
        addPiece(bKingPosition1, bKing1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = boardRow - 1; i >= 0; i--) {
            stringBuilder.append("\n");
            for (int j = 0; j < boardCol; j++) {
                ChessPosition position = new ChessPosition(i + 1,j + 1);
                if (getPiece(position) == null) {
                    stringBuilder.append(" [").append(j + 1).append("] "); // label the column number
                }
                else {
                    stringBuilder.append(getPiece(position).toString());
                }
            }
            stringBuilder.append(i + 1); // label the row number
        }
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }
}
