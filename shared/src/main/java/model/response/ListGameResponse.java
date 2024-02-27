package model.response;

public record ListGameResponse (int statusCode, int gameId, String whiteUsername, String blackUsername, String gameName){ }