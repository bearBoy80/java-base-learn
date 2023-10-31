package com.bearboy.thread;


import java.util.Date;

public class ThreadJoinAlternativeDemo implements Runnable {
    public static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new ThreadJoinDemo());
        Thread thread2 = new Thread(new ThreadJoinDemo());
        thread2.start();
        thread1.start();
        //主线程等待thread1 执行完成
        synchronized (thread1){
            thread1.wait();
        }
        //主线程等待thread2 执行完成
        synchronized (thread2){
            thread2.wait();
        }
        System.out.println(new Date() + Thread.currentThread().getName() + "主线程任务执行完成");
    }

    @Override
    public void run() {
        try {
            System.out.println(new Date() + Thread.currentThread().getName() + ": 正在执行耗时任务");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(new Date() + Thread.currentThread().getName() + ": 完成耗时任务");
    }
}

