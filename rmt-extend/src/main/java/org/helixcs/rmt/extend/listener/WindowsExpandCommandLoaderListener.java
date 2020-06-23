package org.helixcs.rmt.extend.listener;

import org.helixcs.rmt.api.listener.TerminalProcessListener;
import org.helixcs.rmt.api.protocol.TerminalMessage;

/**
 * @Email: wb-zj268791@alibaba-inc.com .
 * @Author: wb-zj268791
 * @Date: 6/23/2020.
 * @Desc: Windows 拓展命令加载 Listener
 */
public class WindowsExpandCommandLoaderListener implements TerminalProcessListener {
    @Override
    public String listenerName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void beforeInit(TerminalMessage message) {

    }
}
