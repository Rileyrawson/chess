package webSocket;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {

    public final Vector<Connection> connections = new Vector<>();

    public void add(int gameID, String authToken, Session session) {
        var connection = new Connection(authToken, gameID, session);
        connections.add(connection);
    }

    public void remove(int gameID) {
        connections.remove(gameID);
    }

    public void broadcast(int gameID, String excludeToken, ServerMessage msg) throws Exception {
        var removeList = new Vector<Connection>();
        for (Connection c : connections) {
            if (c.session.isOpen()) {
                if (c.gameID == gameID && !c.authToken.equals(excludeToken)) {
                    c.send(new Gson().toJson(msg));
                }
            } else {
                removeList.add(c);
            }
        }

        // Clean up any connections that were left open.
        for (Connection c : removeList) {
            connections.remove(c);
        }
    }
}
