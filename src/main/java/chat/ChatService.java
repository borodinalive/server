package chat;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    Set<ChatWebSocket> chatWebSockets;

    public ChatService() {
        this.chatWebSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void add(ChatWebSocket chatWebSocket) {
        chatWebSockets.add(chatWebSocket);
    }

    public void sendMessage(String data) {
        for (ChatWebSocket user : chatWebSockets) {
            try {
                user.sendString(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void remove(ChatWebSocket chatWebSocket) {
        chatWebSockets.remove(chatWebSocket);
    }

}
