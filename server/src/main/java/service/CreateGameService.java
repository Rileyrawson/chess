package service;

import model.requests.CreateGameRequest;
import model.response.CreateGameResponse;
import model.response.ErrorResponse;

public class CreateGameService {

    public Object createGame(CreateGameRequest request){

        // try to create game if not 400(request/client error) or 500(server error)
        if (request.getGameName() == null){ //no game name
            return new ErrorResponse(400, "Error: bad request");
        }

        // check if authorized (auth token) if not 401
        else if (request.getAuthToken().equals("1234")){ // Auth is OK TODO: Hard Coded
            return new CreateGameResponse(200, 1);
        }
        else if (!request.getAuthToken().equals("1234")){ // Auth is not OK TODO: Hard Coded
            return new ErrorResponse(401, "Error: unauthorized");
        }

        else {
            return new ErrorResponse(500, "Error: description");
        }

    }
}
