package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private ChessPosition startPosition;
    private ChessPosition endPosition;
    private ChessPiece.PieceType promotionPiece;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.startPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promotionPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChessMove chessMove)) return false;
        return chessMove.hashCode() == hashCode();
//        return (getStartPosition().equals(chessMove.getStartPosition()) && getEndPosition().equals(chessMove.getEndPosition())) && (getPromotionPiece() == chessMove.getPromotionPiece());
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = prime * (getStartPosition().getRow() + 2 ) * (getStartPosition().getColumn() + 2);
        result = result * prime * (getEndPosition().getRow() + 2 ) * (getEndPosition().getColumn() + 2);

        if (getPromotionPiece() == ChessPiece.PieceType.ROOK){
            result = result * 2;
        }
        if (getPromotionPiece() == ChessPiece.PieceType.BISHOP){
            result = result * 3;
        }
        if (getPromotionPiece() == ChessPiece.PieceType.QUEEN){
            result = result * 4;
        }
        if (getPromotionPiece() == ChessPiece.PieceType.KNIGHT){
            result = result * 5;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(startPosition.getRow());
        sb.append(",");
        sb.append(startPosition.getColumn());
        sb.append(")");
        sb.append("->");
        sb.append("(");
        sb.append(endPosition.getRow());
        sb.append(",");
        sb.append(endPosition.getColumn());
        sb.append(")");
        sb.append(promotionPiece);
        return sb.toString();
    }
}