package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import spark.*;

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

        Spark.get("/test", this::Test);

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

    private Object Test(Request req, Response res) {
        res.type("application/json");
        return new Gson().toJson("{test: 'test',}");
    }
}