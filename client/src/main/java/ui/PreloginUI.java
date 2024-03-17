package ui;

import java.util.Scanner;

public class PreloginUI {

    public static void help(){
        System.out.println("Available Commands:");
        System.out.println("Help: What commands are available");
        System.out.println("Quit: Exit the program");
        System.out.println("Register:  <USERNAME> <PASSWORD> <EMAIL> to register");
        System.out.println("Login: <USERNAME> <PASSWORD> to login");
    }

    public static void quit(){
        System.out.println("Goodbye!");
    }

}
