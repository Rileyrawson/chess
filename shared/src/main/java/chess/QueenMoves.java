package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class QueenMoves {

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

    public Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);

        final int pieceRow = myPosition.getRow(); //gets current row of bishop. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //right
        System.out.print("\nr: ");
        int straightRow = pieceRow;
        int starightCol = pieceCol;
        while ((0 <= starightCol && starightCol < 7)){
            starightCol++;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + straightRow + "," + starightCol + ")" );
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + straightRow + "," + starightCol + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + straightRow + "," + starightCol + ")" );
            }
        }

        //right up
        System.out.print("\nru: ");
        int row = pieceRow;
        int col = pieceCol;
        while ((0 <= row && row < 7) && (0 <= col && col < 7)){
            row++;
            col++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + row + "," + col + ")" );
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

        //up
        System.out.print("\nu: ");
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((0 <= straightRow && straightRow < 7)){
            straightRow++;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + straightRow + "," + starightCol + ")" );
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + straightRow + "," + starightCol + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + straightRow + "," + starightCol + ")" );
            }
        }

        //left up
        System.out.print("\nlu: ");
        row = pieceRow;
        col = pieceCol;
        while ((0 <= row && row < 7) && (0 < col && col <= 7)){
            row++;
            col--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + row + "," + col + ")" );
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

        //left
        System.out.print("\nl: ");
        straightRow = pieceRow;
        starightCol = pieceCol;
        while (0 < starightCol && starightCol <= 7){
            starightCol--;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + straightRow + "," + starightCol + ")" );
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + straightRow + "," + starightCol + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + straightRow + "," + starightCol + ")" );
            }
        }

        //left down
        System.out.print("\nld: ");
        row = pieceRow;
        col = pieceCol;
        while ((0 < row && row <= 7) && (0 < col && col <= 7)){
            row--;
            col--;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + row + "," + col + ")" );
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

        //down
        System.out.print("\nd: ");
        straightRow = pieceRow;
        starightCol = pieceCol;
        while ((0 < straightRow && straightRow <= 7)){
            straightRow--;
            ChessPosition movePosition = new ChessPosition(straightRow + 1,starightCol + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + straightRow + "," + starightCol + ")" );
            if (pieceAtPosition(board, movePosition)){ //If there is a piece at the move position
                if (!isSameColor(board, myPosition, movePosition)){ //check if the piece in the spot is on same team (blocked) or other team (capture) (capture continues and block breaks)
                    possibleMoves.add(move);
                    System.out.print("Capture" + "(" + straightRow + "," + starightCol + ")" );
                    break;
                }else {
                    break;
                }
            } else{ //else if there isn't a piece at the move position
                possibleMoves.add(move);
                System.out.print("(" + straightRow + "," + starightCol + ")" );
            }
        }

        //down right
        System.out.print("\nrd: ");
        row = pieceRow;
        col = pieceCol;
        while ((0 < row && row <= 7) && (0 <= col && col < 7)){
            row--;
            col++;
            ChessPosition movePosition = new ChessPosition(row + 1,col + 1);
            ChessMove move = new ChessMove(startPosition, movePosition, null);
//            possibleMoves.add(move);
//            System.out.print("(" + row + "," + col + ")" );
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
