package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
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
        possibleMoves = King.checkKingMoves(myPosition);

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

}