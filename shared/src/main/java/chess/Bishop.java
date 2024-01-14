package chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Bishop(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    public static ArrayList<ChessMove> checkBishopMoves(ChessPosition position){
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int pieceRow = position.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        // check right & up
        int row = pieceRow;
        int col = pieceCol;

        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row++;
            col++;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        row = pieceRow;
        col = pieceCol;
        //check right & down
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row--;
            col++;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        row = pieceRow;
        col = pieceCol;
        //check left & down
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row--;
            col--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        row = pieceRow;
        col = pieceCol;
        //check left & up
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row++;
            col--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        return possibleMoves;
    }

    public String toString (){
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }

}
