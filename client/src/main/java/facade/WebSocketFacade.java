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

public class WebSocketFacade extends Endpoint {
    private Session session;
    private String color;

    private ChessBoard board;

    public WebSocketFacade() {
        try {
            String url = "ws://localhost:8080";
            URI socketURI = new URI(url + "/connect");

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
//                    Notification notification = new Gson().fromJson(message, Notification.class);
//                    notificationHandler.notify(notification);

                    ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
                    switch (serverMessage.getServerMessageType()) {
                        case ERROR -> errorMessage(new Gson().fromJson(message, Error.class), session);
                        case LOAD_GAME -> loadMessage(new Gson().fromJson(message, LoadGame.class), session);
                        case NOTIFICATION -> notificationMessage(new Gson().fromJson(message, Notification.class), session);
                    }
                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            System.out.println("Websocket Error -> " + ex.getMessage());
        }
    }



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

//    @OnMessage
//    public void onMessage(Session session, String message) throws URISyntaxException, DeploymentException, IOException {  //todo takes in the UserGameCommand message. depending on message it will "do" the logic. -> call gameservice? or send to handler??
//        ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
//        switch (serverMessage.getServerMessageType()) {
//            case ERROR -> errorMessage(new Gson().fromJson(message, Error.class), session);
//            case LOAD_GAME -> loadMessage(new Gson().fromJson(message, LoadGame.class), session);
//            case NOTIFICATION -> notificationMessage(new Gson().fromJson(message, Notification.class), session);
//        }
//    }

    private void notificationMessage(Notification notification, Session session) {
        System.out.println(notification.getMessage());
    }

    private void loadMessage(LoadGame loadGame, Session session) {
        ChessGame game = loadGame.getGame();
        this.board = game.getBoard();
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

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public void redrawBoard(){
        if (board == null){
            board = new ChessBoard();
            board.resetBoard();
        }
        System.out.println(PostloginUI.drawBoard(color, board));
    }

    public void resign(){

    }

    public void joinPlayer(String authToken, String gameID, String color) {
        JoinPlayer joinCommand = new JoinPlayer(authToken);
        joinCommand.setGameID(Integer.parseInt(gameID));
        if (color.equals("white")) {
            joinCommand.setPlayerColor(ChessGame.TeamColor.WHITE);
        } else {
            joinCommand.setPlayerColor(ChessGame.TeamColor.BLACK);
        }

        try {
            this.session.getBasicRemote().sendText(new Gson().toJson(joinCommand));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public void joinObserver(String authToken, String gameID) {
        JoinObserver joinCommand = new JoinObserver(authToken);
        joinCommand.setGameID(Integer.parseInt(gameID));
        try {
            this.session.getBasicRemote().sendText(new Gson().toJson(joinCommand));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}



