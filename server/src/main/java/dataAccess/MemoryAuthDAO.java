package dataAccess;


import model.AuthData;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {

    private HashMap<String,String> authTokens = new HashMap<>();

    public MemoryAuthDAO() { //FOR TESTING
        authTokens.put("123","un");
    }

    @Override
    public void clear() throws DataAccessException {
        authTokens = new HashMap<>();
    }

    @Override
    public void createAuth(AuthData data) throws DataAccessException {
        authTokens.put(data.authToken(), data.username());
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        if (authTokens.containsKey(authToken)) {
            String username = authTokens.get(authToken);
            return new AuthData(authToken, username);
        }
        return null;
    }

    @Override
    public void deleteAuth(AuthData data) throws DataAccessException {
        authTokens.remove(data.authToken());
    }
}
