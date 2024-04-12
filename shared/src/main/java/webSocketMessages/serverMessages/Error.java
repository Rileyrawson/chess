package webSocketMessages.serverMessages;

import webSocketMessages.userCommands.UserGameCommand;

public class Error extends ServerMessage {
    public String errorMessage;

    public Error(String errorMessage) {
        super(ServerMessageType.ERROR);
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
