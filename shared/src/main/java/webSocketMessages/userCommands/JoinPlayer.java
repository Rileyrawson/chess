package webSocketMessages.userCommands;
import chess.ChessGame;
import webSocketMessages.serverMessages.ServerMessage;

public class JoinPlayer extends UserGameCommand {


    public int gameID;
    public ChessGame.TeamColor playerColor;

    public JoinPlayer(String authToken) {
        super(authToken);
        this.commandType = CommandType.JOIN_PLAYER;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(ChessGame.TeamColor playerColor) {
        this.playerColor = playerColor;
    }
}
