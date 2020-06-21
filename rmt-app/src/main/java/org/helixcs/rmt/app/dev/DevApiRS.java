package org.helixcs.rmt.app.dev;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class DevApiRS implements Serializable {
    private Map<String, DevWsSession> devWsSessionMap;
}
