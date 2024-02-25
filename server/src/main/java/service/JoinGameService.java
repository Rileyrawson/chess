package service;

import model.requests.JoinGameRequest;
import model.response.ErrorResponse;

public class JoinGameService {
    public Object joinGame(JoinGameRequest request){

        //Verifies that the specified game exists
        if (request.getGameID() == "321"){ //TODO: Hard Coded

        }

        //if color specified, adds the caller as the requested color to the game

        //if no color, user is joined as an observer

        //200
        //400 error: bad request
        //401 error: unauthorized
        //403 error: already taken
        //500 error: description



        return null;
    }
}
