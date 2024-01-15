package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Knight extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Knight(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        final int pieceRow = myPosition.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();


        //TODO: Check for board boundaries


        int right2 = pieceRow + 2;
        int right1 = pieceRow + 1;
        int left2 = pieceRow - 2;
        int left1 = pieceRow -1;

        int up1 = pieceCol + 1;
        int up2 = pieceCol + 2;
        int down1 = pieceCol - 1;
        int down2 = pieceCol - 2;

        //right 2 up 1
        ChessPosition r2u1 = new ChessPosition(right2,up1);
        ChessMove r2u1Move = new ChessMove(startPosition, r2u1, null);
        possibleMoves.add(r2u1Move);

        //right 1 up 2
        ChessPosition r1u2 = new ChessPosition(right1,up2);
        ChessMove r1u2Move = new ChessMove(startPosition, r1u2, null);
        possibleMoves.add(r1u2Move);

        //left 1 up 2
        ChessPosition l1u2 = new ChessPosition(left1,up2);
        ChessMove l1u2Move = new ChessMove(startPosition, l1u2, null);
        possibleMoves.add(l1u2Move);

        //left 2 up 1
        ChessPosition l2u1 = new ChessPosition(left2,up1);
        ChessMove l2u1Move = new ChessMove(startPosition, l2u1, null);
        possibleMoves.add(l2u1Move);

        //left 2 down 1
        ChessPosition l2d1 = new ChessPosition(left2,down1);
        ChessMove l2d1Move = new ChessMove(startPosition, l2d1, null);
        possibleMoves.add(l2d1Move);

        //left 1 down 2
        ChessPosition l1d2 = new ChessPosition(left1,down2);
        ChessMove l1d2Move = new ChessMove(startPosition, l1d2, null);
        possibleMoves.add(l1d2Move);

        //right 1 down 2
        ChessPosition r1d2 = new ChessPosition(right1,down2);
        ChessMove r1d2Move = new ChessMove(startPosition, r1d2, null);
        possibleMoves.add(r1d2Move);

        //right 2 down 1
        ChessPosition r2d1 = new ChessPosition(right2,down1);
        ChessMove r2d1Move = new ChessMove(startPosition, r2d1, null);
        possibleMoves.add(r2d1Move);

        return possibleMoves;
    }


}
