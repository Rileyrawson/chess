package server;

import com.google.gson.Gson;
import model.requests.CreateGameRequest;
import service.ClearDBService;
import spark.Request;
import spark.Response;

public class ClearDBHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

//        res.type("text/plain");

        res.type("application/json");



        ClearDBService service = new ClearDBService();

        return gson.toJson(service.clear());
//        return gson.toJson("{'Status': 'OK',}");
    }
}
