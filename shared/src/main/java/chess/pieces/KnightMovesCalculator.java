package chess.pieces;
import chess.*;
import java.util.ArrayList;
import java.util.Collection;
public class KnightMovesCalculator implements PieceMovesCalculator {
    public boolean isSameColor(ChessBoard board, ChessPosition piecePosition, ChessPosition movePosition){
        if (board.getTeamAtPosition(piecePosition) == board.getTeamAtPosition(movePosition)){ return true; }
        return false;
    }
    public boolean pieceAtPosition(ChessBoard board, ChessPosition movePosition){
        ChessPiece piece = board.getPiece(movePosition);
        return piece != null; //if there is a piece return true. if no piece at position return false
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
        final int pieceRow = myPosition.getRow();
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
        int right2 = pieceCol + 2;
        int right1 = pieceCol + 1;
        int left2 = pieceCol - 2;
        int left1 = pieceCol -1;
        int up2 = pieceRow + 2;
        int up1 = pieceRow + 1;
        int down2 = pieceRow - 2;
        int down1 = pieceRow - 1;
        if (up1 < 8 && right2 < 8){ //up 1 right 2
            ChessPosition u1r2 = new ChessPosition(up1 + 1,right2 + 1);
            ChessMove u1r2Move = new ChessMove(startPosition, u1r2, null);
            if (pieceAtPosition(board, u1r2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u1r2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u1r2Move);
                }
            } else{possibleMoves.add(u1r2Move);} //else if there isn't a piece at the move position
        }
        if (up2 < 8 && right1 < 8){ //up 2 right 1
            ChessPosition u2r1 = new ChessPosition(up2 + 1,right1 + 1);
            ChessMove u2r1Move = new ChessMove(startPosition, u2r1, null);
            if (pieceAtPosition(board, u2r1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u2r1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u2r1Move);
                }
            } else { possibleMoves.add(u2r1Move); }//else if there isn't a piece at the move position
        }
        if (up2 < 8 && left1 >= 0){ //up 2 left 1
            ChessPosition u2l1 = new ChessPosition(up2 + 1, left1 + 1);
            ChessMove u2l1Move = new ChessMove(startPosition, u2l1, null);
            if (pieceAtPosition(board, u2l1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u2l1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u2l1Move);
                }
            } else { possibleMoves.add(u2l1Move); }//else if there isn't a piece at the move position
        }
        if (up1 < 8 && left2 >= 0){ //up 1  left 2
            ChessPosition u1l2 = new ChessPosition(up1 + 1, left2 + 1);
            ChessMove u1l2Move = new ChessMove(startPosition, u1l2, null);
            if (pieceAtPosition(board, u1l2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, u1l2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(u1l2Move);
                }
            } else { possibleMoves.add(u1l2Move); }//else if there isn't a piece at the move position
        }
        if (down1 > 0 && left2 >= 0){ //down 1 left 2
            ChessPosition d1l2 = new ChessPosition(down1 + 1,left2 + 1);
            ChessMove d1l2Move = new ChessMove(startPosition, d1l2, null);
            if (pieceAtPosition(board, d1l2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d1l2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d1l2Move);
                }
            } else { possibleMoves.add(d1l2Move);} //else if there isn't a piece at the move position
        }
        if (down2 >= 0 && left1 >= 0){ //down 2 left 1
            ChessPosition d2l1 = new ChessPosition(down2 + 1,left1 + 1);
            ChessMove d2l1Move = new ChessMove(startPosition, d2l1, null);
            if (pieceAtPosition(board, d2l1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d2l1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d2l1Move);
                }
            } else { possibleMoves.add(d2l1Move); }//else if there isn't a piece at the move position
        }
        if (down2 >= 0 && right1 < 8){ //down 2 right 1
            ChessPosition d2r1 = new ChessPosition(down2 + 1,right1 + 1);
            ChessMove d2r1Move = new ChessMove(startPosition, d2r1, null);
            if (pieceAtPosition(board, d2r1)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d2r1)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d2r1Move);
                }
            } else { possibleMoves.add(d2r1Move); }//else if there isn't a piece at the move position
        }
        if (down1 >= 0 && right2 < 8 ){ //down 1 right 2
            ChessPosition d1r2 = new ChessPosition(down1 + 1,right2 + 1);
            ChessMove d1r2Move = new ChessMove(startPosition, d1r2, null);
            if (pieceAtPosition(board, d1r2)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, d1r2)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(d1r2Move);
                }
            } else { possibleMoves.add(d1r2Move);}//else if there isn't a piece at the move position
        }
        return possibleMoves;
    }
}