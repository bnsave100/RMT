package org.helixcs.rmt.app.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.socket.TextMessage;

import java.io.Serializable;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/19/2020.
 * @Desc:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class TerminalRS extends TerminalStructure implements Serializable {
    private String text;

    public TextMessage toTextMessage() throws JsonProcessingException {
        TerminalStructure terminalStructure = new TerminalRS().setText(text).setType(type);
        String message = new ObjectMapper().writeValueAsString(terminalStructure);
        return new TextMessage(message);
    }

}
