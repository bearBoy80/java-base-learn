package com.bearboy.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo 演示 reentrantlock 中断后会释放锁
 */
public class ReentrantLockInterruptDemo {
    //默认使用非公平锁
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        //创建两个线程分别获取lock
        Thread thread1 = new Thread(new Task(lock));
        Thread thread2 = new Thread(new Task(lock));
        thread1.start();
        thread2.start();
        //尝试中断thread1、thread2
        thread1.interrupt();
        thread2.interrupt();

    }

}