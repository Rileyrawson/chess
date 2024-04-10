package webSocketMessages.userCommands;

import webSocketMessages.serverMessages.ServerMessage;

public class Leave extends UserGameCommand {
    public int gameID;

    public Leave(String authToken) {
        super(authToken);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
