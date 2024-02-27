package model.response;

public record RegisterResponse (int statusCode, String username, String password, String email, String authToken) {
}