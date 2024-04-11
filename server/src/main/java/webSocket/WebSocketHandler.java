package webSocket;

import Singleton.Singleton;
import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import model.AuthData;
import model.GameData;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import webSocketMessages.userCommands.*;
import webSocketMessages.serverMessages.*;
import java.io.IOException;

@WebSocket
public class WebSocketHandler {

    private final ConnectionManager connections = new ConnectionManager();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        UserGameCommand command = new Gson().fromJson(message, UserGameCommand.class);
        switch (command.getCommandType()) {
            case JOIN_PLAYER -> joinPlayer(new Gson().fromJson(message, JoinPlayer.class), session);
            case JOIN_OBSERVER -> joinObserver(new Gson().fromJson(message, JoinObserver.class), session);
            case MAKE_MOVE -> makeMove(new Gson().fromJson(message, MakeMove.class), session);
            case LEAVE -> leave(new Gson().fromJson(message, Leave.class), session);
            case RESIGN -> resign(new Gson().fromJson(message, Resign.class), session);
        }
    }

    private void joinPlayer(JoinPlayer command, Session session) throws IOException {
        connections.add(command.getGameID(), command.getAuthString(), session);
        try {
            GameData gameData = Singleton.getInstance().getGameDAOInstance().getGameByID(command.getGameID());
            String message;
            if (command.getPlayerColor() == ChessGame.TeamColor.WHITE){
                message = String.format("\n%s has joined the game as WHITE", gameData.whiteUsername());
            } else if (command.getPlayerColor() == ChessGame.TeamColor.BLACK){
                message = String.format("\n%s has joined the game as BLACK", gameData.blackUsername());
            } else {
                message = String.format("\nUSER joined game. There was an error retrieving username");
            }
            var loadGame = new LoadGame(gameData.game());
            sendMessage(loadGame, session);
            var notification = new Notification(message);
            connections.broadcast(command.getAuthString(), notification);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void joinObserver(JoinObserver command, Session session) throws IOException {
        connections.add(command.getGameID(), command.getAuthString(), session);
        try {
            GameData gameData = Singleton.getInstance().getGameDAOInstance().getGameByID(command.getGameID());
            var loadGame = new LoadGame(gameData.game());
            AuthData authData = Singleton.getInstance().getAuthDAOInstance().getAuth(command.getAuthString());
            var message = String.format("%s has joined the game as an OBSERVER", authData.username());
            connections.broadcast(command.getAuthString(), loadGame);
            var notification = new Notification(message);
            connections.broadcast(command.getAuthString(), notification);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void leave(Leave command, Session session) throws IOException {
        connections.add(command.getGameID(), command.getAuthString(), session);
        try {
            GameData gameData = Singleton.getInstance().getGameDAOInstance().getGameByID(command.getGameID());
            AuthData authData = Singleton.getInstance().getAuthDAOInstance().getAuth(command.getAuthString());
            var message = String.format("\n%s has left", authData.username());
//            System.out.println(message); //debugging to make sure it is populating correctly

            var loadGame = new LoadGame(gameData.game());
            sendMessage(loadGame, session);
//            connections.broadcast(command.getAuthString(), loadGame);
            var notification = new Notification(message);
            connections.broadcast(command.getAuthString(), notification);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    private void resign(Resign command, Session session) throws IOException {
        connections.add(command.getGameID(), command.getAuthString(), session);
        try {
            GameData gameData = Singleton.getInstance().getGameDAOInstance().getGameByID(command.getGameID());
            AuthData authData = Singleton.getInstance().getAuthDAOInstance().getAuth(command.getAuthString());
            var message = String.format("%s has resigned", authData.username());
            var loadGame = new LoadGame(gameData.game());
            sendMessage(loadGame, session);
//            connections.broadcast(command.getAuthString(), loadGame);
            var notification = new Notification(message);
            connections.broadcast(command.getAuthString(), notification);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void makeMove(MakeMove command, Session session) throws IOException {
        connections.add(command.getGameID(), command.getAuthString(), session);
        try {
            GameData gameData = Singleton.getInstance().getGameDAOInstance().getGameByID(command.getGameID());
            var loadGame = new LoadGame(gameData.game());
            AuthData authData = Singleton.getInstance().getAuthDAOInstance().getAuth(command.getAuthString());
            var message = String.format("%s moved ", authData.username(), command.getMove());
            connections.broadcast(command.getAuthString(), loadGame);
            var notification = new Notification(message);
            connections.broadcast(command.getAuthString(), notification);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(ServerMessage msg, Session session) throws IOException {
        session.getRemote().sendString(new Gson().toJson(msg));
    }
}
