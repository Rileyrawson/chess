package dataAccess;

import model.UserData;
import java.util.ArrayList;
import java.util.HashSet;

public class MemoryUserDAO implements UserDAO {

    HashSet<UserData> users = new HashSet<>();

    public MemoryUserDAO() {}

    @Override
    public void clear() throws DataAccessException {
        users = new HashSet<>();
    }

    @Override
    public void createUser(UserData data) throws DataAccessException {
        users.add(data);
    }

    @Override
    public UserData getUser(String username) throws DataAccessException {
        for (UserData data: users){
            if (data.username().equals(username)){
                return data;
            }
        }
        return null;
    }
}
