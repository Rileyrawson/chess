package Singleton;

import dataAccess.*;
import server.*;
import service.ClearDBService;
import service.GameService;
import service.UserService;

public class Singleton {

    private ClearDBHandler clearDBHandler = new ClearDBHandler();
    private CreateGameHandler createGameHandler = new CreateGameHandler();
    private JoinGameHandler joinGameHandler = new JoinGameHandler();
    private ListGameHandler listGameHandler = new ListGameHandler();
    private LoginHandler loginHandler = new LoginHandler();
    private LogoutHandler logoutHandler = new LogoutHandler();
    private RegisterHandler registerHandler = new RegisterHandler();
    private SQLAuthDAO authDAO = new SQLAuthDAO();
    private SQLGameDAO gameDAO = new SQLGameDAO();
    private SQLUserDAO userDAO = new SQLUserDAO();
    private ClearDBService clearDBService = new ClearDBService(getUserDAOInstance(), getGameDAOInstance(), getAuthDAOInstance());
    private GameService gameService = new GameService(getAuthDAOInstance(), getGameDAOInstance());
    private UserService userService = new UserService(getAuthDAOInstance(), getUserDAOInstance());

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public ClearDBHandler getClearDBHandlerInstance(){ return clearDBHandler;}
    public CreateGameHandler getCreateGameHandlerInstance(){ return createGameHandler;}
    public JoinGameHandler getJoinGameHandlerInstance(){ return joinGameHandler;}
    public ListGameHandler getListGameHandlerInstance() { return listGameHandler;}
    public LoginHandler getLoginHandlerInstance(){ return loginHandler;}
    public LogoutHandler getLogoutHandlerInstance() {return  logoutHandler;}
    public RegisterHandler getRegisterHandlerInstance() { return registerHandler;}
    public AuthDAO getAuthDAOInstance() {return authDAO;}
    public GameDAO getGameDAOInstance() {return gameDAO;}
    public UserDAO getUserDAOInstance() {return userDAO;}
    public ClearDBService getClearDBServiceInstance() {return clearDBService;}
    public GameService getGameServiceInstance() {return gameService;}
    public UserService getUserServiceInstance() {return userService;}
}
