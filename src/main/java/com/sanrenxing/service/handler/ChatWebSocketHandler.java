package com.sanrenxing.service.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        webSocketSessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] data = payload.split(":", 2);
        String toUserId = data[0];
        String messageContent = data[1];

        WebSocketSession toSession = webSocketSessions.get(toUserId);
        if (toSession != null && toSession.isOpen()) {
            toSession.sendMessage(new TextMessage(messageContent));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session.getId());
    }

    private String getUserId(WebSocketSession session) {
        return (String) session.getAttributes().get("userId");
    }
}
