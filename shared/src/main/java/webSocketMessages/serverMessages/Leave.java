package webSocketMessages.serverMessages;

public class Leave extends ServerMessage{
    public int gameID;

    public Leave(ServerMessageType type) {
        super(type);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
