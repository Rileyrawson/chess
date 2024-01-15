package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class King extends ChessPiece{
    private ChessPiece[][] possibleMoves = new ChessPiece[7][7];

    public King(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        King king = (King) o;
        return Arrays.equals(possibleMoves, king.possibleMoves);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(possibleMoves);
    }

    /*
     TODO: capture
     TODO: blocked by same team
     */

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

        final int row = myPosition.getRow(); //gets current row of bishop. Will not change
        final int col = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        int rowUp = row + 1;
        int rowDown = row - 1;
        int colRight = col + 1;
        int colLeft = col - 1;

        //up
        //row + 1
        if (row < 7){
            ChessPosition up = new ChessPosition(rowUp + 1,col + 1);
            ChessMove upMove = new ChessMove(startPosition, up, null);
            possibleMoves.add(upMove);
        }

        //right up
        //col + 1 row + 1
        if (row < 7 && col < 7){
            ChessPosition rightUp = new ChessPosition(rowUp + 1,colRight + 1);
            ChessMove rightUpMove = new ChessMove(startPosition, rightUp, null);
            possibleMoves.add(rightUpMove);
        }

        //right
        //col + 1
        if (col < 7){
            ChessPosition right = new ChessPosition(row + 1,colRight + 1);
            ChessMove rightMove = new ChessMove(startPosition, right, null);
            possibleMoves.add(rightMove);
        }

        //right down
        //col - 1 row + 1
        if (row < 7 && col > 0){
            ChessPosition rightDown = new ChessPosition(rowDown + 1,colRight + 1);
            ChessMove rightDownMove = new ChessMove(startPosition, rightDown, null);
            possibleMoves.add(rightDownMove);
        }

        //down
        //row - 1
        if(row > 0 && col > 0){
            ChessPosition down = new ChessPosition(rowDown + 1,col + 1);
            ChessMove downMove = new ChessMove(startPosition, down, null);
            possibleMoves.add(downMove);
        }

        //left down
        //col - 1 row - 1
        if(row > 0 && col > 0) {
            ChessPosition leftDown = new ChessPosition(rowDown + 1, colLeft + 1);
            ChessMove leftDownMove = new ChessMove(startPosition, leftDown, null);
            possibleMoves.add(leftDownMove);
        }

        //left
        //col - 1
        if(col > 0){
            ChessPosition left = new ChessPosition(row + 1,colLeft + 1);
            ChessMove leftMove = new ChessMove(startPosition, left, null);
            possibleMoves.add(leftMove);
        }

        //left up
        //col - 1 row + 1
        if(row < 7 && col > 0){
            ChessPosition leftUp = new ChessPosition(rowUp + 1,colLeft + 1);
            ChessMove leftUpMove = new ChessMove(startPosition, leftUp, null);
            possibleMoves.add(leftUpMove);
        }
        return possibleMoves;
    }


}
