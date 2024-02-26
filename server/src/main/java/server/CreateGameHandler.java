package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import model.requests.CreateGameRequest;
import model.response.ErrorResponse;
import service.GameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");
        GameService service = new GameService(new MemoryAuthDAO(), new MemoryGameDAO());
        res.type("application/json");
        try {
            Object errorResponse = service.notAuthorized(authToken);
            if (errorResponse != null) {
                res.status(401);
                return gson.toJson(errorResponse);
            }
            CreateGameRequest request = (CreateGameRequest) gson.fromJson(req.body(), CreateGameRequest.class);
            Object result = service.createGame(request);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
