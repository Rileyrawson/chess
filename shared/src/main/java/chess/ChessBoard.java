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
    private ChessPiece[][] board;
    //private ChessPiece[][] board = new ChessPiece[8][8];


    public ChessBoard() {
        this.board = new ChessPiece[boardRow][boardCol];
        System.out.print(this.board.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessBoard that)) return false;
        return boardRow == that.boardRow && boardCol == that.boardCol && Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(boardRow, boardCol);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
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

//        if (piece.getPieceType() == ChessPiece.PieceType.KING){ //if the piece being passed in is a king...
//            piece = new King(piece.getTeamColor(), piece.getPieceType());
//        } else if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) { //then make new King object and call King constructor
//            piece = new Queen(piece.getTeamColor(), piece.getPieceType());
//        } else if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
//            piece = new Bishop(piece.getTeamColor(), piece.getPieceType());
//        } else if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
//            piece = new Knight(piece.getTeamColor(), piece.getPieceType());
//        } else if (piece.getPieceType() == ChessPiece.PieceType.ROOK){
//            piece = new Rook(piece.getTeamColor(), piece.getPieceType());
//        } else if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
//            piece = new Pawn(piece.getTeamColor(), piece.getPieceType());
//        } else {
//            throw new RuntimeException("type error");
//        }

        this.board[row][col] = piece; // piece object holds color and type. linking the piece to the "spot" on the board

    }

    public ChessGame.TeamColor colorAtPosition (ChessPosition position){
        if (getPiece(position) == null){
            return null;
        }
        return getPiece(position).getTeamColor();
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

        //White
        //pawn = row 1
        for (int i = 1; i < 9; i++){
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(2,i);
            addPiece(position, newPiece);
        }

        //rook 0,0 & 0,7
        ChessPiece whiteRook1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition whiteRookPosition1 = new ChessPosition(1,1);
        addPiece(whiteRookPosition1, whiteRook1);
        ChessPiece whiteRook2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPosition whiteRookPosition2 = new ChessPosition(1,8);
        addPiece(whiteRookPosition2, whiteRook2);

        //kight 0,1 & 0,6
        ChessPiece whiteKnight1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition whiteKnightPosition1 = new ChessPosition(1,2);
        addPiece(whiteKnightPosition1, whiteKnight1);
        ChessPiece whiteKnight2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPosition whiteKnightPosition2 = new ChessPosition(1,7);
        addPiece(whiteKnightPosition2, whiteKnight2);

        //bishop 0,2 & 0,5
        ChessPiece whiteBishop1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition whiteBishopPosition1 = new ChessPosition(1,3);
        addPiece(whiteBishopPosition1, whiteBishop1);
        ChessPiece whiteBishop2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPosition whiteBishopPosition2 = new ChessPosition(1,6);
        addPiece(whiteBishopPosition2, whiteBishop2);

        //queen  0,3
        ChessPiece whiteQueen1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPosition whiteQueenPosition1 = new ChessPosition(1,4);
        addPiece(whiteQueenPosition1, whiteQueen1);

        //king 0,4
        ChessPiece whiteKing1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPosition whiteKingPosition1 = new ChessPosition(1,5);
        addPiece(whiteKingPosition1, whiteKing1);


        //black

        //row 6 = pawn
        for (int i = 1; i < 9; i++){
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPosition position = new ChessPosition(7,i);
            addPiece(position, newPiece);
        }

        //rook 7,0 & 7,7
        ChessPiece bRook1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition bRookPosition1 = new ChessPosition(8,1);
        addPiece(bRookPosition1, bRook1);
        ChessPiece bRook2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPosition bRookPosition2 = new ChessPosition(8,8);
        addPiece(bRookPosition2, bRook2);

        //kight 7,1 & 7,6
        ChessPiece bKnight1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition bKnightPosition1 = new ChessPosition(8,2);
        addPiece(bKnightPosition1, bKnight1);
        ChessPiece bKnight2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPosition bKnightPosition2 = new ChessPosition(8,7);
        addPiece(bKnightPosition2, bKnight2);

        //bishop 7,2 & 7,5
        ChessPiece bBishop1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition bBishopPosition1 = new ChessPosition(8,3);
        addPiece(bBishopPosition1, bBishop1);
        ChessPiece bBishop2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPosition bBishopPosition2 = new ChessPosition(8,6);
        addPiece(bBishopPosition2, bBishop2);

        //queen 7,4
        ChessPiece bQueen1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        ChessPosition bQueenPosition1 = new ChessPosition(8,4);
        addPiece(bQueenPosition1, bQueen1);

        //king  7,3
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