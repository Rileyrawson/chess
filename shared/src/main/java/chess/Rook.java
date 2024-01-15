package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Rook extends ChessPiece{
    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Rook(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return Arrays.equals(possibleMoves, rook.possibleMoves);
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

        final int pieceRow = myPosition.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //right
        int row = pieceRow;
        int col = pieceCol;
        while ((0 <= col && col < 7)){
            col++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //left
        row = pieceRow;
        col = pieceCol;
        while (0 < col && col <= 7){
            col--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //down
        row = pieceRow;
        col = pieceCol;
        while ((0 < row && row <= 7)){
            row--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //up
        row = pieceRow;
        col = pieceCol;
        while ((0 <= row && row < 7)){
            row++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        return possibleMoves;
    }




}
