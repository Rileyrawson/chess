package server;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.api.*;

import Singleton.Singleton;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import spark.*;
import webSocket.WebSocketHandler;
import webSocketMessages.userCommands.UserGameCommand;

@WebSocket
public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        Singleton singleton = Singleton.getInstance();

        Spark.post("/user", (req, resp) -> singleton.getRegisterHandlerInstance().handle(req, resp));//register user
        Spark.post("/session", (req, resp) -> singleton.getLoginHandlerInstance().handle(req, resp)); //login user
        Spark.delete("/session", (req, resp) -> singleton.getLogoutHandlerInstance().handle(req, resp));  //logout user
        Spark.get("/game", (req, resp) -> singleton.getListGameHandlerInstance().handle(req, resp)); //list all games
        Spark.post("/game", (req, resp) -> singleton.getCreateGameHandlerInstance().handle(req, resp)); //create new game
        Spark.put("/game", (req, resp) -> singleton.getJoinGameHandlerInstance().handle(req, resp));  //join game
        Spark.delete("/db",(req, resp) -> singleton.getClearDBHandlerInstance().handle(req, resp)); //clear db

        Spark.webSocket("/connect", WebSocketHandler.class);
        Spark.get("/echo/:msg", (req, res) -> "HTTP response: " + req.params(":msg"));

        Spark.awaitInitialization();
        return Spark.port();
    }

//    @OnWebSocketMessage
//    public void onMessage(Session session, String msg) throws Exception {
//        UserGameCommand command = readJson(msg, UserGameCommand.class); //do i need to code this?
//
//        var conn = getConnection(command.getAuthString(), session);  //do i need to code this?
//        if (conn != null) {
//            switch (command.getCommandType()) {
//                case JOIN_PLAYER -> join(conn, msg);  //functions that connect to web socket and do the logic?
//                case JOIN_OBSERVER -> observe(conn, msg);
//                case MAKE_MOVE -> move(conn, msg);
//                case LEAVE -> leave(conn, msg);
//                case RESIGN -> resign(conn, msg);
//            }
//        } else {
//            Connection.sendError(session.getRemote(), "unknown user");  //do i need to code this?
//        }
//    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}