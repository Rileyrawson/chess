package ui;

import facade.ServerFacade;
import facade.WebSocketFacade;
import model.AuthData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Input {


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
//            for (String x : args) {
////                System.out.println(x);
//            }
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
//                System.out.println(authData.authToken());
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
            for(String x: args){
//                System.out.println(x);
            }
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
                ServerFacade.joinObserver(args.get(2), authData);
                //Open a WebSocket connection with the server (using the /connect endpoint) so it can send and receive gameplay messages.
                gamePlay(authData, "observer");
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

        while (true) {
            ArrayList<String> args = (ArrayList<String>) parseInputPost(authData);
            if (args.get(0).equals("help")) {
                GameplayUI.help();
            }
            else if (args.get(0).equals("redraw chess board")) {
                webSocketFacade.redrawBoard();
            }
            else if (args.get(0).equals("leave")) {         //TODO
                //**checks color is/not observer
                //remove user from game in db using http
                //send notification to the server "user has left"
                //closes websocket session
                if (color.equals("black") || color.equals("white")) {
                    ServerFacade.joinGame(null, gameID, authData);
                }



                postLogin(authData);
            }
            else if (args.get(0).equals("make move")) {     //TODO
                //make the move
                //update board (already handled in lab 1?)
            }
            else if (args.get(0).equals("resign")) {        //TODO
                System.out.println("Are you sure you want to resign? YES/no");
                if (args.get(0).equals("yes")){
                    //send a message to the server "user has resigned"
                    //end the game.

                    //player doesn't leave game
                    //how to make sure game is ended(no more moves, cannot join, etc.?)
                } else if (args.get(0).equals("no")) {
                    System.out.println("Continue playing");
                    GameplayUI.help();
                }
                else {
                    System.out.println("invalid input");
                    GameplayUI.help();
                }
            }
            else if (args.get(0).equals("highlight legal moves")) { //TODO

            }
            else {
                System.out.println("Error: Invlaid Input\n");
                GameplayUI.help();
            }
        }
    }


    /*  TODO:
    leave
        player
        observer
    resign
    make move
    display legal moves
    no moves after resign, checkmate, or stalemate

    check notification
    checkmate notification
     */

    public static void input() throws Exception {
        preLogin();
    }
}
