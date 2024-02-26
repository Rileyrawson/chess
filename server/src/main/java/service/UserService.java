package service;

import model.requests.LoginRequest;
import model.requests.RegisterRequest;

public class UserService {
    public Object register(RegisterRequest request){


        //200 { "username":"", "authToken":"" }
        //400 error: bad request
        //403 error: already taken
        //500 error: description


        return null;
    }
    public Object login(LoginRequest request){

        // 200 success { "username":"", "authToken":"" }
        // 401 error: unauthorized
        // 500 error: description

        return null;
    }
    public Object logout(){

        //200
        //401 error: unauthorized
        //500 error: description

        return null;
    }
}
