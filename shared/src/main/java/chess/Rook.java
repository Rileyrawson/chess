package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Rook extends ChessPiece{
    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Rook(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        final int pieceRow = myPosition.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = myPosition.getColumn();
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
