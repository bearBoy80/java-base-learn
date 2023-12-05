package com.bearboy.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo 演示 reentrantlock 如何获取锁并释放锁
 */
public class ReentrantLockDemo {
    //默认使用非公平锁
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        //创建两个线程分别获取lock
        Thread thread1 = new Thread(new Task(lock));
        Thread thread2 = new Thread(new Task(lock));
        thread1.start();
        thread2.start();
    }

}

class Task implements Runnable {
    private ReentrantLock lock;

    public Task(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            //获取锁并持有1秒
            System.out.println("线程name:" + Thread.currentThread().getName() + "尝试获取");
            lock.lockInterruptibly();
            System.out.println("线程name:" + Thread.currentThread().getName() + "获取锁");
            try {
                Thread.sleep(1000);
                System.out.println("线程name:" + Thread.currentThread().getName() + "睡眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("线程name:" + Thread.currentThread().getName() + "获取锁后被中断了");
            System.out.println("当前锁状态："+ lock.isLocked());
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
                System.out.println("线程name:" + Thread.currentThread().getName() + "释放锁");
            }
        }
    }
}
