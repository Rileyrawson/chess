package dataAccess;

import model.AuthData;
import model.UserData;

import java.sql.SQLException;

public class SQLUserDAO implements UserDAO{
    @Override
    public void clear() throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("DELETE FROM user");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void createUser(UserData data) throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("INSERT INTO user (username, password, email) VALUES (?, ?, ?)");
            statement.setNString(1, data.username());
            statement.setNString(2, data.password());
            statement.setNString(3, data.email());

            statement.executeUpdate();

        } catch (SQLException e) { throw new DataAccessException(e.getMessage());}
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setNString(1, username);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    String dbUsername = rs.getString("username");
                    String dbPassword = rs.getString("password");
                    String dbEmail = rs.getString("email");
                    return new UserData(dbUsername, dbPassword, dbEmail);
                }
            }

        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }
}
