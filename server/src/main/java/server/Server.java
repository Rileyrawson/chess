package server;

import com.google.gson.Gson;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");


        Spark.post("/user", (req, resp) -> new RegisterHandler().handle(req, resp));//register user
        Spark.post("/session", (req, resp) -> new LoginHandler().handle(req, resp)); //login user
        Spark.delete("/session", (req, resp) -> new LogoutHandler().handle(req, resp));  //logout user
        Spark.get("/game", (req, resp) -> new ListGameHandler().handle(req, resp)); //list all games
        Spark.post("/game", (req, resp) -> new CreateGameHandler().handle(req, resp)); //create new game
        Spark.put("/game", (req, resp) -> new JoinGameHandler().handle(req, resp));  //join game
        Spark.delete("/db",(req, resp) -> new ClearDBHandler().handle(req, resp)); //clear db


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