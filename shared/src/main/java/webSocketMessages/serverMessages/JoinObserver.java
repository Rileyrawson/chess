package webSocketMessages.serverMessages;

public class JoinObserver extends ServerMessage {

    public int gameID;

    public JoinObserver(ServerMessageType type) {
        super(type);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
