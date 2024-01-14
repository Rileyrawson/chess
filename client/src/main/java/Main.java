import chess.*;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);

        ChessBoard board = new ChessBoard();
        ChessPosition position = new ChessPosition(4,4) ;
        ChessPiece piece2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board.addPiece( position, piece2);
        System.out.print(board.toString());
    }
}