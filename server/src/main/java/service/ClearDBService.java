package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import model.response.ErrorResponse;

public class ClearDBService {

    private UserDAO userDao;
    private GameDAO gameDao;
    private AuthDAO authDao;

    public ClearDBService(UserDAO userDao, GameDAO gameDao, AuthDAO authDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.authDao = authDao;
    }

    public Object clear() throws DataAccessException {

        try {
            userDao.clear();
            gameDao.clear();
            authDao.clear();
        } catch (DataAccessException exception){
            return new ErrorResponse(exception.getMessage());
        }

        //delete everything without authorization

        //successfully clear = status 200
        //other error status 500

        return "success";
    }
}
