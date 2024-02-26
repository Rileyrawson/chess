package server;

import com.google.gson.Gson;

import service.UserService;
import spark.Request;
import spark.Response;

public class LogoutHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        String authToken = req.headers("authorization");

        //LogoutRequest request = new LogoutRequest(authToken);

        res.type("application/json");

        UserService service = new UserService();

        //return gson.toJson(service.logout(request));

//        return gson.toJson(request);
        return null;
    }
}
