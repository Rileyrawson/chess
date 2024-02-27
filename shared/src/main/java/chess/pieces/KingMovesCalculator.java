package chess.pieces;
import chess.*;
import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalculator implements PieceMovesCalculator{
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
        final int row = myPosition.getRow(); //gets current row of bishop. Will not change
        final int col = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();
        int rowUp = row + 1;
        int rowDown = row - 1;
        int colRight = col + 1;
        int colLeft = col - 1;
        if (row < 7){ //up row + 1
            ChessPosition up = new ChessPosition(rowUp + 1,col + 1);
            ChessMove upMove = new ChessMove(startPosition, up, null);
            if (pieceAtPosition(board, up)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, up)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(upMove);
                }
            } else{ possibleMoves.add(upMove); }//else if there isn't a piece at the move position
        }
        if (row < 7 && col < 7){ //right up col + 1 row + 1
            ChessPosition rightUp = new ChessPosition(rowUp + 1,colRight + 1);
            ChessMove rightUpMove = new ChessMove(startPosition, rightUp, null);
            if (pieceAtPosition(board, rightUp)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, rightUp)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(rightUpMove);
                }
            } else{ possibleMoves.add(rightUpMove); }//else if there isn't a piece at the move position
        }
        if (col < 7){  //right col + 1
            ChessPosition right = new ChessPosition(row + 1,colRight + 1);
            ChessMove rightMove = new ChessMove(startPosition, right, null);
            if (pieceAtPosition(board, right)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, right)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(rightMove);
                }
            } else{ possibleMoves.add(rightMove); } //else if there isn't a piece at the move position
        }
        if (row < 7 && col > 0){ //right down col - 1 row + 1
            ChessPosition rightDown = new ChessPosition(rowDown + 1,colRight + 1);
            ChessMove rightDownMove = new ChessMove(startPosition, rightDown, null);
            if (pieceAtPosition(board, rightDown)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, rightDown)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(rightDownMove);
                }
            } else{ possibleMoves.add(rightDownMove); }//else if there isn't a piece at the move position
        }
        if(row > 0 && col > 0){ //down row - 1
            ChessPosition down = new ChessPosition(rowDown + 1,col + 1);
            ChessMove downMove = new ChessMove(startPosition, down, null);
            if (pieceAtPosition(board, down)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, down)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(downMove);
                }
            } else{ possibleMoves.add(downMove); }//else if there isn't a piece at the move position
        }
        if(row > 0 && col > 0) { //left down col - 1 row - 1
            ChessPosition leftDown = new ChessPosition(rowDown + 1, colLeft + 1);
            ChessMove leftDownMove = new ChessMove(startPosition, leftDown, null);
            if (pieceAtPosition(board, leftDown)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, leftDown)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(leftDownMove);
                }
            } else{ possibleMoves.add(leftDownMove); }//else if there isn't a piece at the move position
        }
        if(col > 0){ //left col - 1
            ChessPosition left = new ChessPosition(row + 1,colLeft + 1);
            ChessMove leftMove = new ChessMove(startPosition, left, null);
            if (pieceAtPosition(board, left)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, left)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(leftMove);
                }
            } else{ possibleMoves.add(leftMove); } //else if there isn't a piece at the move position
        }
        if(row < 7 && col > 0){ //left up col - 1 row + 1
            ChessPosition leftUp = new ChessPosition(rowUp + 1,colLeft + 1);
            ChessMove leftUpMove = new ChessMove(startPosition, leftUp, null);
            if (pieceAtPosition(board, leftUp)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, leftUp)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(leftUpMove);
                }
            } else{ possibleMoves.add(leftUpMove); }//else if there isn't a piece at the move position
        }
        return possibleMoves;
    }
}