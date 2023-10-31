package com.bearboy.thread;

import java.util.concurrent.TimeUnit;

/**
 * jvm 退出只会关注用户相关的线程，针对daemon线程是不会管的
 */
public class ThreadDaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (i< 1000) {
                System.out.println(Thread.currentThread().getName() + ": 执行中");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
            System.out.println(Thread.currentThread().getName() + ": 执行完成");

        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
    }
}
