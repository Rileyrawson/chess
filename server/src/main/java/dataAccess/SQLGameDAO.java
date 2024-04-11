package dataAccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.GameListData;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLGameDAO implements GameDAO{
    @Override
    public void clear() throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("DELETE FROM game");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void createGame(GameData data) throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, chessGame) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, data.gameID());
            statement.setNString(2, data.whiteUsername());
            statement.setNString(3, data.blackUsername());
            statement.setNString(4, data.gameName());
            var json = new Gson().toJson(data.game());
            System.out.println(json);
            statement.setNString(5, json);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException(e.getMessage());}
    }

    @Override
    public GameData getGameByName(String gameName) throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = conn.prepareStatement("SELECT * FROM game WHERE gameName = ?");
            statement.setNString(1, gameName);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    int dbGameID = rs.getInt("gameID");
                    String dbWhiteUsername = rs.getString("whiteUsername");
                    String dbBlackUsername = rs.getString("blackUsername");
                    var json = rs.getString("chessGame");
                    ChessGame dbGame = new Gson().fromJson(json, ChessGame.class);
                    return new GameData(dbGameID,dbWhiteUsername, dbBlackUsername, gameName, dbGame);
                }
            }

        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    @Override
    public GameData getGameByID(int gameID) throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            if (gameID < 0) {
                throw new DataAccessException("Invalid Game ID");
            }
            var statement = conn.prepareStatement("SELECT * FROM game WHERE gameID = ?");
            statement.setInt(1, gameID);
            var rs = statement.executeQuery();
            if (rs.next()) {
                String dbGameName = rs.getString("gameName");
                String dbWhiteUsername = rs.getString("whiteUsername");
                String dbBlackUsername = rs.getString("blackUsername");
                var json = rs.getString("chessGame");
                ChessGame dbGame = new Gson().fromJson(json, ChessGame.class);
                return new GameData(gameID,dbWhiteUsername, dbBlackUsername, dbGameName, dbGame);
            }
        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    @Override
    public GameListData listGames() throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = conn.prepareStatement("SELECT * FROM game");
            ArrayList<GameData> games = new ArrayList<GameData>(); //list
            try (var rs = statement.executeQuery()) {
                while (rs.next()) {
                    int dbGameID = rs.getInt("gameID");
                    String dbGameName = rs.getString("gameName");
                    String dbWhiteUsername = rs.getString("whiteUsername");
                    String dbBlackUsername = rs.getString("blackUsername");
                    var json = rs.getString("chessGame");
                    ChessGame dbGame = new Gson().fromJson(json, ChessGame.class);
                    games.add(new GameData(dbGameID,dbWhiteUsername, dbBlackUsername, dbGameName, dbGame)); //append to list
                }
                if (games.isEmpty()){
                    GameListData emptyGameListData = new GameListData(new ArrayList<GameData>());
                    return emptyGameListData;
//                    throw new DataAccessException("no games in the database");
                }
                return new GameListData(games); //return list as gamelistdata
            }
        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
    }

    @Override
    public void updateGame(GameData data) throws DataAccessException {
        DatabaseManager.createDatabase();
        GameData tempData = getGameByID(data.gameID());
        if (tempData != null) {
            try {
                var conn = DatabaseManager.getConnection();
                var statement = conn.prepareStatement("UPDATE game SET whiteUsername = ?, blackUsername = ?, gameName = ?, chessGame = ? WHERE gameID = ?");
                statement.setNString(1, data.whiteUsername());
                statement.setNString(2, data.blackUsername());
                statement.setNString(3, data.gameName());
                var json = new Gson().toJson(data.game());
                statement.setNString(4, json);
                statement.setInt(5, data.gameID());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }
        }
        else {
            throw new DataAccessException("Error: bad request");
        }
    }
}
