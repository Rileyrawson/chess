package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPosition;
import model.AuthData;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class PostloginUI {

    private static int boardRow = 8;
    private static int boardCol = 8;


    public static void help(){
        System.out.println("Available Commands:");
        System.out.println("Help: What commands are available");
        System.out.println("Logout: Log out of account");
        System.out.println("Create Game: Create new game Input: <GAMENAME>");
        System.out.println("List Games");
        System.out.println("Join Game: Joins existing game as player Input: color(WHITE/BLACK) <GAMEID>");
        System.out.println("Join Observer: Joins existing game Input: <GAMEID>");
    }


    public static StringBuilder whiteBottom(ChessBoard board, StringBuilder stringBuilder){
        int rowLabel = 8;
        stringBuilder.append(RESET_TEXT_COLOR + SET_BG_COLOR_LIGHT_GREY + "    a  b  c  d  e  f  g  h    ");
        for (int i = boardRow - 1; i >= 0; i--) {
            stringBuilder.append(SET_BG_COLOR_DARK_GREY);
            stringBuilder.append("\n");
            stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
            stringBuilder.append(" " + rowLabel + " ");
            for (int j = 0; j < boardCol; j++) {
                ChessPosition position = new ChessPosition(i + 1,j + 1);
                if (j % 2 == 0){
                    if (i % 2 == 0){
                        stringBuilder.append(SET_BG_COLOR_BLACK);
                    } else {
                        stringBuilder.append(SET_BG_COLOR_WHITE);
                    }
                } else {
                    if (i % 2 == 0){
                        stringBuilder.append(SET_BG_COLOR_WHITE);
                    } else {
                        stringBuilder.append(SET_BG_COLOR_BLACK);
                    }
                }
                if (board.getPiece(position) == null) {
                    stringBuilder.append("   ");
                }
                else {
                    if (board.getTeamAtPosition(position) == ChessGame.TeamColor.WHITE){
                        stringBuilder.append(SET_TEXT_COLOR_BLUE + board.getPiece(position).toString() + RESET_TEXT_COLOR);
                    }
                    else if (board.getTeamAtPosition(position) == ChessGame.TeamColor.BLACK){
                        stringBuilder.append(SET_TEXT_COLOR_RED + board.getPiece(position).toString() + RESET_TEXT_COLOR);
                    } else {
                        stringBuilder.append(board.getPiece(position).toString());
                    }
                }
                stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
            }
            stringBuilder.append(" " + rowLabel  + " "); // label the row number
            rowLabel = rowLabel - 1;
            stringBuilder.append(SET_BG_COLOR_DARK_GREY);
        }
        stringBuilder.append("\n");
        stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
        stringBuilder.append("    a  b  c  d  e  f  g  h    ");
        stringBuilder.append(SET_BG_COLOR_DARK_GREY);
        stringBuilder.append("\n");
        stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
        return stringBuilder;
    }

    public static StringBuilder blackBottom(ChessBoard board, StringBuilder stringBuilder){
        int rowLabel = 1;
        stringBuilder.append(RESET_TEXT_COLOR + SET_BG_COLOR_LIGHT_GREY + "    h  g  f  e  d  c  b  a    ");
        for (int i = 0; i < boardRow; i++) {
            stringBuilder.append(SET_BG_COLOR_DARK_GREY);
            stringBuilder.append("\n");
            stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
            stringBuilder.append(" " + rowLabel + " ");
            for (int j = boardCol - 1; j >= 0; j--) {
                ChessPosition position = new ChessPosition(i + 1,j + 1);
                if (j % 2 == 0){
                    if (i % 2 == 0){
                        stringBuilder.append(SET_BG_COLOR_BLACK);
                    } else {
                        stringBuilder.append(SET_BG_COLOR_WHITE);
                    }
                } else {
                    if (i % 2 == 0){
                        stringBuilder.append(SET_BG_COLOR_WHITE);
                    } else {
                        stringBuilder.append(SET_BG_COLOR_BLACK);
                    }
                }
                if (board.getPiece(position) == null ) {
                    stringBuilder.append("   ");
                }
                else {
                    if (board.getTeamAtPosition(position) == ChessGame.TeamColor.WHITE){
                        stringBuilder.append(SET_TEXT_COLOR_BLUE + board.getPiece(position).toString() + RESET_TEXT_COLOR);
                    }
                    else if (board.getTeamAtPosition(position) == ChessGame.TeamColor.BLACK){
                        stringBuilder.append(SET_TEXT_COLOR_RED + board.getPiece(position).toString() + RESET_TEXT_COLOR);
                    } else {
                        stringBuilder.append(board.getPiece(position).toString());
                    }
                }
                stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
            }
            stringBuilder.append(" " + rowLabel + " "); // label the row number
            rowLabel = rowLabel + 1;
            stringBuilder.append(SET_BG_COLOR_DARK_GREY);
        }
        stringBuilder.append("\n");
        stringBuilder.append(SET_BG_COLOR_LIGHT_GREY);
        stringBuilder.append("    h  g  f  e  d  c  b  a    ");
        stringBuilder.append(SET_BG_COLOR_DARK_GREY);
        stringBuilder.append("\n");
        return stringBuilder;
    }


    public static StringBuilder drawBoard(String color, ChessBoard board){ //working
//        ChessBoard board = new ChessBoard();                        //todo get chessboard from websocket
        StringBuilder stringBuilder = new StringBuilder();
        board.resetBoard();
        color.toLowerCase();
        if (color.equals("white")){
            stringBuilder = whiteBottom(board, stringBuilder);
            stringBuilder.append(SET_BG_COLOR_DARK_GREY + "\n" );
        } else if (color.equals("black")) {
            stringBuilder = blackBottom(board, stringBuilder);
            stringBuilder.append(SET_TEXT_COLOR_WHITE);
        } else if (color.equals("observer")){
            stringBuilder = whiteBottom(board, stringBuilder);
            stringBuilder.append(SET_BG_COLOR_DARK_GREY + "\n" );
        }else{
            stringBuilder.append("draw board error");

        }
        return stringBuilder;
    }

}
