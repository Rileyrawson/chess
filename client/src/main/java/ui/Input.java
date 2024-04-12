package ui;

import facade.ServerFacade;
import facade.WebSocketFacade;
import model.AuthData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Input {

    private static boolean resign = false;

    public static List<String> parseInputPre(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Type input here: ");
        String line = scanner.nextLine();
        line = line.toLowerCase();

        String str[] = line.split(" ");
        List<String> args = Arrays.asList(str);
        return new ArrayList<>(args);
    }
    public static List<String> parseInputPost(AuthData authData){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("[" + authData.username() + "]" + " Type input here: ");
        String line = scanner.nextLine();
        line = line.toLowerCase();

        String str[] = line.split(" ");
        List<String> args = Arrays.asList(str);
        return new ArrayList<>(args);
    }

    static void preLogin (){
        AuthData authData = new AuthData("uninitialized", "uninitialized");
        while (true) {
            ArrayList<String> args = (ArrayList<String>) parseInputPre();
            if (args.get(0).equals("help")) {
                PreloginUI.help();
            } else if (args.get(0).equals("quit")) {
                PreloginUI.quit();
                break;
            } else if (args.get(0).equals("register") && args.size() == 4) {
                authData = ServerFacade.register(args.get(1), args.get(2), args.get(3));
                if (!authData.authToken().equals("error")) {
                    postLogin(authData);
                    break;
                } else {
                    System.out.println("invalid login credentials");
                }
            } else if (args.get(0).equals("login") && args.size() == 3) {
                authData = ServerFacade.login(args.get(1), args.get(2));
                if (!authData.authToken().equals("error")) {
                    postLogin(authData);
                    break;
                } else {
                    System.out.println("invalid login credentials");
                }
            } else {
                System.out.println("Invlaid Input\n");
                PreloginUI.help();
            }
        }
    }

    static void postLogin (AuthData authData){
        while (true){
            ArrayList<String> args = (ArrayList<String>) parseInputPost(authData);
            if (args.get(0).equals("help")){
                PostloginUI.help();
            } else if (args.get(0).equals("logout")) {
                ServerFacade.logout(authData);
                preLogin();
                break;
            } else if (args.get(0).equals("create") && args.get(1).equals("game") && args.size() == 3) {
                ServerFacade.createGame(args.get(2), authData);
            } else if (args.get(0).equals("list") && args.get(1).equals("games")) {
                ServerFacade.listGames(authData);
            } else if (args.get(0).equals("join") && args.get(1).equals("game") && args.size() == 4) {
                String playerColor = args.get(2);
                String gameID = args.get(3);
                ServerFacade.joinGame(playerColor, gameID, authData);
                //Open a WebSocket connection with the server (using the /connect endpoint) so it can send and receive gameplay messages.
                gamePlay(authData, playerColor, gameID);
                break;
            } else if (args.get(0).equals("join") && args.get(1).equals("observer") && args.size() == 3) {
                String gameID = args.get(2);
                ServerFacade.joinObserver(gameID, authData);
                //Open a WebSocket connection with the server (using the /connect endpoint) so it can send and receive gameplay messages.
                gameObserver(authData, gameID);
                break;
            } else {
                System.out.println("Invlaid Input\n");
                PostloginUI.help();
            }
        }
    }

    static void gamePlay (AuthData authData, String color, String gameID){
        WebSocketFacade webSocketFacade = new WebSocketFacade();
        webSocketFacade.setColor(color);
        webSocketFacade.joinPlayer(authData.authToken(), gameID, color);
        webSocketFacade.redrawBoard();

        while (true) {
            ArrayList<String> args = (ArrayList<String>) parseInputPost(authData);
            if (args.get(0).equals("help")) {
                GameplayUI.help();
            }
            else if (args.get(0).equals("redraw") && args.get(1).equals("chess") && args.get(2).equals("board")) {
                webSocketFacade.redrawBoard();
            }
            else if (args.get(0).equals("leave")) {
                webSocketFacade.leave(authData.authToken(), gameID);
                postLogin(authData);
            }
            else if (args.get(0).equals("make move")) {     //TO DO
                //make the move
                //update board (already handled in lab 1?)
            }
            else if (args.get(0).equals("resign")) {        //TO DO
                resign = true;
                System.out.println("Are you sure you want to resign? YES/no");
            }
            else if (resign == true && args.get(0).equals("yes")){
//                Resign resign = new Resign(authData.authToken()); //send a message to the server "user has resigned"
                webSocketFacade.resign(authData.authToken(), gameID);
                endGameLoop(authData, color, gameID, webSocketFacade);
            } else if (resign == true && args.get(0).equals("no")) {
                resign = false;
                System.out.println("Continue playing");
                GameplayUI.help();
            }
            else if (args.get(0).equals("highlight legal moves")) { //TO do
            }
            else {
                System.out.println("Error: Invlaid Input\n");
                GameplayUI.help();
            }
        }
    }

    static void gameObserver (AuthData authData, String gameID){
        WebSocketFacade webSocketFacade = new WebSocketFacade();
        webSocketFacade.setColor("observer");
        webSocketFacade.joinObserver(authData.authToken(), gameID);
        webSocketFacade.redrawBoard();

        while (true) {
            ArrayList<String> args = (ArrayList<String>) parseInputPost(authData);
            if (args.get(0).equals("help")) {
                ObserverUI.help();
            }
            else if (args.get(0).equals("redraw") && args.get(1).equals("chess") && args.get(2).equals("board")) {
                webSocketFacade.redrawBoard();
            }
            else if (args.get(0).equals("leave")) {
                webSocketFacade.leave(authData.authToken(), gameID);
                postLogin(authData);
            }
            else if (args.get(0).equals("highlight legal moves")) { // to do
            }
            else {
                System.out.println("Error: Invlaid Input\n");
                ObserverUI.help();
            }
        }
    }


    static void endGameLoop (AuthData authData, String color, String gameID, WebSocketFacade webSocketFacade){
        while (true) {
            ArrayList<String> args = (ArrayList<String>) parseInputPost(authData);
            if (args.get(0).equals("help")) {
                EndGameUI.help();
            }
            else if (args.get(0).equals("redraw chess board")) {
                webSocketFacade.redrawBoard();
            }
            else if (args.get(0).equals("leave")) {
                webSocketFacade.leave(authData.authToken(), gameID);
                postLogin(authData);
            }
            else {
                System.out.println("Error: Invlaid Input\n");
                EndGameUI.help();
            }
        }

    }


    public static void input() throws Exception {
        preLogin();
    }
}
