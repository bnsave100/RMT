package org.helixcs.rmt.app.configuration;

import org.helixcs.rmt.api.protocol.TerminalMessageQueue;
import org.helixcs.rmt.api.lifecycle.TerminalProcessLifecycle;
import org.helixcs.rmt.api.listener.TerminalProcessListenerManager;
import org.helixcs.rmt.api.session.TerminalSession2ProcessManager;
import org.helixcs.rmt.api.session.TerminalSessionManager;
import org.helixcs.rmt.app.websocket.TerminalHandler;
import org.helixcs.rmt.app.websocket.TerminalWsSessionProcessLifecycle;
import org.helixcs.rmt.extend.listener.DefaultTerminalListenerManager;
import org.helixcs.rmt.extend.protocol.DefaultTerminalMessageQueue;
import org.helixcs.rmt.extend.session.DefaultTerminalSession2ProcessManager;
import org.helixcs.rmt.extend.session.DefaultTerminalSessionManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/17/2020.
 * @Desc:
 */
@SpringBootApplication(scanBasePackages = "org.helixcs.rmt")
@EnableWebSocket
public class AppConfiguration implements WebSocketConfigurer, CommandLineRunner {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "/terminal")
                .setAllowedOrigins("*")
        ;
    }

    //@Bean
    //public TerminalProcessListenerManager terminalProcessListenerManager() {
    //    SimpleTerminalListenerManager s = new SimpleTerminalListenerManager();
    //    s.registerListener(new TerminalProcessListener() {
    //        @Override
    //        public String listenerName() {
    //            return "mylistener";
    //        }
    //
    //        @Override
    //        public void lifeCycleContext(TerminalProcessLifecycle terminalProcessLifecycle) {
    //            System.out.println("lifeCycle");
    //        }
    //    });
    //    return s;
    //}

    @Bean
    @Scope("prototype")
    public TerminalProcessLifecycle terminalProcessLifecycle() {
        return new TerminalWsSessionProcessLifecycle();
    }

    @Bean
    public TerminalProcessListenerManager terminalListenerManager() {
        return new DefaultTerminalListenerManager();
    }

    @Bean
    public TerminalMessageQueue<String> terminalMessageQueue() {
        return new DefaultTerminalMessageQueue();
    }

    @Bean
    public TerminalSessionManager terminalSessionManager() {
        return new DefaultTerminalSessionManager();
    }

    @Bean
    public TerminalSession2ProcessManager terminalSession2ProcessManager() {
        return new DefaultTerminalSession2ProcessManager();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new PerConnectionWebSocketHandler(TerminalHandler.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(":)");
    }
}
