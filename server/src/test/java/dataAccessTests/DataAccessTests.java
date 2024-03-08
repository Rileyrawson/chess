package dataAccessTests;

import Singleton.Singleton;
import chess.ChessGame;
import dataAccess.*;
import model.*;
import model.AuthData;
import model.GameData;
import model.requests.CreateGameRequest;
import model.requests.RegisterRequest;
import org.junit.jupiter.api.*;
import passoffTests.testClasses.TestException;
import service.ClearDBService;
import service.GameService;
import service.UserService;

public class DataAccessTests {

    private Singleton singleton = Singleton.getInstance();
    private UserService userService = singleton.getUserServiceInstance();
    private GameService gameService = singleton.getGameServiceInstance();
    private ClearDBService clearDBService = singleton.getClearDBServiceInstance();
    private UserDAO userDao = singleton.getUserDAOInstance();
    private GameDAO gameDAO = singleton.getGameDAOInstance();
    private AuthDAO authDAO = singleton.getAuthDAOInstance();;
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

    @Test
    @Order(1)
    @DisplayName("User Clear Test Positive")
    public void clearUser() throws TestException, DataAccessException {
        userDao.clear();
        UserData data = userDao.getUser("username");
        Assertions.assertEquals(data, null, "responses didn't match");
    }

    @Test
    @Order(2)
    @DisplayName("Create User Test Positive")
    public void positiveCreateUser() throws TestException, DataAccessException {
        UserData userData = new UserData("username1","password", "email");
        userDao.createUser(userData);
        UserData response = userDao.getUser("username1");
        Assertions.assertEquals(userData, response, "responses didn't match");
    }

    @Test
    @Order(3)
    @DisplayName("Create User Test Negative")
    public void negativeCreateUser() throws TestException, DataAccessException {
        UserData userData = new UserData(null,null, null);
        DataAccessException errorResponse = null;
        try {
            userDao.createUser(userData);
            UserData response = userDao.getUser("username");
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'username' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(4)
    @DisplayName("Get User Test Positive")
    public void positiveGetUser() throws TestException, DataAccessException {
        UserData userData = new UserData("username1","password", "email");
        userDao.createUser(userData);
        UserData response = userDao.getUser("username1");
        Assertions.assertEquals(userData, response, "responses didn't match");
    }

    @Test
    @Order(5)
    @DisplayName("Get User Test Negative")
    public void negativeGetUser() throws TestException, DataAccessException {
        UserData userData = new UserData(null,null, null);
        DataAccessException errorResponse = null;
        try {
            userDao.createUser(userData);
            UserData response = userDao.getUser("username");
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'username' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }


    @Test
    @Order(6)
    @DisplayName("Auth Clear Test")
    public void clearAuth() throws TestException, DataAccessException {
        authDAO.clear();
        AuthData data = authDAO.getAuth(validAuthToken);
        Assertions.assertEquals(data, null, "responses didn't match");
    }

    @Test
    @Order(7)
    @DisplayName("Create Auth Test Positive")
    public void positiveCreateAuth() throws TestException, DataAccessException {
        AuthData authData = new AuthData("123","username");
        authDAO.createAuth(authData);
        AuthData response = authDAO.getAuth("123");
        Assertions.assertEquals(authData, response, "responses didn't match");
    }

    @Test
    @Order(8)
    @DisplayName("Create Auth Test Negative")
    public void negativeCreateAuth() throws TestException, DataAccessException {
        AuthData authData = new AuthData(null, "username1");
        DataAccessException errorResponse = null;
        try {
            authDAO.createAuth(authData);
            AuthData response = authDAO.getAuth(null);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'authToken' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(9)
    @DisplayName("Get Auth Test Positive")
    public void positiveGetAuth() throws TestException, DataAccessException {
        AuthData authData = new AuthData("123","username");
        authDAO.createAuth(authData);
        AuthData response = authDAO.getAuth("123");
        Assertions.assertEquals(authData, response, "responses didn't match");
    }

    @Test
    @Order(10)
    @DisplayName("Get Auth Test Negative")
    public void negativeGetAuth() throws TestException, DataAccessException {
        AuthData authData = new AuthData(null, "username1");
        DataAccessException errorResponse = null;
        try {
            authDAO.createAuth(authData);
            AuthData response = authDAO.getAuth(null);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'authToken' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(11)
    @DisplayName("Delete Auth Test Positive")
    public void positiveDeleteAuth() throws TestException, DataAccessException {
        AuthData authData = new AuthData("123","username");
        authDAO.createAuth(authData);
        authDAO.deleteAuth(authData);
        AuthData response = authDAO.getAuth("123");
        Assertions.assertEquals(null, response, "responses didn't match");
    }

    @Test
    @Order(12)
    @DisplayName("Delete Auth Test Negative")
    public void negativeDeleteAuth() throws TestException, DataAccessException {
        AuthData authData = null;
        DataAccessException errorResponse = null;
        AuthData response = null;
        try {
            authDAO.deleteAuth(authData);
            response = authDAO.getAuth(null);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Unable to read data: Cannot invoke \"model.AuthData.authToken()\" because \"data\" is null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }




    @Test
    @Order(13)
    @DisplayName("Game Clear Test")
    public void clearGame() throws TestException, DataAccessException {
        gameDAO.clear();
        GameData data = gameDAO.getGameByName("gameName");
        Assertions.assertEquals(data, null, "responses didn't match");
    }

    @Test
    @Order(14)
    @DisplayName("Create Game Test Positive")
    public void positiveCreateGame() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "name", new ChessGame());
        gameDAO.createGame(gameData);
        GameData response = gameDAO.getGameByID(1);
        Assertions.assertEquals(gameData, response, "responses didn't match");
    }

    @Test
    @Order(15)
    @DisplayName("Create Game Test Negative")
    public void negativeCreateGame() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, null, new ChessGame());
        DataAccessException errorResponse = null;
        try {
            gameDAO.createGame(gameData);
            GameData response = gameDAO.getGameByID(1);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'gameName' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(16)
    @DisplayName("Get Game By Name Test Positive")
    public void positiveGetGameByName() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "name", new ChessGame());
        gameDAO.createGame(gameData);
        GameData response = gameDAO.getGameByName("name");
        Assertions.assertEquals(gameData, response, "responses didn't match");
    }

    @Test
    @Order(17)
    @DisplayName("Get Game By Name Test Negative")
    public void negativeGetGameByName() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, null, new ChessGame());
        DataAccessException errorResponse = null;
        try {
            gameDAO.createGame(gameData);
            gameDAO.getGameByName(null);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Column 'gameName' cannot be null";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(18)
    @DisplayName("Get Game By ID Test Positive")
    public void positiveGetGameByID() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "name", new ChessGame());
        gameDAO.createGame(gameData);
        GameData response = gameDAO.getGameByID(1);
        Assertions.assertEquals(gameData, response, "responses didn't match");
    }

    @Test
    @Order(19)
    @DisplayName("Get Game By ID Test Negative")
    public void negativeGetGameByID() throws TestException, DataAccessException {
        DataAccessException errorResponse = null;
        try {
            gameDAO.getGameByID(-1);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Unable to read data: Invalid Game ID";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(20)
    @DisplayName("List Games Test Positive")
    public void positiveListGames() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "name", new ChessGame());
        gameDAO.createGame(gameData);
        GameListData expected = (GameListData) gameService.listGame(validAuthToken);
        GameListData response = gameDAO.listGames();
        Assertions.assertEquals(expected, response, "responses didn't match");
    }

    @Test
    @Order(21)
    @DisplayName("List Games Test Negative")
    public void negativeListGames() throws TestException, DataAccessException {
        gameDAO.clear();
        DataAccessException errorResponse = null;
        try {
            GameListData gameListData = gameDAO.listGames();
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Unable to read data: no games in the database";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }

    @Test
    @Order(22)
    @DisplayName("Update Game Test Positive")
    public void positiveUpdateGame() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "name", new ChessGame());
        gameDAO.createGame(gameData);
        GameData gameDataUpdate = new GameData(1, "white", null, "name", new ChessGame());
        gameDAO.updateGame(gameDataUpdate);
        GameData response = gameDAO.getGameByID(1);
        Assertions.assertEquals(gameDataUpdate, response, "responses didn't match");
    }

    @Test
    @Order(23)
    @DisplayName("Update Game Test Negative")
    public void negativeUpdateGame() throws TestException, DataAccessException {
        GameData gameData = new GameData(1,null, null, "game", new ChessGame());
        DataAccessException errorResponse = null;
        try {
            gameDAO.createGame(gameData);
            GameData response = gameDAO.getGameByID(1);
            GameData gameDataUpdate = new GameData(2, "white", null, "name", new ChessGame());
            gameDAO.updateGame(gameDataUpdate);
        } catch (DataAccessException exception){
            errorResponse = exception;
        }
        String correctResponse = "Error: bad request";
        Assertions.assertEquals(errorResponse.getMessage(), correctResponse, "wrong error message");
    }




}
