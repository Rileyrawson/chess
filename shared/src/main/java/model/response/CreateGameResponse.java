package model.response;

public class CreateGameResponse {

    private int statusCode;
    private int gameId;

    public CreateGameResponse(int statusCode, int gameId) {
        this.statusCode = statusCode;
        this.gameId = gameId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getGameId() {
        return gameId;
    }
}
