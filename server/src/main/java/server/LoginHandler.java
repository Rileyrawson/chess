package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.requests.LoginRequest;
import model.response.ErrorResponse;
import service.UserService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        Singleton singleton = Singleton.getInstance();

        UserService service = singleton.getUserServiceInstance();
        res.type("application/json");

        try{
            LoginRequest request = gson.fromJson(req.body(), LoginRequest.class);
            Object result = service.login(request);
            res.status(200);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            if (exception.getMessage().equals("Error: unauthorized")){
                res.status(401);
            } else{
                res.status(500);
            }
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
