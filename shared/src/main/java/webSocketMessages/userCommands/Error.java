package webSocketMessages.userCommands;

public class Error extends UserGameCommand{
    public String errorMessage;
    public Error(String authToken) {
        super(authToken);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
