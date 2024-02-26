package dataAccess;

import model.UserData;

public interface UserDao {

    String username = null;
    String password = null;
    String email = null;

    Void create();

    void insertUser(UserData u) throws DataAccessException;


    Void read();
    Void update();
    Void delete();



    Void clear() throws DataAccessException;       //remove everything from db

    Void createUser() throws DataAccessException;  //Create a new user.
    Void authenticateUser() throws DataAccessException; //username and password match
    Void getUser() throws DataAccessException;     //Retrieve a user with the given username.

}
