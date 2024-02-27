package model.response;

public class ErrorResponse {

    private String message;

    public ErrorResponse(String errorMessage) {
        this.message = errorMessage;
    }

    public String getErrorMessage() {
        return message;
    }
}
