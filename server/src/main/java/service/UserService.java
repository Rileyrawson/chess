package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import model.requests.LoginRequest;
import model.requests.RegisterRequest;

import java.util.UUID;

public class UserService {

    AuthDAO authDAO;

    UserDAO userDAO;

    public UserService(AuthDAO authDAO, UserDAO userDAO) {
        this.authDAO = authDAO;
        this.userDAO = userDAO;
    }

    public Object register(RegisterRequest request) throws DataAccessException {

        String username = request.username();
        String password = request.password();
        String email = request.email();

        if (username == null || password == null || email == null ){
            throw new DataAccessException("Error: bad request");
        } else if (userDAO.getUser(username) != null) {
            throw new DataAccessException("Error: already taken");
        }

        UserData data = new UserData(username, password, email);

        String authToken = UUID.randomUUID().toString();
        AuthData authData = new AuthData(authToken, username);
        authDAO.createAuth(authData);

        userDAO.createUser(data);

        //200 { "username":"", "authToken":"" }
        //400 error: bad request
        //403 error: already taken
        //500 error: description

        return authData;
    }
    public Object login(LoginRequest request) throws DataAccessException {

        String username = request.username();
        String password = request.password();

        UserData data = userDAO.getUser(username);
        String authToken = UUID.randomUUID().toString();

        AuthData authData;
        if (username == null || password == null || data == null) {
            throw new DataAccessException("Error: unauthorized");
        } else if (!password.equals(data.password())) {
            throw new DataAccessException("Error: unauthorized");
        } else {

            authData = new AuthData(authToken, username);
            authDAO.createAuth(authData);
        }

        // 200 success { "username":"", "authToken":"" }
        // 401 error: unauthorized
        // 500 error: description

        return authData;
    }
    public Object logout(String authToken) throws DataAccessException {

        AuthData authData = authDAO.getAuth(authToken);

        if (authData == null){
            throw new DataAccessException("Error: unauthorized");
        }

        authDAO.deleteAuth(authData);

        return "success";
        //200
        //401 error: unauthorized
        //500 error: description
    }
}
