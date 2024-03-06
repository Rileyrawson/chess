package dataAccess;

import model.AuthData;

public class SQLAuthDao implements AuthDAO{
    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void createAuth(AuthData data) throws DataAccessException {

    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAuth(AuthData data) throws DataAccessException {

    }
}
