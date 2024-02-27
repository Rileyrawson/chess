package server;

import Singleton.Singleton;
import com.google.gson.Gson;
import dataAccess.*;
import model.response.ErrorResponse;
import service.ClearDBService;
import spark.Request;
import spark.Response;

public class ClearDBHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();

        Singleton singleton = Singleton.getInstance();
        ClearDBService service = singleton.getClearDBServiceInstance();

        try {
            Object result = service.clear();
            res.status(200);
            return gson.toJson(result);
        } catch (DataAccessException exception) {
            res.status(500);
            return gson.toJson(new ErrorResponse(exception.getMessage()));
        }
    }
}
