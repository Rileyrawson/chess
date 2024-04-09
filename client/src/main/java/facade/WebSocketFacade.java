package facade;

import javax.websocket.*;
import webSocketMessages.serverMessages.*;

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

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket error -> " + throwable.getMessage());
    }

}
