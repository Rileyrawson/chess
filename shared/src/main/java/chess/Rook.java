package chess;

import java.util.ArrayList;

public class Rook extends ChessPiece{
    ChessPiece.PieceType type;
    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Rook(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    public static ArrayList<ChessMove> checkRookMoves(ChessPosition position){
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int pieceRow = position.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //right
        int row = pieceRow;
        int col = pieceCol;
        while ((1 < col && col < 8)){
            col++;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //left
        row = pieceRow;
        col = pieceCol;
        while (1 < col && col < 8){
            col--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //down
        row = pieceRow;
        col = pieceCol;

        while ((1 < row && row < 8)){
            row--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //up
        row = pieceRow;
        col = pieceCol;
        while ((1 < row && row < 8)){
            row++;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }



        return possibleMoves;
    }


}
