import ui.Input;
import ui.PreloginUI;

public class Main {
    public static void main(String[] args) {
//        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        System.out.println("♕ 240 Chess Client: " + piece);

        try {
            Input.input();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}