package ui;

import model.AuthData;

import java.util.Scanner;

public class PostloginUI {

    public static void help(){
        System.out.println("Available Commands:");
        System.out.println("Help: What commands are available");
        System.out.println("Quit: Exit the program");
        System.out.println("Logout: Log out of account");
        System.out.println("Create Game: Create new game Input: <GAMENAME>");
        System.out.println("List Games");
        System.out.println("Join Game: Joins existing game as player Input: color(WHITE/BLACK) <GAMEID>");
        System.out.println("Join Observer: Create new game Input: <GAMEID>");
    }

    public static void logout(AuthData authData){

    }


}
