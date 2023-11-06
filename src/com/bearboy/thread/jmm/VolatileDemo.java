package com.bearboy.thread.jmm;

/**
 * 演示volatile 使用场景-作为标志位
 */
public class VolatileDemo {
    private  boolean flag = false;

    public void start() {
        new Thread(() -> {
            System.out.println("Thread 1: Waiting for flag to be true...");
            while (!flag) {
                // 空循环等待flag为true
            }
            System.out.println("Thread 1: Flag is now true. Exiting...");
        }).start();

        new Thread(() -> {
            System.out.println("Thread 2: Sleeping for 2 seconds...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2: Setting flag to true...");
            flag = true;
        }).start();
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        demo.start();
    }
}
