package com.bearboy.thread.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * 默认Threadname 为Thread-xx
 * 如果线程已启动，无法修改操作系统层面的thread name，但是可以修改jvm 层面的thread name
 */
public class ThreadNameDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (true) {
                        if (!Thread.currentThread().isInterrupted()) {
                            System.out.println(Thread.currentThread().getName());
                        } else {
                            break;
                        }
                    }
                });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread.setName("demo-1");
        TimeUnit.MILLISECONDS.sleep(10);
        thread.interrupt();
    }
}
