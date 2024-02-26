package server;

import com.google.gson.Gson;
import dataAccess.DataAccessException;
import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryUserDAO;
import model.requests.LoginRequest;
import model.requests.RegisterRequest;
import model.response.ErrorResponse;
import service.UserService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        UserService service = new UserService(new MemoryAuthDAO(), new MemoryUserDAO());
        res.type("application/json");

        try{
            LoginRequest request = gson.fromJson(req.body(), LoginRequest.class);
            Object result = service.login(request);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
