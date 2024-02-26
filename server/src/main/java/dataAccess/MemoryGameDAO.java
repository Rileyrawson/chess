package dataAccess;

import chess.ChessGame;
import model.GameData;
import java.util.ArrayList;
import java.util.List;

public class MemoryGameDAO implements GameDAO{

    ArrayList<GameData> games = new ArrayList<>();


    public MemoryGameDAO() { //FOR TESTING
        GameData data = new GameData(0, "white", "black", "game", new ChessGame());
        games.add(data);
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
    public List<GameData> listGames() throws DataAccessException {
        return games;
    }

    @Override
    public void updateGame(GameData data) throws DataAccessException {
        GameData tempData = getGameByID(data.gameID());
        if (tempData != null){
            games.remove(tempData);
            GameData updatedData = tempData.updateWhiteUser(data.whiteUsername());
            updatedData = tempData.updateBlackUser(data.blackUsername());
        } else {
            throw new DataAccessException("Error: bad request");
        }
    }

}
