package chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor team; //white always goes first, set later in game logic
    private ChessBoard board;
    private final int boardRow = 8;
    private final int boardCol = 8;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.team;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.team = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {






        throw new RuntimeException("Not implemented");                                                          //TODO 4
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {






        throw new RuntimeException("Not implemented");                                                          //TODO 5
    }

    private boolean canCaptureKing(ArrayList<ChessMove> moves, ChessPosition kingPosition){
        for(ChessMove i : moves){
            if (i.getEndPosition().equals(kingPosition)){ // if the ending position == where the king is -> capture
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {

        ChessPosition kingPosition = board.getKingPosition(teamColor);

        //check if that piece has a possible move at kingPosition

        ChessPiece[][] pieceArray = this.board.getBoard();
        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) { //iterate through board
                if (pieceArray[i][j] != null && pieceArray[i][j].getTeamColor() != teamColor){ //if this position on the board isn't null and not the same team
                    ArrayList<ChessMove> moves = (ArrayList<ChessMove>) pieceArray[i][j].pieceMoves(board, new ChessPosition(i + 1,j + 1)); // get all possible moves for piece
                    if (canCaptureKing(moves, kingPosition)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {














        throw new RuntimeException("Not implemented");                                                        //TODO 2
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {












        throw new RuntimeException("Not implemented");                                                         //TODO 3
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) { this.board = board; }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}