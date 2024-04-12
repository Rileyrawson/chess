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


    public static StringBuilder drawBoard(String color, ChessBoard board){ //working
        StringBuilder stringBuilder = new StringBuilder();
        color.toLowerCase();
        if (color.equals("white")){
            stringBuilder.append(SET_BG_COLOR_DARK_GREY + "\n" );
        } else if (color.equals("black")) {
            stringBuilder.append(SET_TEXT_COLOR_WHITE);
        } else if (color.equals("observer")){
            stringBuilder.append(SET_BG_COLOR_DARK_GREY + "\n" );
        }else{
            stringBuilder.append("draw board error");
        }
        stringBuilder.append(SET_TEXT_COLOR_WHITE);
        return stringBuilder;
    }

}
