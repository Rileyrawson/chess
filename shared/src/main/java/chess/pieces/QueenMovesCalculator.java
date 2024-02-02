package chess.pieces;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessGame.TeamColor currentTeam = board.getTeamAtPosition(myPosition);

        ArrayList<ChessPosition> possiblePositions = new ArrayList<ChessPosition>();
        ArrayList<ChessMove> possibleMoves;

        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.UP, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.UP_RIGHT, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.RIGHT, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.DOWN_RIGHT, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.DOWN, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.DOWN_LEFT, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.LEFT, possiblePositions, currentTeam);
        ChessPiece.recursiveMoves(board, myPosition, ChessPosition.Direction.UP_LEFT, possiblePositions, currentTeam);

        possibleMoves = (ArrayList<ChessMove>)ChessPiece.convertPositionsToMoves(myPosition, possiblePositions); // call conversion function and cast it into an array list

        return possibleMoves;
    }
}
