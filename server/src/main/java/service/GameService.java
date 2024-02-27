package service;

import chess.ChessGame;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.AuthData;
import model.GameData;
import model.requests.CreateGameRequest;
import model.requests.JoinGameRequest;
import model.response.ErrorResponse;

import java.util.Collection;

public class GameService {

    AuthDAO authDAO;
    GameDAO gameDAO;
    private int counter = 1;

    public GameService(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    private Object notAuthorized(String authToken) throws DataAccessException {
        AuthData data = authDAO.getAuth(AuthDAO.authToken);
        if (data == null) {
            return new ErrorResponse("Error: unauthorized");
        } else {
            return null;
        }
    }

    public Object createGame(CreateGameRequest request, String authToken) throws DataAccessException {

        AuthData authData = authDAO.getAuth(authToken);

        if (authData == null){
            throw new DataAccessException("Error: unauthorized");
        }

        int gameId = counter++;
        String whiteUsername = null;
        String blackUsername = null;
        String gameName = request.gameName();
        ChessGame game = new ChessGame();

        if (gameName == null){
            throw new DataAccessException("Error: bad request"); //try to create game if not 400(request/client error)
        }

        GameData data = new GameData(gameId, whiteUsername, blackUsername, gameName, game);
                // try to create game if not 400(request/client error) or 500(server error)

        gameDAO.createGame(data);

        return data;
    }

    public Object joinGame(JoinGameRequest request, String authToken) throws DataAccessException {
        int gameId = request.gameID();
        String playerColor = request.playerColor();

        AuthData authData = authDAO.getAuth(authToken);

        if (authData == null){
            throw new DataAccessException("Error: unauthorized");
        }
        GameData data = gameDAO.getGameByID(gameId);
        String username = authDAO.getAuth(authToken).username();
        String whiteUsername = null;
        String blackUsername = null;
        GameData updatedData;

        if (data == null) {  //Verifies that the specified game exists
            throw new DataAccessException("Error: bad request"); //400 error: bad request
        }

        if (request.playerColor() == null) {
            //if no color, user is joined as an observer
            updatedData = new GameData(data.gameID(), data.whiteUsername(), data.blackUsername(), data.gameName(), data.game());
        } else if (request.playerColor().equals("WHITE")){
            if (data.whiteUsername() == null){
                whiteUsername = username; //if color specified, adds the caller as the requested color to the game
                updatedData = new GameData(data.gameID(), whiteUsername, data.blackUsername(), data.gameName(), data.game());
            } else {
                throw new DataAccessException("Error: already taken");
            }
        } else if (request.playerColor().equals("BLACK")){
            if (data.blackUsername() == null){ //if color specified, adds the caller as the requested color to the game
                blackUsername = username;
                updatedData = new GameData(data.gameID(), data.whiteUsername(), blackUsername, data.gameName(), data.game());
            } else {
                throw new DataAccessException("Error: already taken");
            }
        } else {
            throw new DataAccessException("Error: bad request");
        }

        gameDAO.updateGame(updatedData);
        
        Object response = new Object();
        return response;//return empty json body on success
    }

    public Object listGame(String authToken) throws DataAccessException {

        AuthData data = authDAO.getAuth(authToken);

        if (data == null){
            throw new DataAccessException("Error: unauthorized");   // 401 error: unauthorized
        } else {
            return gameDAO.listGames();
        }
    }
}
