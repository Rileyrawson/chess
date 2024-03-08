package passoffTests.serviceTests;

import Singleton.Singleton;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.DatabaseManager;
import dataAccess.GameDAO;
import model.AuthData;
import model.GameData;
import model.GameListData;
import model.requests.CreateGameRequest;
import model.requests.JoinGameRequest;
import model.requests.LoginRequest;
import model.requests.RegisterRequest;
import org.junit.jupiter.api.*;
import passoffTests.testClasses.TestException;
import service.ClearDBService;
import service.GameService;
import service.UserService;

public class ServiceTests {

    private Singleton singleton = Singleton.getInstance();
    private UserService userService = singleton.getUserServiceInstance();
    private GameService gameService = singleton.getGameServiceInstance();
    private ClearDBService clearDBService = singleton.getClearDBServiceInstance();
    private String validAuthToken;
    private String invalidAuthToken = "notValidAuth";
    private GameData testGame;


    @BeforeEach
    public void prepare() throws DataAccessException {
        DatabaseManager.createDatabase();
        DatabaseManager.createTables();
        clearDBService.clear();
        AuthData response = (AuthData) userService.register(new RegisterRequest("username", "pass", "email"));
        validAuthToken = response.authToken();
        testGame = (GameData) gameService.createGame(new CreateGameRequest("gameName"), validAuthToken);
    }

    @AfterEach
    public void clearAll() throws TestException, DataAccessException {
        singleton.getClearDBServiceInstance();
    }

    //register
    @Test
    @Order(1)
    @DisplayName("Register Service Test Positive")
    public void positiveRegister() throws TestException, DataAccessException {
        RegisterRequest request = new RegisterRequest("username1", "pass", "email");
        AuthData response;

        response = (AuthData) userService.register(request);
        String tempAuthToken = response.authToken();
        AuthData correctResponse = new AuthData(tempAuthToken, "username1");
        Assertions.assertEquals(response, correctResponse, "responses didn't match");
    }
    @Test
    @Order(2)
    @DisplayName("Register Service Test Negative")
    public void negativeRegister() throws TestException {
        RegisterRequest request = new RegisterRequest(null, null, null);
        AuthData response;
        DataAccessException errorResponse = null;
        try {
            response = (AuthData) userService.register(request);
            validAuthToken = response.authToken();
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Error: bad request";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //login
    @Test
    @Order(3)
    @DisplayName("Login Service Test Positive")
    public void positiveLogin() throws TestException, DataAccessException {
        LoginRequest request = new LoginRequest("username", "pass");
        AuthData response;
        response = (AuthData) userService.login(request);
        validAuthToken = response.authToken();
        AuthData correctResponse = new AuthData(validAuthToken, "username");
        Assertions.assertEquals(response, correctResponse, "responses didn't match");
    }
    @Test
    @Order(4)
    @DisplayName("Login Service Test Negative")
    public void negativeLogin() throws TestException{
        LoginRequest request = new LoginRequest(null, null);
        AuthData response;
        DataAccessException errorResponse = null;
        try {
            response = (AuthData) userService.login(request);
            validAuthToken = response.authToken();
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Error: unauthorized";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //logout
    @Test
    @Order(5)
    @DisplayName("Logout Service Test Positive")
    public void positiveLogout() throws TestException, DataAccessException  {
        userService.logout(validAuthToken);
        AuthDAO authDAO = singleton.getAuthDAOInstance();
        AuthData data = authDAO.getAuth(validAuthToken);
        Assertions.assertEquals(data, null, "authToken not removed from DB");
    }
    @Test
    @Order(6)
    @DisplayName("Logout Service Test Negative")
    public void negativeLogout() throws TestException {
        DataAccessException errorResponse = null;
        try {
            userService.logout(invalidAuthToken);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Error: unauthorized";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //createGame
    @Test
    @Order(7)
    @DisplayName("Create Game Service Test Positive")
    public void positiveCreateGame() throws TestException, DataAccessException {
        CreateGameRequest request = new CreateGameRequest("testName");
        GameData response;
        response = (GameData) gameService.createGame(request, validAuthToken);
        GameData correctResponse = singleton.getGameDAOInstance().getGameByName(request.gameName());
        Assertions.assertEquals(response, correctResponse, "responses didn't match");
    }
    @Test
    @Order(8)
    @DisplayName("Create Game Service Test Negative")
    public void negativeCreateGame() throws TestException {
        DataAccessException errorResponse = null;
        try {
            CreateGameRequest request = new CreateGameRequest("gameName");
            gameService.createGame(request, invalidAuthToken);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Error: unauthorized";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //joinGame
    @Test
    @Order(9)
    @DisplayName("Join Game Service Test Positive")
    public void positiveJoinGame() throws TestException, DataAccessException  {
        JoinGameRequest request = new JoinGameRequest("WHITE", testGame.gameID());
        Object response;
        response = gameService.joinGame(request, validAuthToken);
        Assertions.assertTrue(response != null, "response is null instead of empty response");
    }
    @Test
    @Order(10)
    @DisplayName("Join Game Service Test Negative")
    public void negativeJoinGame() throws TestException, DataAccessException {
        DataAccessException errorResponse = null;
        String correctResponse = null;
        try {
            JoinGameRequest request = new JoinGameRequest("WHITE", testGame.gameID());
            Object response = gameService.joinGame(request, invalidAuthToken);
        }catch (DataAccessException exception){
            errorResponse = exception;
        }
        correctResponse = "Error: unauthorized";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //listGame
    @Test
    @Order(11)
    @DisplayName("List Game Service Test Positive")
    public void positiveListGame() throws TestException, DataAccessException  {
        GameListData response = (GameListData) gameService.listGame(validAuthToken);
        GameDAO gameDAO = singleton.getGameDAOInstance();
        GameListData gameList = gameDAO.listGames();
        Assertions.assertEquals(response, gameList, "list of games doesn't match");
    }
    @Test
    @Order(12)
    @DisplayName("List Game Service Test Negative")
    public void negativeListGame() throws TestException {
        DataAccessException errorResponse = null;
        String correctResponse = null;
        try{
            GameListData response = (GameListData) gameService.listGame(invalidAuthToken);
            GameDAO gameDAO = singleton.getGameDAOInstance();
            GameListData gameList = gameDAO.listGames();
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        correctResponse = "Error: unauthorized";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    //clearDB
    @Test
    @Order(13)
    @DisplayName("Clear Service Test")
    public void clear() throws TestException, DataAccessException {
        clearDBService.clear();
        AuthDAO authDAO = singleton.getAuthDAOInstance();
        AuthData data = authDAO.getAuth(validAuthToken);
        Assertions.assertEquals(data, null, "authToken not removed from DB");
    }



}


