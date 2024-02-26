package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import model.requests.JoinGameRequest;

import model.response.ErrorResponse;
import service.GameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");
        GameService service = new GameService(new MemoryAuthDAO(), new MemoryGameDAO());
        res.type("application/json");

        try {
            JoinGameRequest request = (JoinGameRequest)gson.fromJson(req.body(), JoinGameRequest.class);
            Object result = service.joinGame(request, authToken);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            if (exception.getMessage().equals("Error: unauthorized")) {
                res.status(401);
            } else {
                res.status(500);
            }
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
