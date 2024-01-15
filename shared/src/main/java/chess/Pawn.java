package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Pawn extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Pawn(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return Arrays.equals(possibleMoves, pawn.possibleMoves);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(possibleMoves);
    }


    /*
     TODO: fix promotion
     TODO: blocked path (in front any & diagonal same team) (single & double move)
     TODO: diagonal capture
     */
    public boolean isSameColor(ChessBoard board, ChessPosition piecePosition, ChessPosition movePosition){
        if (board.colorAtPosition(piecePosition) == board.colorAtPosition(movePosition)){
            return true;
        }
        return false;
    }
    public boolean pieceAtPosition(ChessBoard board, ChessPosition movePosition){
        ChessPiece piece = board.getPiece(movePosition);
        return piece != null; //if there is a piece return true. if no piece at position return false
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

        final int pieceRow = myPosition.getRow(); //gets current row. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //White up
        if (getTeamColor() == ChessGame.TeamColor.WHITE){
            if (isFirstMove() == true && pieceRow == 1) { //white first move = up 2
                ChessPosition singleMovePosition = new ChessPosition(pieceRow + 2,pieceCol + 1);
                ChessMove singleMove = new ChessMove(startPosition, singleMovePosition, null);
                if (!pieceAtPosition(board, singleMovePosition)){ //if there is not a piece at single move position
                    possibleMoves.add(singleMove);
                    ChessPosition doubleMovePosition = new ChessPosition(pieceRow + 3,pieceCol + 1);
                    ChessMove doubleMove = new ChessMove(startPosition, doubleMovePosition, null);
                    if (!pieceAtPosition(board, doubleMovePosition)){ //if there is not a piece at double move position
                        possibleMoves.add(doubleMove);
                    }
                }
                setFirstMove(false);
            } else if (pieceRow < 6){ //white move up 1 (straight forward move)
                ChessPosition movePosition = new ChessPosition(pieceRow + 2,pieceCol + 1);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                if (!pieceAtPosition(board, movePosition)){ //if there is not a piece at double move position
                    possibleMoves.add(move);
                }
                //check diagonal capture
                ChessPosition rightDiagonalPosition = new ChessPosition(pieceRow + 2,pieceCol + 2); // pieceCol + 2 moves right
                ChessMove rightDiagonalMove = new ChessMove(startPosition, rightDiagonalPosition, null);
                if (pieceAtPosition(board, rightDiagonalPosition)){ //if there is a piece at right diagonal move position
                    if (!isSameColor(board, myPosition, rightDiagonalPosition)){ //if the piece is the other team's
                        possibleMoves.add(rightDiagonalMove);
                    }
                }
                ChessPosition leftDiagonalPosition = new ChessPosition(pieceRow + 2,pieceCol ); // pieceCol + 0 moves left
                ChessMove leftDiagonalMove = new ChessMove(startPosition, leftDiagonalPosition, null);
                if (pieceAtPosition(board, leftDiagonalPosition)){ //if there is a piece at left diagonal move position
                    if (!isSameColor(board, myPosition, leftDiagonalPosition)){ //if the piece is the other team's
                        possibleMoves.add(leftDiagonalMove);
                    }
                }
            }
            else if (pieceRow == 6) {//promotion on row 8 (7 in array)
                ChessPosition movePosition = new ChessPosition(8,pieceCol + 1);
                //ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                ChessMove rookPromotion = new ChessMove(startPosition, movePosition, PieceType.ROOK);
                possibleMoves.add(rookPromotion);
                ChessMove bishopPromotion = new ChessMove(startPosition, movePosition, PieceType.BISHOP);
                possibleMoves.add(bishopPromotion);
                ChessMove queenPromotion = new ChessMove(startPosition, movePosition, PieceType.QUEEN);
                possibleMoves.add(queenPromotion);
                ChessMove knightPromotion = new ChessMove(startPosition, movePosition, PieceType.KNIGHT);
                possibleMoves.add(knightPromotion);
            }
        }

        //Black down
        if (this.getTeamColor() == ChessGame.TeamColor.BLACK){
            if (this.isFirstMove() == true && pieceRow == 6) { //black first move = down 2
                ChessPosition singleMovePosition = new ChessPosition(pieceRow + 0,pieceCol + 1);
                ChessMove singleMove = new ChessMove(startPosition, singleMovePosition, null);
                if (!pieceAtPosition(board, singleMovePosition)){ //if there is not a piece at single move position
                    possibleMoves.add(singleMove);
                    ChessPosition doubleMovePosition = new ChessPosition(pieceRow - 1,pieceCol + 1);
                    ChessMove doubleMove = new ChessMove(startPosition, doubleMovePosition, null);
                    if (!pieceAtPosition(board, doubleMovePosition) ){ //if there is not a piece at double move position
                        possibleMoves.add(doubleMove);
                    }
                }
                setFirstMove(false);
            } else if (pieceRow > 1){ //black move down 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 0,pieceCol + 1);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                if (!pieceAtPosition(board, movePosition)){ //if there is not a piece at double move position
                    possibleMoves.add(move);
                }
            }
            //check diagonal capture
            ChessPosition rightDiagonalPosition = new ChessPosition(pieceRow + 0,pieceCol + 2); // pieceCol + 2 moves right
            ChessMove rightDiagonalMove = new ChessMove(startPosition, rightDiagonalPosition, null);
            if (pieceAtPosition(board, rightDiagonalPosition)){ //if there is a piece at right diagonal move position
                if (!isSameColor(board, myPosition, rightDiagonalPosition)){ //if the piece is the other team's
                    possibleMoves.add(rightDiagonalMove);
                }
            }
            ChessPosition leftDiagonalPosition = new ChessPosition(pieceRow + 0, pieceCol); // pieceCol + 0 moves left
            ChessMove leftDiagonalMove = new ChessMove(startPosition, leftDiagonalPosition, null);
            if (pieceAtPosition(board, leftDiagonalPosition)){ //if there is a piece at left diagonal move position
                if (!isSameColor(board, myPosition, leftDiagonalPosition)){ //if the piece is the other team's
                    possibleMoves.add(leftDiagonalMove);
                }
            }

            else if (pieceRow == 1) {//promotion on row 1 (0 in array)
                ChessPosition movePosition = new ChessPosition(1,pieceCol + 1);
//                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                ChessMove queenPromotion = new ChessMove(startPosition, movePosition, PieceType.QUEEN);
                possibleMoves.add(queenPromotion);
                ChessMove bishopPromotion = new ChessMove(startPosition, movePosition, PieceType.BISHOP);
                possibleMoves.add(bishopPromotion);
                ChessMove rookPromotion = new ChessMove(startPosition, movePosition, PieceType.ROOK);
                possibleMoves.add(rookPromotion);
                ChessMove knightPromotion = new ChessMove(startPosition, movePosition, PieceType.KNIGHT);
                possibleMoves.add(knightPromotion);
            }
        }
        return possibleMoves;
    }


}
