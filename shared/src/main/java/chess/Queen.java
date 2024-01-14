package chess;

import java.util.ArrayList;

public class Queen extends ChessPiece{

    ChessPiece.PieceType type;
    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Queen(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    public static ArrayList<ChessMove> checkQueenMoves(ChessPosition position) {
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int pieceRow = position.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();



        //right
        int straightRow = pieceRow;
        int starightCol = pieceCol;
        while ((1 < starightCol && starightCol < 8)){
            starightCol++;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //right up
        int row = pieceRow;
        int col = pieceCol;
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row++;
            col++;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //up
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((1 < straightRow && straightRow < 8)){
            straightRow++;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //left up
        row = pieceRow;
        col = pieceCol;
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row++;
            col--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //left
        straightRow = pieceRow;
        starightCol = pieceCol;
        while (1 < starightCol && starightCol < 8){
            starightCol--;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //left down
        row = pieceRow;
        col = pieceCol;
        while ((1 < row && row < 8) && (1 < col && col < 8)){
            row--;
            col--;
            ChessPosition movePosition = new ChessPosition(row,col);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //down
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((1 < straightRow && straightRow < 8)){
            straightRow--;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //down right
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

        return possibleMoves;
    }











    private static ArrayList<ChessMove> checkQueenDiagonalMoves(ChessPosition position) {
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
    private static ArrayList<ChessMove> checkQueenStraightMoves(ChessPosition position) {
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int pieceRow = position.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //STRAIGHT MOVES
        //right
        int straightRow = pieceRow;
        int starightCol = pieceCol;
        while ((1 < starightCol && starightCol < 8)){
            starightCol++;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //left
        straightRow = pieceRow;
        starightCol = pieceCol;
        while (1 < starightCol && starightCol < 8){
            starightCol--;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }


        //down
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((1 < straightRow && straightRow < 8)){
            straightRow--;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        //up
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((1 < straightRow && straightRow < 8)){
            straightRow++;
            ChessPosition movePosition = new ChessPosition(straightRow,starightCol);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            possibleMoves.add(move);
        }

        return possibleMoves;
    }




}
