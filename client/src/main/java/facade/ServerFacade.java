package facade;

import com.google.gson.Gson;
import model.requests.LoginRequest;
import model.requests.RegisterRequest;


import java.net.HttpURLConnection;
import java.net.URI;
import java.io.InputStreamReader;
import java.io.InputStream;
public class ServerFacade {


    public static void login(String username, String password){
        new LoginRequest(username, password);

//        URI uri = new URI("http://localhost:8080/session");
//        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
//        http.setRequestMethod("POST");
//
//        // Make the request
//        http.connect();
//
//        // Output the response body
//        try (InputStream respBody = http.getInputStream()) {
//            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
//            System.out.println(new Gson().fromJson(inputStreamReader, Map.class));
//        }

    }
    public static void register(String username, String password, String email){
        new RegisterRequest(username, password, email);
    }
    public static void CreateGame(String gameName){

    }
    public static void listGames(){

    }
    public static void joinGame(String color, String gameID){

    }
    public static void joinObserver(String gameID){

    }



}
