package chess.pieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMoves {


    public boolean isSameColor(ChessBoard board, ChessPosition piecePosition, ChessPosition movePosition){
        if (board.colorAtPosition(piecePosition) == board.colorAtPosition(movePosition)){
            return true;
        }
        return false;
    }
    public boolean pieceAtPosition(ChessBoard board, ChessPosition movePosition){
        ChessPiece piece = board.getPiece(movePosition);
        return piece != null; //if there is a piece return true. if no piece at position return false
    }

    public Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition myPosition) {
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
            ChessMove u1r2Move = new ChessMove(startPosition, u1r2, null);
            if (pieceAtPosition(board, u1r2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u1r2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u1r2Move);
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(u1r2Move);
            }
        }

        //up 2 right 1
        if (up2 < 8 && right1 < 8){
            ChessPosition u2r1 = new ChessPosition(up2 + 1,right1 + 1);
            ChessMove u2r1Move = new ChessMove(startPosition, u2r1, null);
            if (pieceAtPosition(board, u2r1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u2r1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u2r1Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(u2r1Move);
            }
        }

        //up 2 left 1
        if (up2 < 8 && left1 >= 0){
            ChessPosition u2l1 = new ChessPosition(up2 + 1, left1 + 1);
            ChessMove u2l1Move = new ChessMove(startPosition, u2l1, null);
            if (pieceAtPosition(board, u2l1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u2l1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u2l1Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(u2l1Move);
            }
        }

        //up 1  left 2
        if (up1 < 8 && left2 >= 0){
            ChessPosition u1l2 = new ChessPosition(up1 + 1, left2 + 1);
            ChessMove u1l2Move = new ChessMove(startPosition, u1l2, null);
            if (pieceAtPosition(board, u1l2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u1l2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u1l2Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(u1l2Move);
            }
        }

        //down 1 left 2
        if (down1 > 0 && left2 >= 0){
            ChessPosition d1l2 = new ChessPosition(down1 + 1,left2 + 1);
            ChessMove d1l2Move = new ChessMove(startPosition, d1l2, null);
            if (pieceAtPosition(board, d1l2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d1l2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d1l2Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(d1l2Move);
            }
        }

        //down 2 left 1
        if (down2 >= 0 && left1 >= 0){
            ChessPosition d2l1 = new ChessPosition(down2 + 1,left1 + 1);
            ChessMove d2l1Move = new ChessMove(startPosition, d2l1, null);
            if (pieceAtPosition(board, d2l1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d2l1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d2l1Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(d2l1Move);
            }
        }

        //down 2 right 1
        if (down2 >= 0 && right1 < 8){
            ChessPosition d2r1 = new ChessPosition(down2 + 1,right1 + 1);
            ChessMove d2r1Move = new ChessMove(startPosition, d2r1, null);
            if (pieceAtPosition(board, d2r1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d2r1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d2r1Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(d2r1Move);
            }
        }

        //down 1 right 2
        if (down1 >= 0 && right2 < 8 ){
            ChessPosition d1r2 = new ChessPosition(down1 + 1,right2 + 1);
            ChessMove d1r2Move = new ChessMove(startPosition, d1r2, null);
            if (pieceAtPosition(board, d1r2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d1r2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d1r2Move);
                }
            } else { //else if there isn't a piece at the move position
                possibleMoves.add(d1r2Move);
            }
        }

        return possibleMoves;
    }


}
