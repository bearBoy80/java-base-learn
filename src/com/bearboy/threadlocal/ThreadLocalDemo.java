package com.bearboy.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo {
    // 创建一个ThreadLocal对象
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    ;

    public static void main(String[] args) {
        // 创建并启动两个线程
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // 设置线程本地变量的值
            threadLocal.set((int) (Math.random() * 100));
            // 访问线程本地变量的值
            System.out.println("ThreadLocal value in " + Thread.currentThread().getName() + ": " + threadLocal.get());

            // 模拟一些耗时的操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 清除线程本地变量的值
            threadLocal.remove();
        }
    }
}