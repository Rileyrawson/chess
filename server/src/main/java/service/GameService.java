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
    private int counter = 0;

    public GameService(AuthDAO authDAO, GameDAO gameDAO) {
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }

    public Object notAuthorized(String authToken) throws DataAccessException {
        AuthData data = authDAO.getAuth(AuthDAO.authToken);
        if (data == null) {
            return new ErrorResponse("Error: unauthorized");
        } else {
            return null;
        }
    }

    public Object createGame(CreateGameRequest request) throws DataAccessException {

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

        GameData data = gameDAO.getGameByID(gameId);
        String username = authDAO.getAuth(authToken).username();
        String whiteUsername = null;
        String blackUsername = null;
        GameData updatedData;

        if (data == null) {  //Verifies that the specified game exists
            throw new DataAccessException("Error: bad request"); //400 error: bad request
        }

        if (username == null){
            throw new DataAccessException("Error: unauthorized");
        }

        if (request.playerColor().equals("WHITE")){
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
            //if no color, user is joined as an observer
            updatedData = new GameData(data.gameID(), data.whiteUsername(), data.blackUsername(), data.gameName(), data.game());
            //TODO: add an observer client?
        }

        gameDAO.updateGame(updatedData);

        return "success";

        //200
        //401 error: unauthorized
        //403 error: already taken
        //500 error: description
    }

    public Collection<GameData> listGame(String authToken) throws DataAccessException {

        AuthData data = authDAO.getAuth(authToken);

        if (data == null){
            throw new DataAccessException("Error: unauthorized");   // 401 error: unauthorized

        } else {
            return gameDAO.listGames();
        }

        // NOTE: White or Black username may be null
        // 200 success { "games": [{"gameID": 1234, "whiteUsername":"", "blackUsername":"", "gameName:""} ]}
        // 500 error: description
    }
}
