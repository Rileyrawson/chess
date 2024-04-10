package webSocketMessages.userCommands;

import webSocketMessages.serverMessages.ServerMessage;

public class Resign extends UserGameCommand {
    public int gameID;

    public Resign(String authToken) {
        super(authToken);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
