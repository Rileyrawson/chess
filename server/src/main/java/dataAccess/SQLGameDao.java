package dataAccess;

import model.GameData;
import model.GameListData;

public class SQLGameDao implements GameDAO{
    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void createGame(GameData data) throws DataAccessException {

    }

    @Override
    public GameData getGameByName(String gameName) throws DataAccessException {
        return null;
    }

    @Override
    public GameData getGameByID(int gameID) throws DataAccessException {
        return null;
    }

    @Override
    public GameListData listGames() throws DataAccessException {
        return null;
    }

    @Override
    public void updateGame(GameData data) throws DataAccessException {

    }
}
