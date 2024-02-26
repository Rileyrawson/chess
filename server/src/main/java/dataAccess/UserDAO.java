package dataAccess;

import model.UserData;

public interface UserDAO {

    void clear() throws DataAccessException;       //remove everything from db
    void createUser(UserData data) throws DataAccessException;  //Create a new user.
    UserData getUser(String username) throws DataAccessException;     //Retrieve a user with the given username.

}
