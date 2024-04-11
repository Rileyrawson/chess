package facade;

import javax.websocket.*;

import chess.ChessBoard;
import chess.ChessGame;
import com.google.gson.Gson;
import ui.PostloginUI;
import webSocketMessages.serverMessages.*;
import webSocketMessages.serverMessages.Error;
import webSocketMessages.userCommands.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketFacade extends Endpoint{
    private Session session;
    private String color;

    private ChessBoard board;


    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    @OnClose
    public void onClose() {  //closes the connection
        System.out.println("Connection closed");
        close();
    }
    public void close() {
        try {
            this.session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(Session session, String message) throws URISyntaxException, DeploymentException, IOException {  //todo takes in the UserGameCommand message. depending on message it will "do" the logic. -> call gameservice? or send to handler??
        ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
        switch (serverMessage.getServerMessageType()) {
            case ERROR -> errorMessage(new Gson().fromJson(message, Error.class), session);
            case LOAD_GAME -> loadMessage(new Gson().fromJson(message, LoadGame.class), session);
            case NOTIFICATION -> notificationMessage(new Gson().fromJson(message, Notification.class), session);
        }
    }

    private void notificationMessage(Notification notification, Session session) {
        System.out.println(notification.getMessage());
    }

    private void loadMessage(LoadGame loadGame, Session session) {
        ChessGame game = loadGame.getGame();
        this.board = game.getBoard();
        PostloginUI.drawBoard(color, board);
    }

    private void errorMessage(Error error, Session session) {
        System.out.println("Error: " + error.getErrorMessage());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void redrawBoard(){
        PostloginUI.drawBoard(color, board);
    }
}



