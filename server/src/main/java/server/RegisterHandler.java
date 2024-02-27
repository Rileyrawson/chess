package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import model.requests.RegisterRequest;
import model.response.ErrorResponse;
import service.UserService;
import spark.Request;
import spark.Response;

public class RegisterHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        Singleton singleton = Singleton.getInstance();

        String authToken = req.headers("authorization");
        UserService service = singleton.getUserServiceInstance();
        res.type("application/json");
        try {
            RegisterRequest request = (RegisterRequest)gson.fromJson(req.body(), RegisterRequest.class);
            Object result = service.register(request);
            res.status(200); //200 { "username":"", "authToken":"" }
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            if (exception.getMessage().equals("Error: bad request")){
                res.status(400); //400 error: bad request
            } else if (exception.getMessage().equals("Error: already taken")) {
                res.status(403);//400 error: bad request
            } else{
                res.status(500);//500 error: description
            }
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
