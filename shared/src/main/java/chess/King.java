package chess;

import java.util.ArrayList;

public class King extends ChessPiece{
    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public King(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    public static ArrayList<ChessMove> checkKingMoves(ChessPosition position){
        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());

        final int row = position.getRow(); //gets current row of bishop. Will not change
        final int col = position.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();


        int rowUp = row + 1;
        int rowDown = row - 1;
        int colRight = col + 1;
        int colLeft = col - 1;

        //up
        //row + 1
        ChessPosition up = new ChessPosition(rowUp,col);
        ChessMove upMove = new ChessMove(startPosition, up, null);
        possibleMoves.add(upMove);

        //right up
        //col + 1 row + 1
        ChessPosition rightUp = new ChessPosition(rowUp,colRight);
        ChessMove rightUpMove = new ChessMove(startPosition, rightUp, null);
        possibleMoves.add(rightUpMove);

        //right
        //col + 1
        ChessPosition right = new ChessPosition(row,colRight);
        ChessMove rightMove = new ChessMove(startPosition, right, null);
        possibleMoves.add(rightMove);

        //right down
        //col - 1 row + 1
        ChessPosition rightDown = new ChessPosition(rowDown,colRight);
        ChessMove rightDownMove = new ChessMove(startPosition, rightDown, null);
        possibleMoves.add(rightDownMove);

        //down
        //row - 1
        ChessPosition down = new ChessPosition(rowDown,col);
        ChessMove downMove = new ChessMove(startPosition, down, null);
        possibleMoves.add(downMove);

        //left down
        //col - 1 row - 1
        ChessPosition leftDown = new ChessPosition(colLeft,rowDown);
        ChessMove leftDownMove = new ChessMove(startPosition, leftDown, null);
        possibleMoves.add(leftDownMove);

        //left
        //col - 1
        ChessPosition left = new ChessPosition(row,colLeft);
        ChessMove leftMove = new ChessMove(startPosition, left, null);
        possibleMoves.add(leftMove);

        //left up
        //col - 1 row + 1
        ChessPosition leftUp = new ChessPosition(rowUp,colLeft);
        ChessMove leftUpMove = new ChessMove(startPosition, leftUp, null);
        possibleMoves.add(leftUpMove);




        return possibleMoves;
    }




}
