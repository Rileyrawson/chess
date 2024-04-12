package webSocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;


public class Connection {
    public String authToken;
    public int gameID;
    public Session session;

    public Connection(String authToken, int gameID, Session session) {
        this.authToken = authToken;
        this.session = session;
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void send(String msg) throws IOException {
        session.getRemote().sendString(msg);
    }
}