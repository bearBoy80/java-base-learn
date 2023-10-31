package com.bearboy.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 默认main 启动会创建jvm相关的线程，VM Thread、Reference Handle 等，我们创建的线程id默认并不是从2开始
 */
public class ThreadIdDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main线程id:" + Thread.currentThread().getId());
        Thread thread = new Thread(() -> {
        });
        thread.start();
        System.out.println(thread.getId());
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo info : threadInfos) {
            System.out.println(info.getThreadName() + "--" + info.getThreadId());
        }
    }

}
