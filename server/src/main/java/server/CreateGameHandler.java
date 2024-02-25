package server;

import com.google.gson.Gson;
import model.requests.CreateGameRequest;
import service.CreateGameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        CreateGameRequest request = (CreateGameRequest) gson.fromJson(req.body(), CreateGameRequest.class);
        String authToken = req.headers("authorization");

        request.setAuthToken(authToken);

        res.type("application/json");

        CreateGameService service = new CreateGameService();

        return gson.toJson(service.createGame(request));
    }
}
