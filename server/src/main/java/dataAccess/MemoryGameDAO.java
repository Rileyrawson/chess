package dataAccess;

import chess.ChessGame;
import model.GameData;
import model.GameListData;

import java.util.ArrayList;
import java.util.List;

public class MemoryGameDAO implements GameDAO{

    ArrayList<GameData> games = new ArrayList<>();

    public MemoryGameDAO() {
    }

    @Override
    public void clear() throws DataAccessException {
        games = new ArrayList<>();
    }

    @Override
    public void createGame(GameData data) throws DataAccessException {
        games.add(data);
    }

    @Override
    public GameData getGameByName(String gameName) throws DataAccessException {
        for (GameData data: games){
            if (data.gameName().equals(gameName)){
                return data;
            }
        }
        return null;
    }

    @Override
    public GameData getGameByID(int gameID) throws DataAccessException{
        for (GameData data: games){
            if (data.gameID() == gameID){
                return data;
            }
        }
        return null;
    }

    @Override
    public GameListData listGames() throws DataAccessException {
        GameListData data = new GameListData(games);
        return data;
    }

    @Override
    public void updateGame(GameData data) throws DataAccessException {
        GameData tempData = getGameByID(data.gameID());
        if (tempData != null){
            games.remove(tempData);
            createGame(data);
        } else {
            throw new DataAccessException("Error: bad request");
        }
    }

}
