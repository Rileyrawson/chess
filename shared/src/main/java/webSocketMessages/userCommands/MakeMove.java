package webSocketMessages.userCommands;

import chess.ChessMove;
import webSocketMessages.serverMessages.ServerMessage;

public class MakeMove extends UserGameCommand {
    public int gameID;
    public ChessMove move;

    public MakeMove(String authToken) {
        super(authToken);
        this.commandType = CommandType.MAKE_MOVE;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ChessMove getMove() {
        return move;
    }

}
