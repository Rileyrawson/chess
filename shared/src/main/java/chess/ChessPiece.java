package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.Queen.checkQueenMoves;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    protected PieceType type;
    private boolean firstMove = true;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }
    public boolean isFirstMove() {  //checks if pawn has moved before (use with rook and king later)
        return firstMove;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //possibleMoves = Bishop.checkBishopMoves(myPosition);
        //possibleMoves = Rook.checkRookMoves(myPosition);
        //possibleMoves = King.checkKingMoves(myPosition);
        //possibleMoves = Queen.checkQueenMoves(myPosition);
        //possibleMoves = Knight.checkKnightMoves(myPosition);

        possibleMoves = checkPawnMoves(myPosition);


        return possibleMoves;
        //return new ArrayList<>();
        //throw new RuntimeException("Not implemented");
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        String typeName;

        if (this.type == PieceType.KING){
            typeName = " [K] ";
        } else if (this.type == PieceType.QUEEN) {
            typeName = " [Q] ";
        } else if (this.type == PieceType.BISHOP) {
            typeName = " [B] ";
        } else if (this.type == PieceType.KNIGHT) {
            typeName = " [K] ";
        } else if (this.type == PieceType.ROOK){
            typeName = " [R] ";
        } else if (this.type == PieceType.PAWN) {
            typeName = " [P] ";
        } else {
            typeName = "TYPE ERROR";
        }

        if (this.pieceColor == ChessGame.TeamColor.BLACK) {
            return typeName.toLowerCase();
        } else {
            return typeName;
        }
    }


    public ArrayList<ChessMove> checkPawnMoves(ChessPosition position) {
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int pieceRow = position.getRow(); //gets current row. Will not change
        final int pieceCol = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //super.pieceColor;

        //White up
        if (this.getTeamColor() == ChessGame.TeamColor.WHITE){
            if (this.isFirstMove() == true && pieceRow == 2) { //white first move = up 2
                ChessPosition movePosition = new ChessPosition(pieceRow + 2,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
                firstMove = false;
            }
            else if (pieceRow < 8){ //white move up 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
            }
            else if (pieceRow == 8) {//promotion on row 8
                ChessPosition movePosition = new ChessPosition(pieceRow + 8,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                possibleMoves.add(move);
            }
        }

        //Black down
        if (this.getTeamColor() == ChessGame.TeamColor.BLACK){
            if (this.isFirstMove() == true && pieceRow == 7) { //black first move = down 2
                ChessPosition movePosition = new ChessPosition(pieceRow - 2 ,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
                firstMove = false;
            }
            else if (pieceRow > 1){ //black move down 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
            }
            else if (pieceRow == 1) {//promotion on row 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
}