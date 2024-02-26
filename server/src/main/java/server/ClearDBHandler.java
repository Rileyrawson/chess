package server;

import com.google.gson.Gson;
import dataAccess.*;
import model.requests.CreateGameRequest;
import model.response.ErrorResponse;
import service.ClearDBService;
import service.UserService;
import spark.Request;
import spark.Response;

public class ClearDBHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        ClearDBService service = new ClearDBService(new MemoryUserDAO(), new MemoryGameDAO(), new MemoryAuthDAO());
//        res.type("application/json");

        try {
            Object result = service.clear();
            return gson.toJson(result);

        } catch (DataAccessException exception) {
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
