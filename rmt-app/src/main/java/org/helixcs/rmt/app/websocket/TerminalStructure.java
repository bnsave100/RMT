package org.helixcs.rmt.app.websocket;

import lombok.Data;
import lombok.experimental.Accessors;
import org.helixcs.rmt.api.protocol.TerminalMessage;

import java.io.Serializable;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
@Data
@Accessors(chain = true)
public abstract class TerminalStructure implements TerminalMessage, Serializable {
    // commons
    protected MessageType type;

    public enum MessageType {
        TERMINAL_READY,
        TERMINAL_INIT,
        TERMINAL_RESIZE,
        TERMINAL_COMMAND,
        TERMINAL_CLOSE,
        TERMINAL_PRINT,
        TERMINAL_HEARTBEAT;
    }

}
