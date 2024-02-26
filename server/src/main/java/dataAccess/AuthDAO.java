package dataAccess;

import model.AuthData;

public interface AuthDAO {

    String authToken = null;

    void clear() throws DataAccessException;       //remove everything from db
    void createAuth(AuthData data) throws DataAccessException;  //Create a new authorization.
    AuthData getAuth(String authToken) throws DataAccessException;     //Retrieve an authorization given an authToken.
    void deleteAuth(AuthData data) throws DataAccessException;  //Delete an authorization so that it is no longer valid.


}
