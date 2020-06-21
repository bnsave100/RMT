package org.helixcs.rmt.app.websocket;

import org.helixcs.rmt.api.listener.TerminalProcessListenerManager;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.helixcs.rmt.app.websocket.TerminalStructure.MessageType.TERMINAL_PRINT;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
public class BufferedReaderThread extends Thread {
    private TerminalProcessListenerManager manager;
    private WebSocketSession webSocketSession;
    private BufferedReader bufferedReader;
    private final int defaultSendLength = 2 * 1024;

    public BufferedReaderThread setManager(TerminalProcessListenerManager manager) {
        this.manager = manager;
        return this;
    }

    public BufferedReaderThread setWebSocketSession(final WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
        return this;
    }

    public BufferedReaderThread setBufferedReader(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        return this;
    }

    @Override
    public void run() {
        // send message to client
        printReader(bufferedReader);
    }

    private void printReader(BufferedReader bufferedReader) {
        try {
            int nRead;
            char[] data = new char[defaultSendLength];
            while ((nRead = bufferedReader.read(data, 0, data.length)) != -1) {
                sendToClient(String.valueOf(data, 0, nRead));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToClient(String text) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                manager.listenerMap().forEach((key, value) -> value.responseFromPty(text.getBytes(
                    StandardCharsets.UTF_8)));
            }
        }).start();
        TerminalRS terminalRS = new TerminalRS().setText(text);
        terminalRS.setType(TERMINAL_PRINT);
        // browser refresh and close session
        if (webSocketSession.isOpen()) {
            try {
                webSocketSession.sendMessage(terminalRS.toTextMessage());
            }catch (Exception ex){
                // do nothing
            }
        }
    }
}
