package org.helixcs.rmt.expand.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.cache.KVCache;
import com.orbitz.consul.model.kv.Value;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.helixcs.rmt.api.session.AbstractTerminalSessionManager;
import org.helixcs.rmt.api.session.SessionWrapper;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class ConsulTerminalSessionManager extends AbstractTerminalSessionManager {

    private static final int DEFAULT_PORT = 8500;
    private static final int DEFAULT_WATCH_TIMEOUT = 60 * 1000;
    private static final String WATCH_TIMEOUT = "consul-watch-timeout";
    private String consulHost = "127.0.0.1";
    private int consulPort = DEFAULT_PORT;
    private final Consul client;
    private final KeyValueClient kvClient;
    private KVCache kvCache;

    public ConsulTerminalSessionManager() {
        Consul.Builder builder = Consul.builder()
            .withHostAndPort(HostAndPort.fromParts(consulHost, consulPort)).withPing(true);
        client = builder.build();
        kvClient = client.keyValueClient();
    }

    @SneakyThrows
    @Override
    public void registerSession(SessionWrapper sessionWrapper) {
        WebSocketSession ws = sessionWrapper.webSocketSession();
        InternalConsulWSValue internalConsulWSValue = new InternalConsulWSValue();
        internalConsulWSValue
            .setSessionId(ws.getId())
            .setRemoteHost(Objects.requireNonNull(ws.getRemoteAddress()).getHostString())
            .setRemotePort(
                ws.getRemoteAddress().getPort());
        //internalConsulWSValue.setWebSocketSession(ws);

        String s = new ObjectMapper().writeValueAsString(internalConsulWSValue);
        kvClient.putValue(ws.getId(), s);
        super.registerSession(sessionWrapper);
    }

    @SneakyThrows
    @Override
    public SessionWrapper getSession(String sessionId) {
        String s = kvClient.getValueAsString(sessionId, Charsets.UTF_8).orElse(null);
        //if (s != null) {
        //    InternalConsulWSValue internalConsulWSValue = new ObjectMapper().readValue(s, InternalConsulWSValue.class);
        //    return new SessionWrapper() {
        //        @Override
        //        public WebSocketSession webSocketSession() {
        //            return internalConsulWSValue.getWebSocketSession();
        //        }
        //    };
        //}
        return super.getSession(sessionId);
    }

    @Override
    public void removeSession(String sessionId) {
        super.removeSession(sessionId);
        kvClient.deleteKey(sessionId);
    }

    @Data
    @Accessors(chain = true)
    private class InternalConsulWSValue implements Serializable {
        private String sessionId;
        private String remoteHost;
        private int remotePort;
        //private WebSocketSession webSocketSession;
    }

}
