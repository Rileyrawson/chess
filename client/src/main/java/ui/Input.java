package ui;

import facade.ServerFacade;
import model.AuthData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Input {


    public static List<String> parseInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Type input here >>>> ");
        String line = scanner.nextLine();
        line = line.toLowerCase();

        String str[] = line.split(" ");
        List<String> args = Arrays.asList(str);
        return new ArrayList<>(args);
    }

    public static void input() throws Exception { // TODO: break into seperate functions? check for incorrect input (not enough args/too many args)
        boolean isLoggedIn = false;
        AuthData authData = new AuthData("uninitialized", "uninitialized");
        while (!isLoggedIn) {
            ArrayList<String> args = (ArrayList<String>) parseInput();
            for(String x: args){
//                System.out.println(x);
            }

            if (args.get(0).equals("help")) {
                PreloginUI.help();
            } else if (args.get(0).equals("quit")) {
                PreloginUI.quit();
                break;
            } else if (args.get(0).equals("login") && args.size() == 3) {
                authData = ServerFacade.login(args.get(1), args.get(2));
//                System.out.println(authData.authToken());
                if (!authData.authToken().equals("error")){
                    isLoggedIn = true;
                    break;
                } else { System.out.println("invalid login credentials");}
            } else if (args.get(0).equals("register") && args.size() == 4) {
                authData = ServerFacade.register(args.get(1), args.get(2), args.get(3));
            } else {
               System.out.println("Invlaid Input\n");
               PreloginUI.help();
            }
        }
        while (isLoggedIn){
            ArrayList<String> args = (ArrayList<String>) parseInput();
            for(String x: args){
//                System.out.println(x);
            }
            if (args.get(0).equals("help")){
                PostloginUI.help();
            } else if (args.get(0).equals("logout")) {
                PostloginUI.logout(authData);
            } else if (args.get(0).equals("create") && args.get(1).equals("game") && args.size() == 3) {
                ServerFacade.CreateGame(args.get(2), authData);
            } else if (args.get(0).equals("list") && args.get(1).equals("games")) {
                ServerFacade.listGames(authData);
            } else if (args.get(0).equals("join") && args.get(1).equals("game") && args.size() == 4) {
                ServerFacade.joinGame(args.get(2), args.get(3), authData);
            } else if (args.get(0).equals("join") && args.get(1).equals("observer") && args.size() == 3) {
                ServerFacade.joinObserver(args.get(2), authData);
            } else {
                System.out.println("Invlaid Input\n");
                PostloginUI.help();
            }
        }
    }
}
