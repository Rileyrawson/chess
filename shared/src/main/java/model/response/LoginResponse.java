package model.response;

public class LoginResponse {

    private int statusCode;
    private int authToken;
    private String username;
    private String password;

    public LoginResponse(int statusCode, int authToken, String username, String password) {
        this.statusCode = statusCode;
        this.authToken = authToken;
        this.username = username;
        this.password = password;
    }

    public int getStatusCode() {
        return statusCode;
    }

//    public int getAuthToken() {
//        return authToken;
//    }

//    public String getUsername() {
//        return username;
//    }

//    public String getPassword() {
//        return password;
//    }

}
