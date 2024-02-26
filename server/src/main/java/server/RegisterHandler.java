package server;

import com.google.gson.Gson;
import model.requests.RegisterRequest;
import service.UserService;
import spark.Request;
import spark.Response;

public class RegisterHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        RegisterRequest request = (RegisterRequest) gson.fromJson(req.body(), RegisterRequest.class);
        String authToken = req.headers("authorization");

//        request.setAuthToken(authToken);

        res.type("application/json");

        UserService service = new UserService();

        return gson.toJson(service.register(request));

//        return gson.toJson(request);
    }
}
