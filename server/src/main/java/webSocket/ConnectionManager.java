package webSocket;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {

    public final ConcurrentHashMap<Integer, Connection> connections = new ConcurrentHashMap<>();

    public void add(int gameID, String authToken, Session session) {
        var connection = new Connection(authToken, session);
        connections.put(gameID, connection);
    }

    public void remove(int gameID) {
        connections.remove(gameID);
    }

    public void broadcast(String excludeUserAuth, ServerMessage message) throws IOException {

        for (Map.Entry<Integer, Connection> entry : connections.entrySet()) {

//            if (!entry.getValue().getAuthToken().equals(excludeUserAuth)) { //todo: commented out to shoe it working. will now shoe for everyone on connection
                String jsonMessage = new Gson().toJson(message);
                try {
                    entry.getValue().getSession().getRemote().sendString(jsonMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }
        }
    }
}
