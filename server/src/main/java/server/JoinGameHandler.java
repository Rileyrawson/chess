package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.requests.JoinGameRequest;

import model.response.ErrorResponse;
import service.GameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {

    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        Singleton singleton = Singleton.getInstance();

        String authToken = req.headers("authorization");
        GameService service = singleton.getGameServiceInstance();
        res.type("application/json");

        try {
            JoinGameRequest request = (JoinGameRequest)gson.fromJson(req.body(), JoinGameRequest.class);
            Object result = service.joinGame(request, authToken);
            res.status(200);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            if (exception.getMessage().equals("Error: bad request")) {
                res.status(400);
            }
            else if (exception.getMessage().equals("Error: unauthorized")){
                res.status(401);
            } else if (exception.getMessage().equals("Error: already taken")) {
                res.status(403);
            } else{
                res.status(500);
            }
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
