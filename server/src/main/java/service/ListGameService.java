package service;

import model.requests.ListGamesRequest;

public class ListGameService {

    public Object listGame(ListGamesRequest request){

        //NOTE: White or Black username may be null

        // 200 success { "games": [{"gameID": 1234, "whiteUsername":"", "blackUsername":"", "gameName:""} ]}
        // 401 error: unauthorized
        // 500 error: description


        return null;
    }

}
