package server;

import com.google.gson.Gson;
import model.requests.CreateGameRequest;
import spark.Request;
import spark.Response;

public class ClearDBHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

//        res.type("text/plain");

        res.type("application/json");
        return gson.toJson("{'Status': 'OK',}");
    }
}
