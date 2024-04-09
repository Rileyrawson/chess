import webSocketMessages.serverMessages.ServerMessage;

import javax.websocket.*;
import java.net.URI;

public class Client extends ServerMessage {
    public Client(ServerMessageType type) {
        super(type);
    }

//    public Session session;
//    public Client() throws Exception {
//        URI uri = new URI("ws://localhost:8080/connect");
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//        this.session = container.connectToServer(this, uri);
//        this.session.addMessageHandler(new MessageHandler.Whole<String>() {
//
//            //when receive logic, do the command (i.e make move, join game
//
//            public void onMessage(String message) {
//                System.out.println(message);
//            }
//
//        });
//    }

    @Override
    public void notify(ServerMessage message) {
        switch (message.getServerMessageType()) {
            case NOTIFICATION -> displayNotification(((NotificationMessage) message).getMessage());
            case ERROR -> displayError(((ErrorMessage) message).getErrorMessage());
            case LOAD_GAME -> loadGame(((LoadGameMessage) message).getGame());
        }
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }
}
