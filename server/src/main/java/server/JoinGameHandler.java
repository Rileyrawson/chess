package server;

import com.google.gson.Gson;
import model.requests.JoinGameRequest;
import spark.Request;
import spark.Response;

public class JoinGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        JoinGameRequest request = (JoinGameRequest) gson.fromJson(req.body(), JoinGameRequest.class);
        String authToken = req.headers("authorization");

        request.setAuthToken(authToken);

        res.type("application/json");
        return gson.toJson(request);
    }
}
