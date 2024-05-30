package com.sanrenxing.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CallWSController extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        webSocketSessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, Object> messageData = parseMessage(payload);
        String toUserId = (String) messageData.get("toUserId");
        WebSocketSession toSession = webSocketSessions.get(toUserId);

        if(toSession!=null && toSession.isOpen()){
            toSession.sendMessage(new TextMessage(payload));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(webSocketSessions.containsValue(session)){
            webSocketSessions.values().remove(session);
        }
    }

    private String getUserId(WebSocketSession session) {
        return (String) session.getAttributes().get("userId");
    }

    private Map<String, Object> parseMessage(String message) throws IOException {
        return objectMapper.readValue(message, HashMap.class);
    }
}
