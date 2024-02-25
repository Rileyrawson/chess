package model.response;

public class LogoutResponse {
    private int statusCode;

    public LogoutResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
