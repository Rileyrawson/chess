package webSocketMessages.userCommands;

import webSocketMessages.serverMessages.ServerMessage;

public class Resign extends ServerMessage {
    public int gameID;
    public Resign(ServerMessageType type) {
        super(type);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
