package org.helixcs.rmt.app.websocket;

import java.io.BufferedReader;


/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
public class BufferedReaderThread extends AbstractBufferedThread {

    protected TerminalStructure.MessageType messageType;

    public BufferedReaderThread setMessageType(TerminalStructure.MessageType messageType) {
        this.messageType = messageType;
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
            int defaultSendLength = 2 * 1024;
            char[] data = new char[defaultSendLength];
            while ((nRead = bufferedReader.read(data, 0, data.length)) != -1) {
                TerminalRS terminalRS = new TerminalRS().setText(String.valueOf(data, 0, nRead));
                terminalRS.setType(this.messageType);
                sendToClient(terminalRS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
