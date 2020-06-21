package org.helixcs.rmt.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/18/2020.
 * @Desc:
 */
public class TerminalThreadHelper {
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private String threadName;

        public DefaultThreadFactory setThreadName(String threadName) {
            this.threadName = threadName;
            return this;
        }

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-" + threadName;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
            if (t.isDaemon()) { t.setDaemon(false); }
            if (t.getPriority() != Thread.NORM_PRIORITY) { t.setPriority(Thread.NORM_PRIORITY); }
            return t;
        }
    }

    public static ExecutorService readerHandlerThreadPool = Executors.newFixedThreadPool(10,
        new DefaultThreadFactory().setThreadName("stdout"));
    public static ExecutorService errorHandlerThreadPool = Executors.newFixedThreadPool(10,
        new DefaultThreadFactory().setThreadName("stderr"));
    public static ExecutorService writeHandlerThreadPool = Executors.newFixedThreadPool(10,
        new DefaultThreadFactory().setThreadName("stdin"));
}
