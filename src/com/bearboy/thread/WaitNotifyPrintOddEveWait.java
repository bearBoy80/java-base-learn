package com.bearboy.thread;

import java.util.TreeMap;

/**
 * 通过wait\notify来实现奇偶数交替打印
 */
public class WaitNotifyPrintOddEveWait implements Runnable {
    private static final Object lock = new Object();
    private static int i = 0;

    @Override
    public void run() {
        while (i <= 100) {
            synchronized (lock) {
                System.out.println( Thread.currentThread().getName() + i++);
                lock.notify();
                if (i <=100){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new WaitNotifyPrintOddEveWait();
        Thread thread1 = new Thread(runnable,"偶数 ");
        Thread thread2 = new Thread(runnable ,"奇数 ");
        thread1.start();
        thread2.start();
    }
}
