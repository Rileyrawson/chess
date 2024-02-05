package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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

        if (board.getPiece(startPosition) == null){ // if no piece, return null
            return null;
        }
        if (isInCheck(board.getTeamAtPosition(startPosition))){

            //moves limited to ones that can't keep in check
        }
        if (isInCheckmate(board.getTeamAtPosition(startPosition))){
            //no valid moves
            return null;
        }
        if (isInStalemate(board.getTeamAtPosition(startPosition))){
            //no valid moves
            return null;
        }
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



    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //check if that piece has a possible move at kingPosition

        ChessPosition kingPosition = board.getKingPosition(teamColor);
        ChessPiece[][] pieceArray = this.board.getBoard();

        ArrayList<ChessMove> captureMoves = new ArrayList<>();// just used for checkmate

        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) { //iterate through board
                if (pieceArray[i][j] != null && pieceArray[i][j].getTeamColor() != teamColor){ //if this position on the board isn't null and not the same team
                    ArrayList<ChessMove> moves = (ArrayList<ChessMove>) pieceArray[i][j].pieceMoves(board, new ChessPosition(i + 1,j + 1)); // get all possible moves for piece
                    if (canCapture(moves, kingPosition)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canCapture(ArrayList<ChessMove> otherTeamMoves, ChessPosition piecePosition){
        for(ChessMove i : otherTeamMoves){
            if (i.getEndPosition().equals(piecePosition)){ // if the ending position == where the king is -> capture
//                System.out.print("other team: " + i.getStartPosition() + ", " + i.getEndPosition());
//                System.out.print("king team: " + piecePosition);
                return true;
            }
        }
        return false;
    }
    public boolean kingCanEscape(ArrayList<ChessMove> otherTeamMoves, ArrayList<ChessMove> sameTeamKingMoves, ChessPosition kingPosition){

        for(ChessMove i : sameTeamKingMoves) {
            if (!canCapture(otherTeamMoves, i.getEndPosition())) { //when you can't capture the king, the king can escape and is no longer in checkmate;
                return true;
            }
        }
        return false;
    }
    private ArrayList<ChessPosition> collectThreateningPiecePosition(ArrayList<ChessMove> otherTeamMoves, ChessPosition kingPosition){
        ArrayList<ChessPosition> threateningPiecePosition = new ArrayList<>();
        for(ChessMove i : otherTeamMoves){
            if (i.getEndPosition().equals(kingPosition)){ // if the ending position == where the king is -> capture
                threateningPiecePosition.add(i.getStartPosition());
            }
        }
        return threateningPiecePosition;
    }

    //capture moves
    public boolean canCaptureThreateningPiece(ArrayList<ChessMove> otherTeamMoves, ArrayList<ChessMove> sameTeamMoves, ChessPosition kingPosition){
        ArrayList<ChessPosition> threateningPiecePositions = collectThreateningPiecePosition(otherTeamMoves, kingPosition);
        for(ChessPosition i : threateningPiecePositions) {
            if (canCapture(otherTeamMoves, i)) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<ChessMove> collectThreateningPieceMoves(ArrayList<ChessMove> otherTeamMoves, ChessPosition kingPosition){
        ArrayList<ChessMove> threateningPieceMoves = new ArrayList<>();
        for(ChessMove i : otherTeamMoves){
            if (i.getEndPosition().equals(kingPosition)){ // if the ending position == where the king is -> capture
                threateningPieceMoves.add(i);
            }
        }
        return threateningPieceMoves;
    }
    //block moves
    public boolean canBlockThreateningPiece(ArrayList<ChessMove> otherTeamMoves, ArrayList<ChessMove> sameTeamMoves, ChessPosition kingPosition){
        ArrayList<ChessMove> threateningPieceMoves = collectThreateningPieceMoves(otherTeamMoves, kingPosition);
        for(ChessMove i : threateningPieceMoves) {
            for(ChessMove j : sameTeamMoves) {
                if (i.getEndPosition().equals(j.getEndPosition())) {
                    return true;
                }
            }
        }
        return false;
    }
    private void getSameTeamKingMoves(ChessBoard currBoard, ArrayList<ChessMove> sameTeamKingMoves, TeamColor teamColor){
        ChessPiece[][] pieceArray = currBoard.getBoard();
        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) { //iterate through board
                if (pieceArray[i][j] != null && pieceArray[i][j].getTeamColor() == teamColor && pieceArray[i][j].getPieceType() == ChessPiece.PieceType.KING){ //is a piece and on same team
                    sameTeamKingMoves.addAll(pieceArray[i][j].pieceMoves(currBoard, new ChessPosition(i + 1,j + 1))); // get all possible moves for piece
                }
            }
        }
    }
    private void getOtherTeamMoves(ChessBoard currBoard, ArrayList<ChessMove> otherTeamMoves, TeamColor teamColor){
        ChessPiece[][] pieceArray = currBoard.getBoard();
        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) { //iterate through board
                if (pieceArray[i][j] != null && pieceArray[i][j].getTeamColor() != teamColor){ //is a piece and is not the same team
                    otherTeamMoves.addAll(pieceArray[i][j].pieceMoves(currBoard, new ChessPosition(i + 1,j + 1))); // get all possible moves for piece
                }
            }
        }
    }
    private void getSameTeamMoves(ChessBoard currBoard, ArrayList<ChessMove> sameTeamMoves, TeamColor teamColor){
        ChessPiece[][] pieceArray = currBoard.getBoard();
        for (int i = boardRow - 1; i >= 0; i--) {
            for (int j = 0; j < boardCol; j++) { //iterate through board
                if (pieceArray[i][j] != null && pieceArray[i][j].getTeamColor() == teamColor){ //is a piece and on same team
                    sameTeamMoves.addAll(pieceArray[i][j].pieceMoves(currBoard, new ChessPosition(i + 1,j + 1))); // get all possible moves for piece
                }
            }
        }

    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {   //same as in check?

        ChessPosition kingPosition = board.getKingPosition(teamColor);
        ChessPiece[][] pieceArray = this.board.getBoard(); //all pieces on board
        ArrayList<ChessMove> otherTeamMoves = new ArrayList<>();
        ArrayList<ChessMove> sameTeamMoves = new ArrayList<>();
        ArrayList<ChessMove> sameTeamKingMoves = new ArrayList<>();

        if (!isInCheck(teamColor)){
            return false;
        }

        getSameTeamKingMoves(board, sameTeamKingMoves, teamColor);

        ChessPiece kingPiece =  board.getPiece(kingPosition);
        System.out.println("Original Hashcode: " + this.board.hashCode());
        // makes 8 new boards to account for king's 8 potential moves
        ArrayList<ChessBoard> kingMoveBoards = new ArrayList<>();
        for (ChessMove i : sameTeamKingMoves){
            ChessBoard simBoard = new ChessBoard();
            ChessPiece[][] simPieceArray = new ChessPiece[8][8];
            for (int j = 0; j < pieceArray.length; ++j) { // copying the new board into a new board. will later make move and store in array of possible moves
                simPieceArray[j] = new ChessPiece[pieceArray[j].length];      // allocating space for each row of destination array
                System.arraycopy(pieceArray[j], 0, simPieceArray[j], 0, pieceArray[j].length);
            }
            simBoard.setBoard(simPieceArray);
            simBoard.makeMove(simBoard, i, kingPiece);
            kingMoveBoards.add(simBoard);
        }

        System.out.println(kingMoveBoards);

        int counter = 0;
        for (ChessBoard i : kingMoveBoards){
            counter++;
            System.out.println(i);
            System.out.println(counter);
            getOtherTeamMoves(i, otherTeamMoves, teamColor);
            getSameTeamMoves(i, sameTeamMoves, teamColor);
        }


        if(kingCanEscape(otherTeamMoves, sameTeamKingMoves, kingPosition)){   //king can move to escape
            return false;
        }
        if (canCaptureThreateningPiece(otherTeamMoves, sameTeamMoves, kingPosition)){  //team can capture other team's moves to protect
            return false;
        }
        if (canBlockThreateningPiece(otherTeamMoves, sameTeamMoves, kingPosition)){ // team can block path
            return false;
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {

        /*
        if both teams are in checkmate? so call isInCheckmate on both teams
         */

//        return false;

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
    public ChessBoard getBoard() { return this.board; }
}