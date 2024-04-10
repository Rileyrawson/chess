package webSocketMessages.userCommands;

import webSocketMessages.serverMessages.ServerMessage;

public class JoinObserver extends UserGameCommand {

    public int gameID;

    public JoinObserver(String authToken) {
        super(authToken);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
