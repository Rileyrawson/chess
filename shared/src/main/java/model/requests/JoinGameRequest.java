package model.requests;

public class JoinGameRequest {

    private String authToken;
    private String playerColor;
    private String gameID;

    public JoinGameRequest(String playerColor, String gameID) {
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getGameID() {
        return gameID;
    }
}
