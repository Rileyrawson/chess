package server;

import com.google.gson.Gson;
import model.requests.LoginRequest;
import service.LoginService;
import spark.Request;
import spark.Response;

public class LoginHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        LoginRequest request = (LoginRequest) gson.fromJson(req.body(), LoginRequest.class);
        String authToken = req.headers("authorization");

        request.setAuthToken(authToken);

        res.type("application/json");

        LoginService service = new LoginService();

        return gson.toJson(service.login(request));


//        return gson.toJson(request);
    }
}
