package chess.pieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoves {

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

    public Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

        final int pieceRow = myPosition.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        // check right & up
        int col = pieceCol;
        int row = pieceRow;
        System.out.print("\nru: ");
        while ((0 <= row && row < 7) && (0 <= col && col < 7)){
            row++; // up
            col++; // right
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);

            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + row + "," + col + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + row + "," + col + ")" );
            }
        }

        //check right & down
        row = pieceRow;
        col = pieceCol;
        System.out.print("\nrd: ");
        while ((0 < row && row < 7) && (0 <= col && col <= 7)){
            row--; // down
            col++; // right
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + row + "," + col + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + row + "," + col + ")" );
            }
        }

        //check left & down
        row = pieceRow;
        col = pieceCol;
        System.out.print("\nld: ");
        while ((0 < row && row <= 7) && (0 < col && col <= 7)){
            row--; // down
            col--; // left
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + row + "," + col + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + row + "," + col + ")" );
            }
        }

        //check left & up
        row = pieceRow;
        col = pieceCol;
        System.out.print("\nlu: ");
        while ((0 <= row && row < 7) && (0 < col && col <= 7)){
            row++; // up
            col--; // left
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + row + "," + col + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + row + "," + col + ")" );
            }
        }
        return possibleMoves;
    }
}
