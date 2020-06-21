package org.helixcs.rmt.api.listener;

import org.helixcs.rmt.api.lifecycle.TerminalProcessLifecycle;
import org.helixcs.rmt.api.protocol.TerminalMessage;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
public interface TerminalProcessListener extends TerminalListenerOrder {
    // 获取监听器名称
    String listenerName();

    // 准备
    default void prepared(TerminalMessage message) { }

    default void beforeInit(TerminalMessage message) {}

    // 已经完成初始化，只会执行一次
    default void afterInit(TerminalMessage message) {}

    // 发送命令
    default void beforeCommand(TerminalMessage message) {}

    default void requestToPty(final byte[] bytes) {}

    default void responseFromPty(final byte[] bytes) {}

    // 发送命令
    default void afterCommand(TerminalMessage message) {}

    // 关闭
    default void closed(TerminalMessage message) {}

    // 上下文，生命周期
    default void lifeCycleContext(TerminalProcessLifecycle terminalProcessLifecycle) {}
}
