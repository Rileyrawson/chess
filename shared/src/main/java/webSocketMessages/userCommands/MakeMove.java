package webSocketMessages.userCommands;

import chess.ChessMove;
import webSocketMessages.serverMessages.ServerMessage;

public class MakeMove extends ServerMessage {
    public int gameID;
    public ChessMove move;
    public MakeMove(ServerMessageType type) {
        super(type);
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

    public void setMove(ChessMove move) {
        this.move = move;
    }
}
