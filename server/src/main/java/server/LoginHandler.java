package server;

import com.google.gson.Gson;
import model.requests.CreateGameRequest;
import spark.Request;
import spark.Response;

public class LoginHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        CreateGameRequest request = (CreateGameRequest) gson.fromJson(req.body(), CreateGameRequest.class);
        String authToken = req.headers("authorization");

        request.setAuthToken(authToken);

        res.type("application/json");
        return gson.toJson(request);
    }
}
