package server;

import com.google.gson.Gson;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

//        Spark.externalStaticFileLocation("/Users/rr/IdeaProjects/chess/server/src/main/resources/web");

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