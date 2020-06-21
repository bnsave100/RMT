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
public class TerminalRQ extends TerminalStructure implements Serializable {
    //command
    private String command;
    // resize
    // compatible
    private int columns;
    private int cols;
    private int rows;

    public TerminalRQ toTerminalRQ(final TextMessage textMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(textMessage.getPayload(), TerminalRQ.class);
    }
}