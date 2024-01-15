package chess;

import java.util.ArrayList;
import java.util.Collection;

public class Pawn extends ChessPiece{

    private ChessPiece[][] possibleMoves = new ChessPiece[8][8];

    public Pawn(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {  //auto generated constructor
        super(pieceColor, type);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPosition startPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());

        final int pieceRow = myPosition.getRow(); //gets current row. Will not change
        final int pieceCol = myPosition.getColumn();
        ArrayList<ChessMove> possibleMoves = new ArrayList<ChessMove>();

        //super.pieceColor;

        //White up
        if (getTeamColor() == ChessGame.TeamColor.WHITE){
            if (isFirstMove() == true && pieceRow == 2) { //white first move = up 2
                ChessPosition movePosition = new ChessPosition(pieceRow + 2,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
                setFirstMove(false);
            }
            else if (pieceRow < 8){ //white move up 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
            }
            else if (pieceRow == 8) {//promotion on row 8
                ChessPosition movePosition = new ChessPosition(pieceRow + 8,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                possibleMoves.add(move);
            }
        }

        //Black down
        if (this.getTeamColor() == ChessGame.TeamColor.WHITE){
            if (this.isFirstMove() == true && pieceRow == 7) { //black first move = down 2
                ChessPosition movePosition = new ChessPosition(pieceRow - 2 ,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
                setFirstMove(false);
            }
            else if (pieceRow > 1){ //black move down 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, null);
                possibleMoves.add(move);
            }
            else if (pieceRow == 1) {//promotion on row 1
                ChessPosition movePosition = new ChessPosition(pieceRow + 1,pieceCol);
                ChessMove move = new ChessMove(startPosition, movePosition, this.type);
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }


}
