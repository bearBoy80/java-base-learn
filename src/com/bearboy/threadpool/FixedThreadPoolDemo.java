package com.bearboy.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executors.newFixedThreadPool 创建一个固定大小的线程池，
 * 默认队列：LinkedBlockingQueue 默认队列长度是Integer.MAX_VALUE,这个可能会出现oom,因，由于coresize = maxsize， keepalive 为0。
 * keepalive:含义when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)
                Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(new Task());
        }
        poolExecutor.shutdown();
    }

}