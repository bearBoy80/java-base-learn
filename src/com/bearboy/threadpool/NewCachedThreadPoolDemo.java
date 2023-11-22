package com.bearboy.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * newCachedThreadPool 可以无限创建线程，提交一个任务创建一个线程
 * 队列是：SynchronousQueue
 */
public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)
                Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(new Task());
        }
        poolExecutor.shutdown();
    }
}
