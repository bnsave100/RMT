package org.helixcs.rmt.app.controller;

import org.helixcs.rmt.api.session.SessionWrapper;
import org.helixcs.rmt.api.session.TerminalSession2ProcessManager;
import org.helixcs.rmt.api.session.TerminalSessionManager;
import org.helixcs.rmt.app.dev.DevApiRS;
import org.helixcs.rmt.app.dev.DevWsSession;
import org.helixcs.rmt.app.websocket.TerminalRS;
import org.helixcs.rmt.app.websocket.TerminalStructure;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/17/2020.
 * @Desc:
 */

@Controller
@RequestMapping("ws")
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "index.html";
    }

    @RequestMapping("multiple")
    public String multiple() {
        return "multiple.html";
    }

    @RequestMapping("dev")
    public String ops() {
        return "dev.html";
    }

    @Resource
    private TerminalSessionManager terminalSessionManager;

    @RequestMapping("dev/api/showSession")
    @ResponseBody
    public DevApiRS showSession() {

        return new DevApiRS().setDevWsSessionMap(terminalSessionManager.sessionMap().values().stream().collect(Collectors.toMap(
                x -> x.webSocketSession().getId(), x -> new DevWsSession()
                        .setOpen(x.webSocketSession().isOpen())
                        .setRemoteHost(Objects.requireNonNull(x.webSocketSession().getRemoteAddress()).getAddress().getHostAddress())
                        .setRemotePort(Objects.requireNonNull(x.webSocketSession().getRemoteAddress()).getPort())
                        .setSessionId(x.webSocketSession().getId())
                        .setUri(Objects.requireNonNull(x.webSocketSession().getUri()).toString()))));
    }

    @RequestMapping("dev/api/p2pPush")
    @ResponseBody
    public String p2pPush(String sessionId, String text) {
        SessionWrapper sessionWrapper = terminalSessionManager.getSession(sessionId);
        WebSocketSession session = sessionWrapper.webSocketSession();
        if (session == null) {
            return "session is invalid";
        }
        try {
            TerminalRS terminalRS = new TerminalRS().setText(text);
            terminalRS.setType(TerminalStructure.MessageType.TERMINAL_PRINT);

            terminalSessionManager.p2pSend(sessionWrapper, terminalRS.toTextMessage());
        } catch (IOException e) {
            return "send failed," + e.getMessage();
        }
        return "ok";
    }

    @RequestMapping("dev/api/broadcastPush")
    @ResponseBody
    public String broadcastPush(String text) {
        Map<String, SessionWrapper> sessionMap = terminalSessionManager.sessionMap();
        try {
            TerminalRS terminalRS = new TerminalRS().setText(text);
            terminalRS.setType(TerminalStructure.MessageType.TERMINAL_PRINT);
            terminalSessionManager.broadCastSend(sessionMap, terminalRS.toTextMessage());
        } catch (IOException e) {
            return "send failed," + e.getMessage();
        }
        return "ok";
    }

    @Resource
    private TerminalSession2ProcessManager terminalSession2ProcessManager;

    @RequestMapping("dev/api/terminalSession2ProcessManager")
    @ResponseBody
    public String terminalSession2ProcessManager(String text) {
        terminalSession2ProcessManager.terminalSession2ProcessMappedMap().values().forEach(x -> {
            BufferedWriter write = x.processWrapper().write();
            try {
                write.write("ping baidu.com\r");
                write.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "ok";
    }

}
