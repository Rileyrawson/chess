package server;

import com.google.gson.Gson;
import model.requests.JoinGameRequest;
import service.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        JoinGameRequest request = (JoinGameRequest) gson.fromJson(req.body(), JoinGameRequest.class);
        String authToken = req.headers("authorization");

        request.setAuthToken(authToken);

        res.type("application/json");


        JoinGameService service = new JoinGameService();

        return gson.toJson(service.joinGame(request));
    }
}
