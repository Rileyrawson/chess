package server;

import com.google.gson.Gson;
import model.requests.LoginRequest;
import service.UserService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        LoginRequest request = (LoginRequest) gson.fromJson(req.body(), LoginRequest.class);
        String authToken = req.headers("authorization");

        //request.setAuthToken(authToken);

        res.type("application/json");

        UserService service = new UserService();

        return gson.toJson(service.login(request));


//        return gson.toJson(request);
    }
}
