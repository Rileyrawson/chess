package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Pawn(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }
//    public ArrayList<ChessMove> checkPawnMoves(ChessPosition position) {
//        ChessPosition startPosition = new ChessPosition(position.getRow(), position.getColumn());
//
//        final int pieceRow = position.getRow(); //gets current row. Will not change
//        final int pieceCol = position.getColumn();
//        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
//
//        //super.pieceColor;
//
//        //White up
//        if (this.getTeamColor() == ChessGame.TeamColor.WHITE){
//            if (this.isFirstMove() == true && pieceRow == 2) { //white first move = up 2
//                ChessPosition movePosition = new ChessPosition(pieceRow + 2,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, null);
//                possibleMoves.add(move);
//                this.isFirstMove() = false;
//            }
//            else if (pieceRow < 8){ //white move up 1
//                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, null);
//                possibleMoves.add(move);
//            }
//            else if (pieceRow == 8) {//promotion on row 8
//                ChessPosition movePosition = new ChessPosition(pieceRow + 8,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
//                possibleMoves.add(move);
//            }
//        }
//
//        //Black down
//        if (this.getTeamColor() == ChessGame.TeamColor.WHITE){
//            if (this.isFirstMove() == true && pieceRow == 7) { //black first move = down 2
//                ChessPosition movePosition = new ChessPosition(pieceRow - 2 ,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, null);
//                possibleMoves.add(move);
//                this.isFirstMove() = false;
//            }
//            else if (pieceRow > 1){ //black move down 1
//                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, null);
//                possibleMoves.add(move);
//            }
//            else if (pieceRow == 1) {//promotion on row 1
//                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
//                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
//                possibleMoves.add(move);
//            }
//        }
//        return possibleMoves;
//    }
}
