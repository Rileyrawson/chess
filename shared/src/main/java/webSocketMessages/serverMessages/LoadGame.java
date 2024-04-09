package webSocketMessages.serverMessages;

import chess.ChessGame;
import webSocketMessages.userCommands.UserGameCommand;

public class LoadGame extends UserGameCommand {
    public ChessGame game;
    public LoadGame(String authToken) {
        super(authToken);
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }
}
