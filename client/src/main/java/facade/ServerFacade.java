package facade;

import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.GameListData;
import ui.PostloginUI;

import java.net.HttpURLConnection;
import java.net.URI;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
public class ServerFacade {

    private static int port = 8080;

    public static void setPort(int port) {
        ServerFacade.port = port;
    }

    public static AuthData register(String username, String password, String email){
        try {
            URI uri = new URI("http://localhost:" + port + "/user");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.addRequestProperty("Content-Type", "application/json");
            var body = Map.of("username", username, "password", password, "email", email);

            var outputStream = http.getOutputStream();
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());

            http.connect();  // Make the request

            InputStream respBody = http.getInputStream();  // Output the response body
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            AuthData data = new Gson().fromJson(inputStreamReader, AuthData.class);

//            login(username, String.valueOf(data));
//            System.out.println(data);

            return data;

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new AuthData("error", "error");
    }
    public static AuthData login(String username, String password){
        try {
            URI uri = new URI("http://localhost:" + port + "/session");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.addRequestProperty("Content-Type", "application/json");
            var body = Map.of("username", username, "password", password);
            var outputStream = http.getOutputStream();
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());

            http.connect();  // Make the request

            InputStream respBody = http.getInputStream();  // Output the response body
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            AuthData data = new Gson().fromJson(inputStreamReader, AuthData.class);
//            System.out.println(data);

            return data;

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new AuthData("error", "error");
    }


    public static String createGame(String gameName, AuthData authData){
        try {
            URI uri = new URI("http://localhost:" + port + "/game");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.addRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Authorization", authData.authToken());
            var body = Map.of("gameName", gameName);
            try (var outputStream = http.getOutputStream()) {
                var jsonBody = new Gson().toJson(body);
                outputStream.write(jsonBody.getBytes());
            }
            http.connect();  // Make the request
            try (InputStream respBody = http.getInputStream()) {  // Output the response body
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
//                System.out.println(new Gson().fromJson(inputStreamReader, Map.class));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }
    public static String listGames(AuthData authData){ //TODO: list games as a numbered list, not just json
        try {
            URI uri = new URI("http://localhost:" + port + "/game");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("GET");
            http.setRequestProperty("Authorization", authData.authToken());
            http.addRequestProperty("Content-Type", "application/json");

            http.connect();   // Make the request
            try (InputStream respBody = http.getInputStream()) {      // Output the response body
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
                GameListData data = new Gson().fromJson(inputStreamReader, GameListData.class);
                ArrayList<GameData> games = data.games();
                for (int i = 0; i < games.size(); i++){
                    System.out.println( (i + 1) + ": " + games.get(i));
                }
//                GameListData data = new Gson().fromJson(inputStreamReader, GameListData.class);
//                System.out.println(data);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }


    public static String joinGame(String color, String gameID, AuthData authData){  // todo: draw board
        try {
            URI uri = new URI("http://localhost:" + port + "/game");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("PUT");

            http.setRequestProperty("Authorization", authData.authToken());
            http.addRequestProperty("Content-Type", "application/json");

            String bodyColor = color.toUpperCase();
            var body = Map.of("playerColor", bodyColor, "gameID", gameID, "username", authData.username());


            var outputStream = http.getOutputStream();
            var jsonBody = new Gson().toJson(body);
            outputStream.write(jsonBody.getBytes());

            http.connect();   // Make the request

            InputStream respBody = http.getInputStream();     // Output the response body
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
//            System.out.println(new Gson().fromJson(inputStreamReader, Map.class));

//            StringBuilder sb = new StringBuilder();
//            sb.append(authData.username());
//            sb.append(" has joined game ");
//            sb.append(gameID);
//            sb.append(" as ");
//            sb.append(bodyColor);
//            sb.append("\n");
//            sb.append(PostloginUI.drawBoard(color));
//            System.out.println(sb);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }
    public static String joinObserver(String gameID, AuthData authData){ //todo: draw board
        try {
            URI uri = new URI("http://localhost:" + port + "/game");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("PUT");
            http.setRequestProperty("Authorization", authData.authToken());
            http.setRequestProperty("Username", authData.username());
            http.addRequestProperty("Content-Type", "application/json");

            var body = Map.of("gameID", gameID);
            try (var outputStream = http.getOutputStream()) {
                var jsonBody = new Gson().toJson(body);
                outputStream.write(jsonBody.getBytes());
            }

            http.connect();  // Make the request

            try (InputStream respBody = http.getInputStream()) {  // Output the response body
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
//                System.out.println(new Gson().fromJson(inputStreamReader, Map.class));

//                StringBuilder sb = new StringBuilder();
//                sb.append(authData.username());
//                sb.append(" has joined game ");
//                sb.append(gameID);
//                sb.append(" as an observer  \n");
//                sb.append(PostloginUI.drawBoard(""));
//                System.out.println(sb);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }

    public static String logout(AuthData authData){
        try {
            URI uri = new URI("http://localhost:" + port + "/session");
            HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
            http.setDoOutput(true);
            http.setRequestMethod("DELETE");
            http.setRequestProperty("Authorization", authData.authToken());
            http.addRequestProperty("Content-Type", "application/json");

            http.connect();   // Make the request

            try (InputStream respBody = http.getInputStream()) {      // Output the response body
                InputStreamReader inputStreamReader = new InputStreamReader(respBody);
//                System.out.println(new Gson().fromJson(inputStreamReader, Map.class));
                GameData data = new Gson().fromJson(inputStreamReader, GameData.class);
                System.out.println("Logged out");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
        return "success";
    }









    //leave game (remove user from game in db) (message w/player name)

    //resign (forfeit game. can't play anymore but doesn't remove player from game) (message w/player name)

    //Make Move (input (a,b) (x,y))? (update & print board) (probably invalid move error message & success message(move & player name))




    //player in check notification
    //player in checkmate notification
}
