package clientTests;

import facade.ServerFacade;
import model.AuthData;
import org.junit.jupiter.api.*;
import server.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;


public class ServerFacadeTests {

    private static Server server;
    private static int port;

    @BeforeAll
    public static void init() {
        server = new Server();
        port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
    }

    @BeforeEach
    public void prepare() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:" + port + "/db");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setDoOutput(true);
        http.setRequestMethod("DELETE");

        http.connect();

        InputStream respBody = http.getInputStream();  // Output the response body
//        System.out.println(respBody);
    }

    @BeforeEach
    public void clear() throws URISyntaxException, IOException {
        URI uri = new URI("http://localhost:" + port + "/db");
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setDoOutput(true);
        http.setRequestMethod("DELETE");

        http.connect();

        InputStream respBody = http.getInputStream();  // Output the response body
//        System.out.println(respBody);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    //register
    @Test
    @Order(1)
    public void positiveRegister(){
        AuthData data = ServerFacade.register("u", "p", "e");
        Assertions.assertNotEquals("error", data.authToken());
    }

    @Test
    @Order(2)
    public void negativeRegister(){
        AuthData data1 = ServerFacade.register("u2", "p", "e");
        AuthData data2 = ServerFacade.register("u2", "p", "e");
        Assertions.assertEquals("error", data2.authToken());
    }

    //login
    @Test
    @Order(3)
    public void positiveLogin(){
        AuthData register = ServerFacade.register("u3", "p", "e");
        AuthData login = ServerFacade.login("u3", "p");
        Assertions.assertNotEquals("error", login.authToken());
    }

    @Test
    @Order(4)
    public void negativeLogin(){
        AuthData login = ServerFacade.login("u4", "p");
        Assertions.assertEquals("error", login.authToken());
    }

    //create game
    @Test
    @Order(5)
    public void positiveCreateGame(){
        AuthData register = ServerFacade.register("u5", "p", "e");
        AuthData login = ServerFacade.login("u5", "p");
        ServerFacade.createGame("name", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("success", list);
    }

    @Test
    @Order(6)
    public void negativeCreateGame(){
        AuthData login = ServerFacade.login("u6", "p");
        ServerFacade.createGame("name", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("error", list);
    }

    //list games
    @Test
    @Order(7)
    public void positiveList(){
        AuthData register = ServerFacade.register("u7", "p", "e");
        AuthData login = ServerFacade.login("u7", "p");
        ServerFacade.createGame("name", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("success", list);
    }

    @Test
    @Order(8)
    public void negativeList(){
        AuthData login = ServerFacade.login("u8", "p");
        ServerFacade.createGame("name", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("error", list);
    }

    //join game
    @Test
    @Order(9)
    public void positiveJoinGame(){
        AuthData register = ServerFacade.register("u9", "p", "e");
        AuthData login = ServerFacade.login("u9", "p");
        ServerFacade.createGame("name", login);
        ServerFacade.joinGame("WHITE", "1", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("success", list);
    }

    @Test
    @Order(10)
    public void negativeJoinGame(){
        AuthData dummy = new AuthData("1", "fhdfyguhij");
        ServerFacade.createGame("name", dummy);
        ServerFacade.joinGame("WHITE", "1", dummy);
        String list = ServerFacade.listGames(dummy);
        Assertions.assertEquals("error", list);
    }

    //join observer
    @Test
    @Order(11)
    public void positiveJoinObserver(){
        AuthData register = ServerFacade.register("u11", "p", "e");
        AuthData login = ServerFacade.login("u11", "p");
        ServerFacade.createGame("name", login);
        ServerFacade.joinObserver("1",login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("success", list);
    }

    @Test
    @Order(12)
    public void negativeJoinObserver(){
        AuthData register = ServerFacade.register("u12", "p", "e");
        AuthData login = ServerFacade.login("u12", "p");
        ServerFacade.createGame("name", login);
        ServerFacade.joinObserver("1", register);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("error", list);
    }


    @Test
    @Order(13)
    public void positiveLogout(){
        AuthData register = ServerFacade.register("u12", "p", "e");
        AuthData login = ServerFacade.login("u12", "p");
        ServerFacade.logout(login);
        ServerFacade.createGame("name", login);
        String list = ServerFacade.listGames(login);
        Assertions.assertEquals("error", list);
    }
    @Test
    @Order(14)
    public void negativeLogout(){
        AuthData register = ServerFacade.register("u12", "p", "e");
        ServerFacade.logout(register);
        ServerFacade.createGame("name", register);
        String list = ServerFacade.listGames(register);
        Assertions.assertEquals("error", list);
    }


}