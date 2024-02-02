package chess;

import chess.pieces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;
    private boolean firstMove = true; // check if piece has moved yet. For pawn (rook and king later)
    private PieceMovesCalculator movesCalculator;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) { // constructor - creating ChessPiece object
        this.pieceColor = pieceColor;
        this.type = type;

        // decides what type of pieceMovesCalculator to use and sets local variable movesCalculator
        switch (type){
            case KING -> this.movesCalculator = new KingMovesCalculator();
            case QUEEN -> this.movesCalculator = new QueenMovesCalculator();
            case BISHOP -> this.movesCalculator = new BishopMovesCalculator();
            case KNIGHT -> this.movesCalculator = new KnightMovesCalculator();
            case ROOK -> this.movesCalculator = new RookMovesCalculator();
            case PAWN -> this.movesCalculator = new PawnMovesCalculator();
            default -> throw new RuntimeException("type error");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessPiece piece)) return false;
        return firstMove == piece.firstMove && pieceColor == piece.pieceColor && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type, firstMove);
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

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return firstMove;
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
        return movesCalculator.pieceMoves(board, myPosition);
    }

    public static void recursiveMoves(ChessBoard board, ChessPosition startPosition, ChessPosition.Direction direction, Collection<ChessPosition> possiblePositions, ChessGame.TeamColor currTeam){
        ChessPosition nextPosition = new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1); //initialize to same position as startPosition to begin with. +1 to switch to base 1 not base 0
        nextPosition.move(direction);
        if (board.isValidMove(nextPosition, currTeam)){ // if the move is valid (not out of bounds or blocked by same team) then add to array of possible positions
            possiblePositions.add(nextPosition);
            if (!board.isCapturePosition(nextPosition,currTeam)){ // if capture position break, else if not capture position continue until oob, blocked, or capture
                recursiveMoves(board, nextPosition, direction, possiblePositions, currTeam); //do again. this time the nextPosition becomes the startPosition
            }
        }
    }

    public static Collection<ChessMove> convertPositionsToMoves(ChessPosition startPosition, Collection<ChessPosition> possiblePositions){
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
        for (ChessPosition position : possiblePositions){ // iterates through every possible position in possiblePositions ArrayList
            ChessMove move = new ChessMove(startPosition, position, null);
            possibleMoves.add(move);
        }
        return possibleMoves;
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
