package server;

import com.google.gson.Gson;
import model.requests.LogoutRequest;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");

        LogoutRequest request = new LogoutRequest(authToken);

        res.type("application/json");
        return gson.toJson(request);
    }
}
