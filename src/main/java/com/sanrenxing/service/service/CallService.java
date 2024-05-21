package com.sanrenxing.service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CallService {

    private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(UUID userId, WebSocketSession session) {
        sessions.put(userId, session);
    }

    public void removeSession(UUID userId) {
        sessions.remove(userId);
    }

    public void handleSignalMessage(WebSocketSession session, String message) throws Exception {
        // Handle signaling messages here
        // e.g., forward messages to the appropriate user
        String payload = message;
        // Parse the payload and handle different message types
    }

    public void startCall(UUID from, UUID to) throws Exception {
        // Notify the callee of the incoming call
        String message = String.format("{\"type\": \"call\", \"from\": \"%s\"}", from);
        sendMessageToUser(to, message);
    }

    public void stopCall(UUID from, UUID to) throws Exception {
        // Implement call stop logic
        String message = String.format("{\"type\": \"call_stop\", \"from\": \"%s\"}", from);
        sendMessageToUser(to, message);
    }

    private void sendMessageToUser(UUID userId, String message) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }
}

