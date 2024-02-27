package dataAccess;

import model.GameData;
import model.GameListData;
import spark.Request;

import java.util.List;

public interface GameDAO {

    void clear() throws DataAccessException;           //remove everything from db
    void createGame(GameData data) throws DataAccessException;      //Create a new game.
//    GameData getGameByName(String gameName) throws DataAccessException;         //Retrieve a specified game with the given game ID.
    GameData getGameByID(int gameID) throws DataAccessException;
    GameListData listGames() throws DataAccessException;       //Retrieve all games.
    void updateGame(GameData data) throws DataAccessException;      //Updates a chess game. It should replace the chess game string corresponding to a given gameID. This is used when players join a game or when a move is made.

}
