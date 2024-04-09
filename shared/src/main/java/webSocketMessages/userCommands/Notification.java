package webSocketMessages.userCommands;

public class Notification extends UserGameCommand {
    public String message;
    public Notification(String authToken) {
        super(authToken);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
