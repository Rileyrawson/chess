package facade;

import javax.websocket.*;

import com.google.gson.Gson;
import webSocketMessages.serverMessages.*;
import webSocketMessages.userCommands.UserGameCommand;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketFacade extends Endpoint{
    private Session session;


    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }

    @OnClose
    public void onClose() {  //closes the connection
        System.out.println("Connection closed");
        close();
    }
    public void close() {
        try {
            this.session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMessage(Session session, String message) throws URISyntaxException, DeploymentException, IOException {  //todo takes in the UserGameCommand message. depending on message it will "do" the logic. -> call gameservice? or send to handler??
        UserGameCommand command = new Gson().fromJson(message, UserGameCommand.class);


        //code from documentation
        URI uri = new URI("ws://localhost:8080/connect");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        this.session = container.connectToServer(this, uri);
        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
            public void onMessage(String message) {
                System.out.println(message);
            }
        });


    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error -> " + throwable.getMessage());
    }

}
