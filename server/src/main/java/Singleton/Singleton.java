package Singleton;

import dataAccess.MemoryAuthDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserDAO;
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
    private MemoryAuthDAO memoryAuthDAO = new MemoryAuthDAO();
    private MemoryGameDAO memoryGameDAO = new MemoryGameDAO();
    private MemoryUserDAO memoryUserDAO = new MemoryUserDAO();
    private ClearDBService clearDBService = new ClearDBService(memoryUserDAO, memoryGameDAO, memoryAuthDAO);
    private GameService gameService = new GameService(memoryAuthDAO, memoryGameDAO);
    private UserService userService = new UserService(memoryAuthDAO, memoryUserDAO);

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
    public MemoryAuthDAO getMemoryAuthDAOInstance() {return memoryAuthDAO;}
//    public MemoryGameDAO getMemoryGameDAOInstance() {return memoryGameDAO;}
//    public MemoryUserDAO getMemoryUserDAOInstance() {return memoryUserDAO;}
    public ClearDBService getClearDBServiceInstance() {return clearDBService;}
    public GameService getGameServiceInstance() {return gameService;}
    public UserService getUserServiceInstance() {return userService;}
}
