package server;

import com.google.gson.Gson;
import model.requests.LogoutRequest;
import service.LogoutService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");

        LogoutRequest request = new LogoutRequest(authToken);

        res.type("application/json");

        LogoutService service = new LogoutService();

        return gson.toJson(service.logout(request));

//        return gson.toJson(request);
    }
}
