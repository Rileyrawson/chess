package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import model.GameData;
import model.response.ErrorResponse;
import service.GameService;
import spark.Request;
import spark.Response;
import java.util.Collection;

public class ListGameHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");
        GameService service = new GameService(new MemoryAuthDAO(), new MemoryGameDAO());
        res.type("application/json");


        try {
            Collection<GameData> games = null;
            games = service.listGame(authToken);
            return gson.toJson(games);
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
