package server;

import Singleton.Singleton;
import spark.*;
import webSocket.WebSocketHandler;

public class Server {

    private WebSocketHandler webSocketHandler;

    public int run(int desiredPort) {
        webSocketHandler = new WebSocketHandler();
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        Singleton singleton = Singleton.getInstance();

        Spark.webSocket("/connect", webSocketHandler);
        Spark.post("/user", (req, resp) -> singleton.getRegisterHandlerInstance().handle(req, resp));//register user
        Spark.post("/session", (req, resp) -> singleton.getLoginHandlerInstance().handle(req, resp)); //login user
        Spark.delete("/session", (req, resp) -> singleton.getLogoutHandlerInstance().handle(req, resp));  //logout user
        Spark.get("/game", (req, resp) -> singleton.getListGameHandlerInstance().handle(req, resp)); //list all games
        Spark.post("/game", (req, resp) -> singleton.getCreateGameHandlerInstance().handle(req, resp)); //create new game
        Spark.put("/game", (req, resp) -> singleton.getJoinGameHandlerInstance().handle(req, resp));  //join game
        Spark.delete("/db",(req, resp) -> singleton.getClearDBHandlerInstance().handle(req, resp)); //clear db

        Spark.get("/echo/:msg", (req, res) -> "HTTP response: " + req.params(":msg"));

        Spark.awaitInitialization();
        return Spark.port();
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}