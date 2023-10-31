package com.bearboy.thread;

/**
 * 通过synchronized 实现交替打印奇偶数
 * 缺点:两个线程同时获取一把锁会出现竞争，消耗无用的资源
 * 比如thread 线程可以在一段时间内一直获取到锁，导致thread1 无法获取到锁
 */
public class WaitNotifyPrintOddEvenSyn {
    private static final Object lock = new Object();
    private static int i = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (i < 100) {
                synchronized (lock) {
                    if ((i & 1) == 0) {
                        System.out.println("偶数：" + i);
                        i++;
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (i < 100) {
                synchronized (lock) {
                    if ((i & 1) != 0) {
                        System.out.println("奇数：" + i);
                        i++;
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
