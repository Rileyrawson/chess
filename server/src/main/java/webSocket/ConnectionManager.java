package webSocket;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.*;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {

    public final ConcurrentHashMap<Integer, Connection> connections = new ConcurrentHashMap<>();

    public void add(int gameID, Session session) {
        var connection = new Connection(gameID, session);
        connections.put(gameID, connection);
    }

    public void remove(int gameID) {
        connections.remove(gameID);
    }

    public void broadcast(String excludeVisitorName, ServerMessage message) throws IOException {
        var removeList = new ArrayList<Connection>();
        for (var c : connections.values()) {
//            if (c.session.isOpen()) {
//                if (!c.gameID.equals(excludeVisitorName)) {
//                    c.send(message.toString());
//                }
//            } else {
//                removeList.add(c);
//            }
        }

        // Clean up any connections that were left open.
        for (var c : removeList) {
            connections.remove(c.gameID);
        }
    }
}
