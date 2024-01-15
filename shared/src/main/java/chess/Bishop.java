package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Bishop extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Bishop(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bishop bishop = (Bishop) o;
        return Arrays.equals(possibleMoves, bishop.possibleMoves);
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

        // check right & up
        int col = pieceCol;
        int row = pieceRow;
        System.out.print("\nru: ");
        while ((0 <= row && row < 7) && (0 <= col && col < 7)){
            row++; // up
            col++; // right
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        row = pieceRow;
        col = pieceCol;
        //check right & down
        System.out.print("\nrd: ");
        while ((0 < row && row < 7) && (0 <= col && col <= 7)){
            row--; // down
            col++; // right
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        row = pieceRow;
        col = pieceCol;
        //check left & down
        System.out.print("\nld: ");
        while ((0 < row && row <= 7) && (0 < col && col <= 7)){
            row--; // down
            col--; // left
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );

        }

        row = pieceRow;
        col = pieceCol;
        //check left & up
        System.out.print("\nlu: ");
        while ((0 <= row && row < 7) && (0 < col && col <= 7)){
            row++; // up
            col--; // left
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        return possibleMoves;
    }


    public String toString (){
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }

}
