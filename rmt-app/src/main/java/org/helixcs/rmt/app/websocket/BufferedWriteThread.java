package org.helixcs.rmt.app.websocket;

import lombok.SneakyThrows;
import org.helixcs.rmt.api.listener.TerminalProcessListenerManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
public class BufferedWriteThread extends Thread {
    private TerminalProcessListenerManager manager;

    private BufferedWriter bufferedWriter;
    private String command;

    public BufferedWriteThread setManager(TerminalProcessListenerManager manager) {
        this.manager = manager;
        return this;
    }

    public BufferedWriteThread setCommand(String command) {
        this.command = command;
        return this;
    }

    public BufferedWriteThread setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
        return this;
    }

    @SneakyThrows
    @Override
    public void run() {
        writeToPty();
    }

    private void writeToPty() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                manager.listenerMap().values().forEach(x -> x.requestToPty(command.getBytes(StandardCharsets.UTF_8)));
            }
        }).start();
        bufferedWriter.write(command);
        bufferedWriter.flush();
    }
}
