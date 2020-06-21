package org.helixcs.rmt.api.session;

import org.helixcs.rmt.api.protocol.TagFilter;
import org.springframework.web.socket.WebSocketMessage;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractTerminalSessionManager implements TerminalSessionManager {
    private final Map<String, SessionWrapper> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void registerSession(SessionWrapper SessionWrapper) {
        sessionMap.put(SessionWrapper.webSocketSession().getId(), SessionWrapper);
    }

    @Override
    public SessionWrapper getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    @Override
    public void removeSession(SessionWrapper SessionWrapper) {
        removeSession(SessionWrapper.webSocketSession().getId());
    }

    @Override
    public Map<String, SessionWrapper> sessionMap() {
        return sessionMap;
    }

    @Override
    public <T> void p2pSend(SessionWrapper SessionWrapper, WebSocketMessage<T> webSocketMessage) throws IOException {
        if (!SessionWrapper.webSocketSession().isOpen()) {
            return;
        }
        SessionWrapper.webSocketSession().sendMessage(webSocketMessage);
    }

    @Override
    public <T> void broadCastSend(Map<String, SessionWrapper> sessionMap, WebSocketMessage<T> webSocketMessage) {
        sessionMap.forEach((key, value) -> {
            try {
                p2pSend(value, webSocketMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public <T> void filteredSend(TagFilter messageFilter, Map<String, SessionWrapper> sessionMap, WebSocketMessage<T> webSocketMessage) {


    }

    public void removeSession(final String sessionId) {
        sessionMap.remove(sessionId);
    }
}