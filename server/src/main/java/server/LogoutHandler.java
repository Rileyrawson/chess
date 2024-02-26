package server;

import com.google.gson.Gson;

import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import model.response.ErrorResponse;
import service.UserService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");
        UserService service = new UserService(new MemoryAuthDAO(), new MemoryUserDAO());
        res.type("application/json");

        try {
            Object result = service.logout(authToken);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }

    }
}
