import chess.*;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);

        ChessBoard board = new ChessBoard();
        ChessPosition position1 = new ChessPosition(5,4) ;
        ChessPiece piece1 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board.addPiece(position1, piece1);
        System.out.print(board.toString());
    }
}