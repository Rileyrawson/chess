package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Bishop extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Bishop(ChessGame.TeamColor pieceColor, PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bishop bishop = (Bishop) o;
        return Arrays.equals(possibleMoves, bishop.possibleMoves);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(possibleMoves);
    }


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

    /*
     TODO: capture
     TODO: blocked by same team
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
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



        row = pieceRow;
        col = pieceCol;
        //check right & down
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

        row = pieceRow;
        col = pieceCol;
        //check left & down
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

        row = pieceRow;
        col = pieceCol;
        //check left & up
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


    public String toString (){
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.toString();
    }

}
