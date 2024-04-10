package webSocketMessages.serverMessages;

import chess.ChessGame;
import webSocketMessages.userCommands.UserGameCommand;

public class LoadGame extends ServerMessage {
    public ChessGame game;

    public LoadGame(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }
}
