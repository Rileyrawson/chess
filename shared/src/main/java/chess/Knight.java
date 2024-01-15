package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Knight extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Knight(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return Arrays.equals(possibleMoves, knight.possibleMoves);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(possibleMoves);
    }

    /*
     TODO: blocked by same team
     */

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

        final int pieceRow = myPosition.getRow();
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
        System.out.println("pieceRow: " + pieceRow);
        System.out.println("pieceCol: " + pieceCol);

        int right2 = pieceCol + 2;
        int right1 = pieceCol + 1;
        int left2 = pieceCol - 2;
        int left1 = pieceCol -1;

        int up2 = pieceRow + 2;
        int up1 = pieceRow + 1;
        int down2 = pieceRow - 2;
        int down1 = pieceRow - 1;

        System.out.println("r2: " + right2);
        System.out.println("l1: " + right1);
        System.out.println("l2: " + left2);
        System.out.println("l1: " + left1);

        System.out.println("u2: " + up2);
        System.out.println("u1: " + up1);
        System.out.println("d2: " + down2);
        System.out.println("d1: " + down1);


        //up 1 right 2
        if (up1 < 8 && right2 < 8){
            ChessPosition u1r2 = new ChessPosition(up1 + 1,right2 + 1);
            ChessMove u1r2Moves = new ChessMove(startPosition, u1r2, null);
            possibleMoves.add(u1r2Moves);

        }

        //up 2 right 1
        if (up2 < 8 && right1 < 8){
            ChessPosition u2r1 = new ChessPosition(up2 + 1,right1 + 1);
            ChessMove u2r1Move = new ChessMove(startPosition, u2r1, null);
            possibleMoves.add(u2r1Move);
        }

        //up 2 left 1
        if (up2 < 8 && left1 >= 0){
            ChessPosition u2l1 = new ChessPosition(up2 + 1, left1 + 1);
            ChessMove u2l1Move = new ChessMove(startPosition, u2l1, null);
            possibleMoves.add(u2l1Move);
        }

        //up 1  left 2
        if (up1 < 8 && left2 >= 0){
            ChessPosition u1l2 = new ChessPosition(up1 + 1, left2 + 1);
            ChessMove u1l2Move = new ChessMove(startPosition, u1l2, null);
            possibleMoves.add(u1l2Move);
        }

        //down 1 left 2
        if (down1 > 0 && left2 >= 0){
            ChessPosition d1l2 = new ChessPosition(down1 + 1,left2 + 1);
            ChessMove d1l2Move = new ChessMove(startPosition, d1l2, null);
            possibleMoves.add(d1l2Move);
        }

        //down 2 left 1
        if (down2 >= 0 && left1 >= 0){
            ChessPosition d2l1 = new ChessPosition(down2 + 1,left1 + 1);
            ChessMove d2l1Move = new ChessMove(startPosition, d2l1, null);
            possibleMoves.add(d2l1Move);
        }

        //down 2 right 1
        if (down2 >= 0 && right1 < 8){
            ChessPosition d2r1 = new ChessPosition(down2 + 1,right1 + 1);
            ChessMove d2r1Move = new ChessMove(startPosition, d2r1, null);
            possibleMoves.add(d2r1Move);
        }

        //down 1 right 2
        if (down1 >= 0 && right2 < 8 ){
            ChessPosition d1r2 = new ChessPosition(down1 + 1,right2 + 1);
            ChessMove d1r2Move = new ChessMove(startPosition, d1r2, null);
            possibleMoves.add(d1r2Move);
        }

        return possibleMoves;
    }


}
