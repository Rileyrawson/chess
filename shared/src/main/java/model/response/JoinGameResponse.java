package model.response;

public class JoinGameResponse {
    private int statusCode;
    private String playerColor;
    private int gameId;

    public JoinGameResponse(int statusCode, String playerColor, int gameId) {
        this.statusCode = statusCode;
        this.playerColor = playerColor;
        this.gameId = gameId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getPlayerColor() {return playerColor;}

    public int getGameId() {
        return gameId;
    }
}