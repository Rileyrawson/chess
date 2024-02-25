package server;

import com.google.gson.Gson;
import model.requests.CreateGameRequest;
import service.ListGameService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class ListGameHandler {
    public Object handle(Request req, Response res) {
        Gson gson = new Gson();
        ArrayList<String> gameList = new ArrayList<>();

        gameList.add("game1");
        gameList.add("game2");
        gameList.add("game3");



        ListGameService service = new ListGameService();

        return gson.toJson(service.listGame(request));

//        return gson.toJson(gameList);
    }

}
