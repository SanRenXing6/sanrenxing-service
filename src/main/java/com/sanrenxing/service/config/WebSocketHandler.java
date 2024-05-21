package com.sanrenxing.service.config;

import com.sanrenxing.service.service.CallService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final CallService callService;

    public WebSocketHandler(CallService callService) {
        this.callService = callService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UUID userId = getUserIdFromSession(session);
        sessions.put(userId, session);
        callService.addSession(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        callService.handleSignalMessage(session, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        UUID userId = getUserIdFromSession(session);
        sessions.remove(userId);
        callService.removeSession(userId);
    }

    private UUID getUserIdFromSession(WebSocketSession session) {
        // Extract user ID from the session (e.g., from query parameters or session attributes)
        // This is a placeholder implementation
        return UUID.fromString(session.getUri().getQuery().split("=")[1]);
    }
}
