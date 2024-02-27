package model.response;

public class ListGameResponse {

    private int statusCode;
    private int gameId;
    private String whiteUsername;
    private String blackUsername;
    private String gameName;

    public ListGameResponse(int statusCode, int gameId, String whiteUsername, String blackUsername, String gameName) {
        this.statusCode = statusCode;
        this.gameId = gameId;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
    }

//    public int getStatusCode() {
//        return statusCode;
//    }

//    public int getGameId() {
//        return gameId;
//    }

//    public String getWhiteUsername() {
//        return whiteUsername;
//    }

//    public String getBlackUsername() {
//        return blackUsername;
//    }

//    public String getGameName() {
//        return gameName;
//    }
}