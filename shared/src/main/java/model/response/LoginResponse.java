package model.response;

public record LoginResponse (int statusCode, int authToken, String username, String password) { }
