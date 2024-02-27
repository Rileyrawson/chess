package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.GameData;
import model.GameListData;
import model.response.ErrorResponse;
import service.GameService;
import spark.Request;
import spark.Response;
import java.util.Collection;

public class ListGameHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        Singleton singleton = Singleton.getInstance();

        String authToken = req.headers("authorization");
        GameService service =singleton.getGameServiceInstance();
        res.type("application/json");

        try {
            GameListData games = null;
            games = (GameListData) service.listGame(authToken);
            res.status(200);
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
