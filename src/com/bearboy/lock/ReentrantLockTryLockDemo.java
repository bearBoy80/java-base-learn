package com.bearboy.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demo 演示 reentrantlock 中断后会释放锁
 */
public class ReentrantLockTryLockDemo {
    //默认使用非公平锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //创建两个线程分别获取lock
        Thread thread1 = new Thread(new TryLockTask(lock));
        Thread thread2 = new Thread(new TryLockTask(lock));
        thread1.start();
        thread2.start();
        //尝试中断thread1、thread2
        thread1.interrupt();
        thread2.interrupt();
    }

}

class TryLockTask implements Runnable {
    private Lock lock;

    public TryLockTask(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            //获取锁并持有1秒
            System.out.println("线程name:" + Thread.currentThread().getName() + "尝试获取");
            if (lock.tryLock()) {
                System.out.println("线程name:" + Thread.currentThread().getName() + "获取锁");
            }
            try {
                Thread.sleep(1000);
                System.out.println("线程name:" + Thread.currentThread().getName() + "睡眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("线程name:" + Thread.currentThread().getName() + "获取锁后被中断了");
        } finally {
            System.out.println("线程name:" + Thread.currentThread().getName() + "释放锁");
            lock.unlock();
        }
    }
}