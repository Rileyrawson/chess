package model.response;

public class RegisterResponse {

    private int statusCode;
    private String username;
    private String password;
    private String email;
    private String authToken;

    public RegisterResponse(int statusCode, String username, String password, String email, String authToken) {
        this.statusCode = statusCode;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authToken = authToken;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthToken() {
        return authToken;
    }
}