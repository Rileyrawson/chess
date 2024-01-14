import chess.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);


//        // check bishop
//        ChessBoard board = new ChessBoard();
//        ChessPosition position1 = new ChessPosition(5,4) ;
//        Bishop bishop = new Bishop(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
//        board.addPiece(position1, bishop);
//        // ArrayList<ChessMove> moves = bishop.checkBishopMoves(position1);
//        //System.out.print(board.toString());



//        //check rook
//        ChessBoard board = new ChessBoard();
//        ChessPosition position1 = new ChessPosition(5,4) ;
//        Rook rook = new Rook(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
//        board.addPiece(position1, rook);

        //check king
        ChessBoard board = new ChessBoard();
        ChessPosition position1 = new ChessPosition(5,4) ;
        King king = new King(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        board.addPiece(position1, king);


    }
}