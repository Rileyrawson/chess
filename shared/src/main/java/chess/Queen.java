package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Queen extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Queen(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return Arrays.equals(possibleMoves, queen.possibleMoves);
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
        System.out.print("\nr: ");

        int straightRow = pieceRow;
        int starightCol = pieceCol;
        while ((0 <= starightCol && starightCol < 7)){
            starightCol++;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + straightRow + "," + starightCol + ")" );
        }

        //right up
        System.out.print("\nru: ");

        int row = pieceRow;
        int col = pieceCol;
        while ((0 <= row && row < 7) && (0 <= col && col < 7)){
            row++;
            col++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );

        }

        //up
        System.out.print("\nu: ");

        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((0 <= straightRow && straightRow < 7)){
            straightRow++;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + straightRow + "," + starightCol + ")" );
        }

        //left up
        System.out.print("\nlu: ");

        row = pieceRow;
        col = pieceCol;
        while ((0 <= row && row < 7) && (0 < col && col <= 7)){
            row++;
            col--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        //left
        System.out.print("\nl: ");

        straightRow = pieceRow;
        starightCol = pieceCol;
        while (0 < starightCol && starightCol <= 7){
            starightCol--;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + straightRow + "," + starightCol + ")" );
        }

        //left down
        System.out.print("\nld: ");

        row = pieceRow;
        col = pieceCol;
        while ((0 < row && row <= 7) && (0 < col && col <= 7)){
            row--;
            col--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        //down
        System.out.print("\nd: ");

        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((0 < straightRow && straightRow <= 7)){
            straightRow--;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + straightRow + "," + starightCol + ")" );
        }

        //down right
        System.out.print("\nrd: ");

        row = pieceRow;
        col = pieceCol;
        while ((0 < row && row <= 7) && (0 <= col && col < 7)){
            row--;
            col++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
            System.out.print("(" + row + "," + col + ")" );
        }

        return possibleMoves;
    }


}
