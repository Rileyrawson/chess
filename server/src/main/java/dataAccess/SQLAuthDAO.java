package dataAccess;
import model.AuthData;
import java.sql.SQLException;


public class SQLAuthDAO implements AuthDAO{

    @Override
    public void clear() throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("DELETE FROM auth");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void createAuth(AuthData data) throws DataAccessException {
        DatabaseManager.createDatabase();
        try {
            var conn = DatabaseManager.getConnection();
            var statement = conn.prepareStatement("INSERT INTO auth (authToken, username) VALUES (?, ?)");
            statement.setNString(1, data.authToken());
            statement.setNString(2, data.username());
            statement.executeUpdate();
        } catch (SQLException e) { throw new DataAccessException(e.getMessage());}
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = conn.prepareStatement("SELECT * FROM auth WHERE authToken = ?");
            statement.setNString(1, authToken);
            try (var rs = statement.executeQuery()) {
                if (rs.next()) {
                    String dbAuthToken = rs.getString("authToken");
                    String dbUsername = rs.getString("username");
                    return new AuthData(dbAuthToken, dbUsername);
                }
            }

        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
        return null;
    }

    @Override
    public void deleteAuth(AuthData data) throws DataAccessException {
        DatabaseManager.createDatabase();
        try (var conn = DatabaseManager.getConnection()) {
            String oldAuthToken = data.authToken();
            var statement = conn.prepareStatement("DELETE FROM auth WHERE authToken = ?");
            statement.setNString(1, oldAuthToken);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(String.format("Unable to read data: %s", e.getMessage()));
        }
    }
}
