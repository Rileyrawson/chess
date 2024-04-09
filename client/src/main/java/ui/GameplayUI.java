package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPosition;
import model.AuthData;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class GameplayUI {


    public static void help(){
        System.out.println("Available Commands:");
        System.out.println("Help: What commands are available");
        System.out.println("Redraw Chess Board");
        System.out.println("Leave: Remove yourself from the game");
        System.out.println("Make Move: <>");
        System.out.println("Resign: Forfeit game");
        System.out.println("Highlight Legal Moves: Display possible moves");
    }


    //display legal moves

}
